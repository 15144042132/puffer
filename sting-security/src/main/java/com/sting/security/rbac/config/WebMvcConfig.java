package com.sting.security.rbac.config;

import com.sting.security.rbac.interceptor.AuthorityInterceptor;
import com.sting.security.rbac.interceptor.OriginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //全局拦截器
    @Autowired
    private OriginInterceptor originInterceptor;
    @Autowired
    private AuthorityInterceptor authorityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(originInterceptor)
                .addPathPatterns("/**");
        registry
                .addInterceptor(authorityInterceptor)
                .addPathPatterns("/**");
    }
}
