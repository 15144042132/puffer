package com.sting.security.rbac.config;

import com.sting.db.dao.StDao;
import com.sting.db.wrapper.StWrapper;
import com.sting.security.rbac.entity.StConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * 配置相关默认实现
 */
@CacheConfig(cacheNames = "puffer:security:config")
public class SecurityConfigDefaultImpl implements SecurityConfig {

    @Autowired
    private StDao stDao;

    @Cacheable(key = "#root.methodName")
    @Override
    public StConfig systemSecret() {
        return getStConfig("system_secret");
    }

    @Cacheable(key = "#root.methodName")
    @Override
    public StConfig loginErrorInputMaxCount() {
        return getStConfig("login_error_input_max_count");
    }

    @Cacheable(key = "#root.methodName")
    @Override
    public StConfig initStatus() {
        return getStConfig("init_status");
    }

    @Cacheable(key = "#root.methodName")
    @Override
    public StConfig rootAccountIsCreate() {
        return getStConfig("root_account_is_create");
    }

    @Cacheable(key = "#root.methodName")
    @Override
    public StConfig accessControlAllowOrigin() {
        return getStConfig("access_control_allow_origin");
    }

    @Cacheable(key = "#root.methodName")
    @Override
    public StConfig accessControlAllowHeaders() {
        return getStConfig("access_control_allow_headers");
    }

    @Cacheable(key = "#root.methodName")
    @Override
    public StConfig accessControlAllowMethods() {
        return getStConfig("access_control_allow_methods");
    }

    @Cacheable(key = "#root.methodName")
    @Override
    public StConfig accessControlMaxAge() {
        return getStConfig("access_control_max_age");
    }

    @Cacheable(key = "#root.methodName")
    @Override
    public StConfig accessControlAllowCredentials() {
        return getStConfig("access_control_allow_credentials");
    }

    @Cacheable(key = "#root.methodName")
    @Override
    public StConfig publicUrl() {
        return getStConfig("public_url");
    }

    @Cacheable(key = "#root.methodName")
    @Override
    public StConfig rbacIsOpen() {
        return getStConfig("rbac_is_open");
    }

    private StConfig getStConfig(String code) {
        return stDao.selectOne(new StWrapper<>(StConfig.class).eq("code", code));
    }
}
