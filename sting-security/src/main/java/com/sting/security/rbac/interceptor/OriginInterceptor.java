package com.sting.security.rbac.interceptor;

import com.sting.core.project.StException;
import com.sting.core.project.StMsg;
import com.sting.security.rbac.JwtKit;
import com.sting.security.rbac.config.SecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 请求拦截器
 *
 * @author WangYongJi
 */
@Slf4j
@Component
public class OriginInterceptor implements HandlerInterceptor {
    @Autowired
    private SecurityConfig config;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object object) throws Exception {
        log.info("OriginInterceptor---preHandle");
        response.setHeader("Access-Control-Allow-Origin", config.accessControlAllowOrigin().getValue());
        response.setHeader("Access-Control-Allow-Headers", config.accessControlAllowHeaders().getValue());
        response.setHeader("Access-Control-Allow-Methods", config.accessControlAllowMethods().getValue());
        response.setHeader("Access-Control-Max-Age", config.accessControlMaxAge().getValue());
        response.setHeader("Access-Control-Allow-Credentials", config.accessControlAllowCredentials().getValue());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
                                Exception exception) {
        log.info("OriginInterceptor---afterCompletion");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
                           ModelAndView modelAndView) {
        log.info("OriginInterceptor---postHandle");
    }


}
