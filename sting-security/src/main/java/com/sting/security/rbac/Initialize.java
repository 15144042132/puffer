package com.sting.security.rbac;

import com.sting.security.rbac.config.SecurityConfig;
import com.sting.security.rbac.config.SecurityConfigDefaultImpl;
import com.sting.security.rbac.handler.ConfigHandler;
import com.sting.security.rbac.handler.ResHandler;
import com.sting.security.rbac.handler.RootRoleHandler;
import com.sting.security.rbac.handler.TableHandler;
import com.sting.security.rbac.interceptor.AuthorityInterceptor;
import com.sting.security.rbac.interceptor.OriginInterceptor;
import com.sting.security.rbac.service.SecurityService;
import com.sting.security.rbac.service.SecurityServiceDefaultImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 启动类
 */
@Slf4j
@Configuration("PufferSecurityInitialize")
public class Initialize implements WebMvcConfigurer {

    public Initialize(TableHandler tableHandler, ConfigHandler configHandler, ResHandler resHandler, RootRoleHandler accountHandler) {
        //建表
        tableHandler.checkAndCreateTable();

        //初始化配置
        configHandler.checkAndInitConfig();

        //扫描资源
        resHandler.scanResource();

        //更新资源
        resHandler.refreshDbResource();

        //创建ROOT角色
        accountHandler.checkAndCreateRoot();

    }


    //全局拦截器
    @Autowired
    private OriginInterceptor originInterceptor;
    @Autowired
    private AuthorityInterceptor authorityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("addInterceptors");
        registry
                .addInterceptor(originInterceptor)
                .addPathPatterns("/**");
        registry
                .addInterceptor(authorityInterceptor)
                .addPathPatterns("/**");
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
     * 默认安全服务实现，
     * 实现权限相关功能
     */
    @Bean
    @ConditionalOnMissingBean(SecurityService.class)
    public SecurityService securityService() {
        return new SecurityServiceDefaultImpl();
    }
}
