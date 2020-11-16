package com.sting.security.rbac;

/**
 * 配置相关默认实现·
 */
public class SecurityConfigDefaultImpl implements SecurityConfig {

    @Override
    public String systemSecret() {
        return null;
    }

    @Override
    public String loginErrorInputMaxCount() {
        return null;
    }
}
