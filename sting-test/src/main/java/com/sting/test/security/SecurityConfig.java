package com.sting.test.security;

import com.sting.security.rbac.config.PuffSecurity;
import com.sting.security.rbac.config.PuffSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Slf4j
@Configuration
public class SecurityConfig extends PuffSecurityConfig {
    @Override
    public void security(PuffSecurity security) {
        security
                .addPublic("")
                .addRequired("", "")
                .addRequired("", Arrays.asList("", "", ""));
    }
}
