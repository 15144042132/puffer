package com.sting.security.rbac;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 权限相关属性
 * 在配置文件中实现自定义配置
 */
@Configuration()
@ConfigurationProperties(prefix = "puffer.security")
@EnableConfigurationProperties(PufferSecurityProperties.class)
public class PufferSecurityProperties {
    //秘钥
    String aaa;


//    private final Test test = new Test();
//
//    public static class Test {
//        String loginPage = "默认值";
//
//        public String getLoginPage() {
//            return loginPage;
//        }
//
//        public void setLoginPage(String loginPage) {
//            this.loginPage = loginPage;
//        }
//    }

}
