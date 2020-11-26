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
