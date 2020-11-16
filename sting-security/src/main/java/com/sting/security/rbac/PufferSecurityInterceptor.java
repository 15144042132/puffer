package com.sting.security.rbac;

import lombok.extern.slf4j.Slf4j;
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
public class PufferSecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object object) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "authorization,Authorization,Token,token,Auth,DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type");
        //response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        if (request.getMethod().equals("OPTIONS")) return true;

        //检查权限
        return hasResource(request, object);
    }

    //校验权限
    private boolean hasResource(HttpServletRequest request, Object object) throws Exception {
        boolean isHandlerMethod = object instanceof HandlerMethod;
        if (isHandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) object;
            String requestURI = request.getRequestURI();
            //路由符合放行条件
            if (requestURI.contains("/public/") || requestURI.contains("static/") || requestURI.contains("/static/")) {
                log.info("{} 路由符合放行条件，直接放行", requestURI);
                return true;
            }

            //报错了
            if (requestURI.equals("/error")) {
                return true;
            }

            //权限校验

            //检查通过，放行
            return true;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
                                Exception exception) {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
                           ModelAndView modelAndView) {
    }


}
