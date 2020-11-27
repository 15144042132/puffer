package com.sting.security;

import com.sting.security.rbac.Initialization;
import com.sting.security.rbac.config.SecurityConfig;
import com.sting.security.rbac.config.SecurityConfigDefaultImpl;
import com.sting.security.rbac.handler.ConfigHandler;
import com.sting.security.rbac.handler.ResHandler;
import com.sting.security.rbac.handler.RootRoleHandler;
import com.sting.security.rbac.handler.TableHandler;
import com.sting.security.rbac.service.SecurityService;
import com.sting.security.rbac.service.SecurityServiceDefaultImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
@ComponentScan("com.sting.security")
public class StingSecurityAutoConfiguration {

    /**
     * 默认配置实现，返回数据库中的安全配置项
     */
    @Bean
    public Initialization initialization(TableHandler tableHandler, ConfigHandler configHandler, ResHandler resHandler, RootRoleHandler accountHandler) {
        return new Initialization(tableHandler, configHandler, resHandler, accountHandler);
    }

    /**
     * 默认配置实现，返回数据库中的安全配置项
     */
    @Bean
    @ConditionalOnMissingBean(SecurityConfig.class)
    public SecurityConfig securityConfig() {
        return new SecurityConfigDefaultImpl();
    }

    /**
     * 默认安全服务实现，实现权限相关功能
     */
    @Bean
    @ConditionalOnMissingBean(SecurityService.class)
    public SecurityService securityService() {
        return new SecurityServiceDefaultImpl();
    }

}
