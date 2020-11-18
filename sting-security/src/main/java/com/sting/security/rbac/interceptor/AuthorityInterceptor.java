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

        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        boolean isHandlerMethod = object instanceof HandlerMethod;

        if (isHandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) object;
            String requestURI = request.getRequestURI();

            //路由符合放行条件
            if (requestURI.contains("/public/") || requestURI.contains("static/") || requestURI.contains("/static/") || requestURI.equals("/error")) {
                log.info("{}  直接放行", requestURI);
                return true;
            }

            //权限校验
            String token = request.getHeader("token");
            if (!JwtKit.check(token)) {
                log.info("{} 身份认证失败，Token不存在或已失效  Token: {}", requestURI, token);
                throw new StException(StMsg.ERROR_401);
            }

            //权限不足
            String userId = JwtKit.getUserId(token);

//            if (!resourceUrls.contains(requestURI)) {
//                log.info("请求被截拦:{} , 原因:{} , 访问者ID:{} , 访问者账号:{}", requestURI, "权限不足", userId, userAccount);
//                throw new MiException(ResMsg.ERROR_402);
//            }


            return true;
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


}
