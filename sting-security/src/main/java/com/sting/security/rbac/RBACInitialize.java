package com.sting.security.rbac;

import com.sting.security.rbac.authority.Auth;
import com.sting.security.rbac.authority.RoleHandler;
import com.sting.security.rbac.config.PuffSecurityConfig;
import com.sting.security.rbac.resource.ResHandler;
import com.sting.security.rbac.table.TableHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 权限启动类
 */
@Slf4j
@Configuration
public class RBACInitialize implements WebMvcConfigurer {

    public RBACInitialize(TableHandler tableHandler, RoleHandler roleHandler, ResHandler resHandler) {
        //创建数据库表结构
        tableHandler.createTable();

        //扫描资源，更新数据库
        resHandler.scanResource();
        resHandler.refreshDbResource();

        //扫描角色资源，更新数据库
        roleHandler.scanRole();
    }

    //全局拦截器
    @Resource
    private Auth authInterceptor;
    @Autowired
    private PuffSecurityConfig securityConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("addInterceptors");
        registry
                .addInterceptor(authInterceptor)
                .addPathPatterns("/**");
    }

}
