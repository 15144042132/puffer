package com.sting.security.rbac;

import com.alibaba.fastjson.JSON;
import com.sting.core.spring.EnvKit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 资源注解处理器
 */
@Configuration
@RestControllerAdvice
public class ResourceHandler implements ApplicationContextAware {
    private static ApplicationContext context;
    private static ArrayList<ResEntity> cList = null;
    private static ArrayList<ResEntity> mList = null;

    public static ArrayList<ResEntity> getAllList() {
        intiRes();
        cList.addAll(mList);
        return cList;
    }

    public static ArrayList<ResEntity> getResMList() {
        intiRes();
        return mList;
    }

    public static ArrayList<ResEntity> getResCList() {
        intiRes();
        return cList;
    }

    private static synchronized void intiRes() {
        ArrayList<ResEntity> moduleList = new ArrayList<>();
        ArrayList<ResEntity> resList = new ArrayList<>();
        Collection<Object> values = context.getBeansWithAnnotation(ResC.class).values();
        for (Object bean : values) {
            Class<?> aClass = bean.getClass();
            ResC resC = AnnotationUtils.findAnnotation(aClass, ResC.class);
            RequestMapping controllerMapping = AnnotationUtils.findAnnotation(aClass, RequestMapping.class);
            if (resC == null || controllerMapping == null) continue;

            //遍历所有资源
            for (String controllerValue : controllerMapping.value()) {
                //class
                String controllerPath = EnvKit.contextPath() + controllerValue;
                ResEntity moduleEntity = new ResEntity();
                moduleEntity.setName(resC.value());
                moduleEntity.setParentName(resC.parent());
                if (StringUtils.isBlank(resC.parent())) {
                    moduleEntity.setParentName(resC.value());
                }
                moduleEntity.setUrl(controllerPath);
                moduleList.add(moduleEntity);

                //method
                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
                    ResM resMethod = AnnotationUtils.findAnnotation(method, ResM.class);
                    if (requestMapping == null || resMethod == null) continue;

                    //遍历所有资源
                    for (String methodValue : requestMapping.value()) {
                        String methodPath = controllerPath + methodValue;
                        ResEntity resEntity = new ResEntity();
                        resEntity.setUrl(methodPath);
                        resEntity.setName(resMethod.value());
                        resEntity.setParentName(resC.value());
                        resList.add(resEntity);
                    }
                }
            }
        }
        ResourceHandler.cList = moduleList;
        ResourceHandler.mList = resList;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        getAllList();
        getResMList();


        System.out.println("  ");
        System.out.println(JSON.toJSONString(cList));
        System.out.println("  ");
        System.out.println(JSON.toJSONString(mList));
    }
}
