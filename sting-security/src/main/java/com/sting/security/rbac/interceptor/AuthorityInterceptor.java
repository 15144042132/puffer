package com.sting.security.rbac.interceptor;

import com.sting.core.project.StException;
import com.sting.core.project.StMsg;
import com.sting.security.rbac.JwtKit;
import com.sting.security.rbac.config.SecurityConfig;
import com.sting.security.rbac.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 权限认证
 *
 * @author WangYongJi
 */
@Slf4j
@Component
public class AuthorityInterceptor implements HandlerInterceptor {
    @Autowired
    private SecurityConfig config;
    @Autowired
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object object) throws Exception {
        log.info("AuthorityInterceptor---preHandle");

        //开关检查
        if (config.rbacIsOpen().getValue().equals("false")) {
            return true;
        }

        //OPTIONS 请求直接放行
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        if (object instanceof HandlerMethod) {
            //放行检查
            if (isPublic(request.getRequestURI())) {
                return true;
            }

            //token检查
            String token = request.getHeader("token");
            if (!JwtKit.check(token)) {
                log.info("请求地址：{} 身份认证失败，Token: {} ，原因：Token不存在或已失效  ", request.getRequestURI(), token);
                throw new StException(StMsg.ERROR_401);
            }

            //权限检查
            String userId = JwtKit.getUserId(token);
            List<String> userResourceUrls = securityService.userResourceUrls(userId);
            if (!userResourceUrls.contains(request.getRequestURI())) {
                log.info("请求地址：{} 请求失败，userId: {} ，原因：该用户权限不足  ", request.getRequestURI(), userId);
                throw new StException(StMsg.ERROR_402);
            }

        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
                                Exception exception) {
        log.info("AuthorityInterceptor--afterCompletion");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
                           ModelAndView modelAndView) {
        log.info("AuthorityInterceptor--postHandle");
    }

    private boolean isPublic(String requestURI) {
        for (String url : config.publicUrl().getValue().split(",")) {
            if (requestURI.contains(url)) {
                return true;
            }
        }
        return false;
    }
}

