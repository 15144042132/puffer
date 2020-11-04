package com.sting.security.rbac.authority;

import com.sting.core.spring.EnvKit;
import com.sting.db.dao.StDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 资源处理器
 * 读取全部资源，更新数据库
 */
@Slf4j
@Configuration
public class RoleHandler implements ApplicationContextAware {
    @Autowired
    private StDao dao;
    @Autowired
    private RoleHandler handler;

    HashMap<String, HashSet<String>> stringHashSetHashMap = new HashMap<>();

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        handler.intiRes();
        System.out.println(stringHashSetHashMap);
    }

    //扫描所有资源
    synchronized void intiRes() {
        Collection<Object> values = context.getBeansWithAnnotation(Role.class).values();
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
                        HashSet<String> orDefault = stringHashSetHashMap.getOrDefault(roleCode, new HashSet<>());
                        orDefault.add(controllerPath);
                        stringHashSetHashMap.put(roleCode, orDefault);
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
                                HashSet<String> orDefault = stringHashSetHashMap.getOrDefault(roleCode, new HashSet<>());
                                orDefault.add(methodPath);
                                stringHashSetHashMap.put(roleCode, orDefault);
                            }
                        }
                        if (role != null) {
                            for (String roleCode : role.value()) {
                                HashSet<String> orDefault = stringHashSetHashMap.getOrDefault(roleCode, new HashSet<>());
                                orDefault.add(methodPath);
                                stringHashSetHashMap.put(roleCode, orDefault);
                            }
                        }
                        /*排除资源*/
                        if (roleExclude != null) {
                            for (String roleCode : roleExclude.value()) {
                                HashSet<String> orDefault = stringHashSetHashMap.getOrDefault(roleCode, new HashSet<>());
                                orDefault.remove(methodPath);
                                stringHashSetHashMap.put(roleCode, orDefault);
                            }
                        }
                    }
                }
            }
        }
    }

}
