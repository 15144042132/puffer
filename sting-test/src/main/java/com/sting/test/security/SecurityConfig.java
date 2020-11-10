package com.sting.test.security;

import com.sting.security.rbac.PufferSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Slf4j
@Configuration
public class SecurityConfig extends PufferSecurityConfig {
    @Override
    public void security(PuffSecurity security) {
        security
                .addPublic("")
                .addRequired("", "")
                .addRequired("", Arrays.asList("", "", ""));

    }
}
