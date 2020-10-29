package com.sting.security.rbac.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class PuffSecurityConfig {
    public PuffSecurity puffSecurity = new PuffSecurity();

    public PuffSecurityConfig() {
        log.info("PuffSecurityConfig");
        security(puffSecurity);
    }

    public  abstract void security(PuffSecurity puffSecurity);

}
