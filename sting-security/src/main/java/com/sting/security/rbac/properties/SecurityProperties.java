package com.sting.security.rbac.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "puffer.security")
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityProperties {
    private WebProperties web = new WebProperties();
}
