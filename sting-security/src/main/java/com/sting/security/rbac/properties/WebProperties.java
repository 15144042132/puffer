package com.sting.security.rbac.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "puffer.security.web")
@EnableConfigurationProperties(WebProperties.class)
public class WebProperties {
    private String loginPage = "默认值";
}
