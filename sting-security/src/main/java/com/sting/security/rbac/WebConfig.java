package com.sting.security.rbac;

import com.sting.security.rbac.authority.Auth;
import com.sting.security.rbac.config.PuffSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
    //全局拦截器
    @Resource
    private Auth authInterceptor;
    @Autowired
    private PuffSecurityConfig puffSecurityConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("addInterceptors");
        registry
                .addInterceptor(authInterceptor)
                .addPathPatterns("/**");
    }

}
