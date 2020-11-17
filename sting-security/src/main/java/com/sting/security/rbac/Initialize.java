package com.sting.security.rbac;

import com.sting.security.rbac.handler.ConfigHandler;
import com.sting.security.rbac.handler.ResHandler;
import com.sting.security.rbac.handler.TableHandler;
import com.sting.security.rbac.interceptor.PufferSecurityInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 启动类
 */
@Slf4j
@Configuration("PufferSecurityInitialize")
public class Initialize implements WebMvcConfigurer {

    public Initialize(TableHandler tableHandler, ConfigHandler configHandler, ResHandler resHandler) {
        //检查并建表
        tableHandler.checkAndCreateTable();
        //检查并初始化配置
        configHandler.checkAndInitConfig();
        //扫描资源，更新数据库
        resHandler.scanResource();
        resHandler.refreshDbResource();

        //
    }

    //全局拦截器
    @Resource
    private PufferSecurityInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 结合实体店
         * 为实体店引流
         * 抓取大数据
         * 推广营销
         * app
         *
         * 研发出一款 app 小程序 结合实体店的新零售模式，
         * 有效的为实体店引流、拓客、锁客、抓取大数据，
         * 有针对性的对准客户进行推广营销，有效的为实体店提高营业额、增加收入。
         * 一款几乎适用于任何实体店铺的app小程序，能够有效的提升实体店营业额的工具
         * 线上线下相结合，新零售模式为客户建立私域流量池，从而引发销售裂变，增加销售额
         *
         * 服务器：一年 3000
         * 业务原型：1个人 -- 1个月 -- 5000
         * 页面+设计：1个人 -- 2个月 -- 1万
         * 功能开发：2个人--3个月 -- 2万
         * 公司载体：无法估价
         * 上下浮动：至少2万
         *
         */

        log.info("addInterceptors");
        registry
                .addInterceptor(authInterceptor)
                .addPathPatterns("/**");
    }

    @Bean
    @ConditionalOnMissingBean(SecurityConfig.class)
    public SecurityConfig SecurityConfig() {
        return new SecurityConfigDefaultImpl();
    }
}
