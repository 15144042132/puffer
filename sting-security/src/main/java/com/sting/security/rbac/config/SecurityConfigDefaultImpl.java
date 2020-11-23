package com.sting.security.rbac.config;

import com.sting.db.dao.StDao;
import com.sting.db.wrapper.StWrapper;
import com.sting.security.rbac.entity.StConfig;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 配置相关默认实现
 */
public class SecurityConfigDefaultImpl implements SecurityConfig {

    @Autowired
    private StDao stDao;

    @Override
    public StConfig systemSecret() {
        return getStConfig("system_secret");
    }

    @Override
    public StConfig loginErrorInputMaxCount() {
        return getStConfig("login_error_input_max_count");
    }

    @Override
    public StConfig initStatus() {
        return getStConfig("init_status");

    }

    @Override
    public StConfig rootAccountIsCreate() {
        return null;
    }

    @Override
    public StConfig accessControlAllowOrigin() {
        return getStConfig("access_control_allow_origin");
    }

    @Override
    public StConfig accessControlAllowHeaders() {
        return getStConfig("access_control_allow_headers");
    }

    @Override
    public StConfig accessControlAllowMethods() {
        return getStConfig("access_control_allow_methods");
    }

    @Override
    public StConfig accessControlMaxAge() {
        return getStConfig("access_control_max_age");
    }

    @Override
    public StConfig accessControlAllowCredentials() {
        return getStConfig("access_control_allow_credentials");
    }

    @Override
    public StConfig publicUrl() {
        return getStConfig("public_url");
    }

    @Override
    public StConfig rbacIsOpen() {
        return getStConfig("rbac_is_open");
    }

    private StConfig getStConfig(String code) {
        return stDao.selectOne(new StWrapper<>(StConfig.class).eq("code", code));
    }
}
