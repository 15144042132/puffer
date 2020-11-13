package com.sting.security.rbac;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 权限相关属性
 * 在配置文件中实现自定义配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "puffer.security")
@EnableConfigurationProperties(PufferSecurityProperties.class)
public class PufferSecurityProperties {

    /**
     * 生成Token时的加密字符串
     */
    private String secret;

    /**
     * 是否在项目启动时，运行建表语句
     */
    private Boolean createTable = true;


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
