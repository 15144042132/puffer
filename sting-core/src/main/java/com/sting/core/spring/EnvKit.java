package com.sting.core.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

/**
 * 环境变量
 */
@Order(1)
@Slf4j
@Configuration
public class EnvKit implements EnvironmentAware {
    private static Environment env = null;

    /**
     * 读取配置文件中的属性值
     *
     * @param propertyKey 属性Key
     */
    public static String getProperty(String propertyKey) {
        return EnvKit.env.getProperty(propertyKey);
    }


    /**
     * 秘钥
     * 默认：sdhiufiwehgasoibjiyewgsgcbqo2335pqkshfuiw1h23iuh1i23hi3
     */
    public static String authSecret() {
        String property = getProperty("mi.auth.secret");
        return property == null ? "sdhiufiwehgasoibjiyewgsgcbqo2335pqkshfuiw1h23iuh1i23hi3" : property;
    }

    /**
     * 显示请求日志
     * 默认 false
     */
    public static boolean showRequestLog() {
        String property = EnvKit.env.getProperty("mi.log.show-request-log");
        return "true".equals(property);
    }

    /**
     * 存储日志到数据库
     * 默认 false
     */
    public static boolean isSaveLog() {
        String property = EnvKit.env.getProperty("mi.log.save-log");
        return "true".equals(property);
    }

    /**
     * 上下文路径
     * 默认 ""
     */
    public static String contextPath() {
        String property = EnvKit.env.getProperty("server.servlet.context-path");
        return property == null ? "" : property;
    }

    @Override
    public void setEnvironment(Environment environment) {
        EnvKit.env = environment;
    }


}
