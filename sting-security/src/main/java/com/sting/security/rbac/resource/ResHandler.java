package com.sting.security.rbac.resource;

import com.alibaba.fastjson.JSON;
import com.sting.core.spring.EnvKit;
import com.sting.db.dao.StDao;
import com.sting.db.wrapper.StWrapper;
import com.sting.security.rbac.table_entity.SysLinkRoleResource;
import com.sting.security.rbac.table_entity.SysResource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
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
@Slf4j
@Configuration
public class ResHandler implements ApplicationContextAware {
    @Autowired
    private StDao dao;
    @Autowired
    private ResHandler handler;

    private ApplicationContext context;
    private ArrayList<ResEntity> cList = null;
    private ArrayList<ResEntity> mList = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        handler.intiRes();
        handler.createTable();
        handler.refreshResource();
        handler.refreshRoleResource();

        System.out.println("  ");
        System.out.println(JSON.toJSONString(cList));
        System.out.println("  ");
        System.out.println(JSON.toJSONString(mList));
        System.out.println("  ");
    }

    //扫描所有资源
    synchronized void intiRes() {
        ArrayList<ResEntity> moduleList = new ArrayList<>();
        ArrayList<ResEntity> resList = new ArrayList<>();
        Collection<Object> values = context.getBeansWithAnnotation(Res.class).values();
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

    //建表
    @Transactional(rollbackFor = Exception.class)
    synchronized void createTable() {
        dao.insert(sys_role);
        dao.insert(sys_user);
        dao.insert(sys_resource);
        dao.insert(sys_link_role_user);
        dao.insert(sys_link_role_resource);
    }

    //更新数据库中的资源
    @Transactional(rollbackFor = Exception.class)
    synchronized void refreshResource() {
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

    //更新代码中所配置的角色资源情况
    private synchronized void refreshRoleResource() {

    }


    private static final String sys_link_role_resource = "CREATE TABLE IF NOT EXISTS `sys_link_role_resource`  (\n" +
            "  `role_id` bigint UNSIGNED NOT NULL,\n" +
            "  `resource_id` bigint UNSIGNED NOT NULL,\n" +
            "  PRIMARY KEY (`role_id`, `resource_id`) USING BTREE,\n" +
            "  UNIQUE INDEX `role_id`(`role_id`, `resource_id`) USING BTREE COMMENT '联合唯一索引'\n" +
            ") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '关联表（角色-资源）' ROW_FORMAT = COMPACT;\n";

    private static final String sys_link_role_user = "CREATE TABLE IF NOT EXISTS `sys_link_role_user`  (\n" +
            "  `role_id` bigint UNSIGNED NOT NULL,\n" +
            "  `user_id` bigint UNSIGNED NOT NULL,\n" +
            "  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,\n" +
            "  UNIQUE INDEX `menu_id`(`user_id`, `role_id`) USING BTREE COMMENT '联合唯一索引'\n" +
            ") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '关联表（角色-菜单）' ROW_FORMAT = COMPACT;\n";

    private static final String sys_resource = "CREATE TABLE IF NOT EXISTS `sys_resource`  (\n" +
            "  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',\n" +
            "  `pid` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT 'pid',\n" +
            "  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',\n" +
            "  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源地址',\n" +
            "  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',\n" +
            "  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',\n" +
            "  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',\n" +
            "  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',\n" +
            "  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',\n" +
            "  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',\n" +
            "  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',\n" +
            "  PRIMARY KEY (`id`) USING BTREE,\n" +
            "  UNIQUE INDEX `url`(`url`) USING BTREE COMMENT '资源唯一索引'\n" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 304 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = COMPACT;\n";

    private static final String sys_role = "CREATE TABLE IF NOT EXISTS `sys_role`  (\n" +
            "  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',\n" +
            "  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',\n" +
            "  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一标记，用作见名知意(不可重复，不可修改)',\n" +
            "  `detail` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',\n" +
            "  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',\n" +
            "  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',\n" +
            "  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',\n" +
            "  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',\n" +
            "  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',\n" +
            "  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',\n" +
            "  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',\n" +
            "  PRIMARY KEY (`id`) USING BTREE,\n" +
            "  UNIQUE INDEX `name`(`name`) USING BTREE COMMENT '角色名唯一',\n" +
            "  UNIQUE INDEX `code`(`code`) USING BTREE COMMENT '角色code唯一'\n" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = COMPACT;\n";

    private static final String sys_user = "CREATE TABLE IF NOT EXISTS `sys_user`  (\n" +
            "  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',\n" +
            "  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',\n" +
            "  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',\n" +
            "  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',\n" +
            "  `head` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',\n" +
            "  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',\n" +
            "  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座机号',\n" +
            "  `id_card` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',\n" +
            "  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',\n" +
            "  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭住址',\n" +
            "  `states` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '用户状态（1=正常，2=因为违规已停用）',\n" +
            "  `login_ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后一次登录IP',\n" +
            "  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次登录时间',\n" +
            "  `refresh_token` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '刷新Token',\n" +
            "  `sort` int UNSIGNED NULL DEFAULT 100 COMMENT '排序(默认=100)',\n" +
            "  `create_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据创建者',\n" +
            "  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',\n" +
            "  `update_uid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '数据修改者',\n" +
            "  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',\n" +
            "  `is_use` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否可用(1=可用 , 2=不可用)',\n" +
            "  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '逻辑删除(1=已删除 , 2=未删除)',\n" +
            "  PRIMARY KEY (`id`) USING BTREE,\n" +
            "  UNIQUE INDEX `account`(`account`) USING BTREE COMMENT '账号唯一',\n" +
            "  UNIQUE INDEX `phone`(`phone`) USING BTREE COMMENT '手机号唯一',\n" +
            "  UNIQUE INDEX `emaile`(`email`) USING BTREE COMMENT '邮箱唯一'\n" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 137 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = COMPACT;\n";


}
