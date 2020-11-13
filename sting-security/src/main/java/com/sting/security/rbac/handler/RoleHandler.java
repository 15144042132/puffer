package com.sting.security.rbac.handler;

import com.sting.core.spring.ContextKit;
import com.sting.core.spring.EnvKit;
import com.sting.db.dao.StDao;
import com.sting.db.wrapper.StWrapper;
import com.sting.security.rbac.annotation.Role;
import com.sting.security.rbac.annotation.RoleExclude;
import com.sting.security.rbac.table.StLinkRoleResource;
import com.sting.security.rbac.table.StResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色注解处理器
 * 扫描角色对应资源权限
 */
@Slf4j
@Component
public class RoleHandler {
    @Autowired
    private StDao dao;

    public HashMap<String, HashSet<String>> roleResourceMap = new HashMap<>();

    //扫描所有角色资源
    public void scanRole() {
        Collection<Object> values = ContextKit.getContext().getBeansWithAnnotation(Role.class).values();
        for (Object bean : values) {
            Class<?> aClass = bean.getClass();
            RequestMapping controllerMapping = AnnotationUtils.findAnnotation(aClass, RequestMapping.class);
            if (controllerMapping == null) {
                continue;
            }
            Role role = AnnotationUtils.findAnnotation(aClass, Role.class);

            //遍历所有资源
            for (String controllerValue : controllerMapping.value()) {

                String controllerPath = EnvKit.contextPath() + controllerValue;
                if (role != null) {
                    for (String roleCode : role.value()) {
                        HashSet<String> orDefault = roleResourceMap.getOrDefault(roleCode, new HashSet<>());
                        orDefault.add(controllerPath);
                        roleResourceMap.put(roleCode, orDefault);
                    }
                }

                /*method*/
                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
                    if (requestMapping == null) {
                        continue;
                    }

                    Role roleMethod = AnnotationUtils.findAnnotation(method, Role.class);
                    RoleExclude roleExclude = AnnotationUtils.findAnnotation(method, RoleExclude.class);

                    //遍历所有资源
                    for (String methodValue : requestMapping.value()) {
                        String methodPath = controllerPath + methodValue;
                        /*添加资源*/
                        if (roleMethod != null) {
                            for (String roleCode : roleMethod.value()) {
                                HashSet<String> orDefault = roleResourceMap.getOrDefault(roleCode, new HashSet<>());
                                orDefault.add(methodPath);
                                roleResourceMap.put(roleCode, orDefault);
                            }
                        }
                        if (role != null) {
                            for (String roleCode : role.value()) {
                                HashSet<String> orDefault = roleResourceMap.getOrDefault(roleCode, new HashSet<>());
                                orDefault.add(methodPath);
                                roleResourceMap.put(roleCode, orDefault);
                            }
                        }
                        /*排除资源*/
                        if (roleExclude != null) {
                            for (String roleCode : roleExclude.value()) {
                                HashSet<String> orDefault = roleResourceMap.getOrDefault(roleCode, new HashSet<>());
                                orDefault.remove(methodPath);
                                roleResourceMap.put(roleCode, orDefault);
                            }
                        }
                    }
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void refreshRoleResource() {
        //全部资源
        List<StResource> list = dao.list(StResource.class);
        Set<String> roleIds = new HashSet<>();
        ArrayList<StLinkRoleResource> stLinkRoleResources = new ArrayList<>();

        /* 1.类上的注解处理
         * 填充 roleIds 需要更新的角色ID
         * 填充 stLinkRoleResources 角色关联表资源列表
         */
        for (String roleCode : roleResourceMap.keySet()) {
            Object roleId = dao.selectObj("select id from sys_role where code='" + roleCode + "'");
            roleIds.add(roleId + "");
            HashSet<String> resources = roleResourceMap.get(roleCode);
            Set<String> ids = list.stream().filter(it -> resources.contains(it.getUrl())).map(StResource::getId).collect(Collectors.toSet());
            for (String resId : ids) {
                StLinkRoleResource stLinkRoleResource = new StLinkRoleResource();
                stLinkRoleResource.setRoleId(roleId + "");
                stLinkRoleResource.setResourceId(resId);
                stLinkRoleResources.add(stLinkRoleResource);
            }
        }



        /* TODO 2.配置类处理
         * 填充 roleIds 需要更新的角色ID
         * 填充 stLinkRoleResources 角色关联表资源列表
         */








        /*2.更新数据库关联
         *
         */
        long deleteCount = dao.delete(new StWrapper<>(StLinkRoleResource.class).in("role_id", roleIds));
        long insertCount = dao.insertBatch(stLinkRoleResources);
        log.info(deleteCount + "");
        log.info(insertCount + "");
    }
}
