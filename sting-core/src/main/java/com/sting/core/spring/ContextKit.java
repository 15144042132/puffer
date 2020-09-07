package com.sting.core.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 容器对象工具
 * <p>
 * 1.通过实例变量名获取实例
 * 2.通过class获取单个Bean实例
 * 3.通过name,以及Clazz返回指定的Bean
 * 4.是否存在实例
 * 5.是否为单例
 * 6.读取配置文件中的属性值
 */
@Slf4j
@Order(1)
@Component
@Configuration
public class ContextKit implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getContext() throws BeansException {
        return ContextKit.applicationContext;
    }


    /**
     * 1.通过实例变量名获取实例
     *
     * @param beanName 实例变量名
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    /**
     * 2.通过class获取单个Bean实例
     *
     * @param tClass 类型
     */
    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    /**
     * 3.通过name,以及Clazz返回指定的Bean
     *
     * @param beanName 实例变量名
     * @param tClass   类型
     */
    public static <T> T getBean(String beanName, Class<T> tClass) {
        return applicationContext.getBean(beanName, tClass);
    }

    /**
     * 4.是否存在实例
     *
     * @param beanName 实例变量名
     */
    public static boolean contains(String beanName) {
        return applicationContext.containsBean(beanName);
    }

    /**
     * 5.是否为单例
     *
     * @param beanName 实例变量名
     */
    public static boolean isSingle(String beanName) {
        return applicationContext.isSingleton(beanName);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ContextKit.applicationContext = applicationContext;
    }

}
