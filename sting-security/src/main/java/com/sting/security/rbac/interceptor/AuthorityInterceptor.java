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
 * 权限认证
 *
 * @author WangYongJi
 */
@Slf4j
@Component
public class AuthorityInterceptor implements HandlerInterceptor {
    @Autowired
    private SecurityConfig config;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object object) throws Exception {
        log.info("AuthorityInterceptor---preHandle");

        //OPTIONS 请求直接放行
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        if (object instanceof HandlerMethod) {

            //此路径不需要检查
            if (isPublic(request.getRequestURI())) {
                return true;
            }

            //权限校验
            String token = request.getHeader("token");
            if (!JwtKit.check(token)) {
                log.info("{} 身份认证失败，Token不存在或已失效  Token: {}", request.getRequestURI(), token);
                throw new StException(StMsg.ERROR_401);
            }
            //获取用户ID
            String userId = JwtKit.getUserId(token);
            //获取用户角色
            //获取角色资源
            //匹配当前资源


            return;
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

