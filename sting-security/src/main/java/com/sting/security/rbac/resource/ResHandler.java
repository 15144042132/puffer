package com.sting.security.rbac.resource;

import com.sting.core.spring.ContextKit;
import com.sting.core.spring.EnvKit;
import com.sting.db.dao.StDao;
import com.sting.db.wrapper.StWrapper;
import com.sting.security.rbac.table.SysLinkRoleResource;
import com.sting.security.rbac.table.SysResource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 资源处理器
 * 读取全部资源，更新数据库
 */
@Order(998)
@Slf4j
@Configuration
public class ResHandler {
    @Autowired
    private StDao dao;

    private ArrayList<ResEntity> cList = new ArrayList<>();
    private ArrayList<ResEntity> mList = new ArrayList<>();

    //扫描所有资源
    public void scanResource() {
        ArrayList<ResEntity> moduleList = new ArrayList<>();
        ArrayList<ResEntity> resList = new ArrayList<>();
        Collection<Object> values = ContextKit.getContext().getBeansWithAnnotation(Res.class).values();
        for (Object bean : values) {
            Class<?> aClass = bean.getClass();
            Res resC = AnnotationUtils.findAnnotation(aClass, Res.class);
            RequestMapping controllerMapping = AnnotationUtils.findAnnotation(aClass, RequestMapping.class);
            if (resC == null || controllerMapping == null) continue;

            //遍历所有资源
            for (String controllerValue : controllerMapping.value()) {
                //class
                String rescValue = StringUtils.isBlank(resC.value()) ? aClass.getName() : resC.value();
                String controllerPath = EnvKit.contextPath() + controllerValue;
                ResEntity moduleEntity = new ResEntity();
                moduleEntity.setName(rescValue);
                moduleEntity.setPName(rescValue);
                moduleEntity.setUrl(controllerPath);
                moduleList.add(moduleEntity);

                //method
                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
                    Res resMethod = AnnotationUtils.findAnnotation(method, Res.class);
                    if (requestMapping == null || resMethod == null) continue;
                    //遍历所有资源
                    for (String methodValue : requestMapping.value()) {
                        String methodPath = controllerPath + methodValue;
                        ResEntity resEntity = new ResEntity();
                        resEntity.setUrl(methodPath);
                        resEntity.setName(StringUtils.isBlank(resMethod.value()) ? method.getName() : resMethod.value());
//                        resEntity.setParentName(resC.value());
                        resEntity.setPName(rescValue);
                        resList.add(resEntity);
                    }
                }
            }
        }
        cList = moduleList;
        mList = resList;
    }

    //更新数据库中的资源
    @Transactional(rollbackFor = Exception.class)
    public void refreshDbResource() {
        /*项目中的全部资源*/
        ArrayList<ResEntity> resList = new ArrayList<>();
        resList.addAll(cList);
        resList.addAll(mList);
        List<String> resUrlList = resList.stream().map(ResEntity::getUrl).collect(Collectors.toList());

        /*数据库中的全部资源*/
        List<SysResource> dbResList = dao.list(SysResource.class);
        List<String> dbUrlList = dbResList.stream().map(SysResource::getUrl).collect(Collectors.toList());

        /*
         *筛选资源
         *删除 -- deleteResCIds
         *新增 -- insertResC -- 项目中存在，数据库中不存在
         *更新 -- updateResC
         */
        ArrayList<SysResource> insertResCList = new ArrayList<>();
        ArrayList<String> deleteResCIds = new ArrayList<>();
        ArrayList<SysResource> updateResCList = new ArrayList<>();

        //筛选新增资源--项目中存在，数据库中不存在
        for (ResEntity resEntity : resList) {
            if (!dbUrlList.contains(resEntity.getUrl())) {
                SysResource sysResource = new SysResource();
                sysResource.setName(resEntity.getName());
                sysResource.setParentName(resEntity.getPName());
                sysResource.setUrl(resEntity.getUrl());
                sysResource.setPid("0");//初始为0后面会做修改
                insertResCList.add(sysResource);
            }
        }

        //筛选，更新和删除
        for (SysResource sysResource : dbResList) {
            String dbUrl = sysResource.getUrl();

            //待更新 -- 数据库中存在，项目中存在
            if (resUrlList.contains(dbUrl)) {
                List<ResEntity> collect = resList.stream().filter(it -> it.getUrl().equals(dbUrl)).collect(Collectors.toList());
                ResEntity resEntity = collect.get(0);
                sysResource.setName(resEntity.getName());
                sysResource.setParentName(resEntity.getPName());
                updateResCList.add(sysResource);
            }

            //待删除 -- 数据库中存在，项目中不存在
            if (!resUrlList.contains(dbUrl)) {
                deleteResCIds.add(sysResource.getId());
            }
        }

        //添加新资源
        dao.insertBatch(insertResCList);

        //更新资源
        ArrayList<SysResource> allResC = new ArrayList<>();
        allResC.addAll(insertResCList);
        allResC.addAll(updateResCList);
        //更新PID
        for (SysResource insertRes : allResC) {
            if (insertRes.getName().equals(insertRes.getParentName())) {
                insertRes.setPid("0");
            } else {
                for (SysResource sysResource : allResC) {
                    if (sysResource.getName().equals(insertRes.getParentName())) {
                        insertRes.setPid(sysResource.getId());
                        break;
                    }
                }
            }
        }
        dao.updateBatchById(allResC);


        //删除失效资源和角色资源关联表
        long l = dao.deleteByIds(SysResource.class, deleteResCIds);

        if (deleteResCIds.size() > 0) {
            dao.delete(new StWrapper<>(SysLinkRoleResource.class).in("role_id", deleteResCIds));
        }


        //TODO 这里注释
//        List<SysResource> list = dao.list(SysResource.class);
//        List<SysResource> gen = list.stream().filter(it -> it.getPid().equals("0")).collect(Collectors.toList());
//        List<SysResource> notGen = list.stream().filter(it -> !it.getPid().equals("0")).collect(Collectors.toList());

//        log.info("list     {}", JSON.toJSONString(list));
//        log.info("gen     {}", JSON.toJSONString(gen));
//        log.info("notGen  {}", JSON.toJSONString(notGen));

//        ArrayList<JSONObject> arrayList = TreeKit.listToTree(gen, notGen);
//        log.info("全部资源树化结果      {}", JSON.toJSONString(arrayList));
        //        修改后更新资源

    }

}
