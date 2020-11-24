package com.sting.security.rbac.config;

import com.sting.db.dao.StDao;
import com.sting.db.wrapper.StWrapper;
import com.sting.security.rbac.entity.StSysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * 配置相关默认实现
 */
@CacheConfig(cacheNames = "puffer:security:config")
public class SecurityConfigDefaultImpl implements SecurityConfig {

    @Autowired
    private StDao stDao;

    @Cacheable(key = "'security_system_secret'")
    @Override
    public StSysConfig systemSecret() {
        return getSysConfig("security_system_secret");
    }

    @Cacheable(key = "'security_login_error_input_max_count'")
    @Override
    public StSysConfig loginErrorInputMaxCount() {
        return getSysConfig("security_login_error_input_max_count");
    }

    @Cacheable(key = "'init_status'")
    @Override
    public StSysConfig initStatus() {
        return getSysConfig("init_status");
    }

    @Cacheable(key = "'security_root_account_is_create'")
    @Override
    public StSysConfig rootAccountIsCreate() {
        return getSysConfig("security_root_account_is_create");
    }

    @Cacheable(key = "'security_access_control_allow_origin'")
    @Override
    public StSysConfig accessControlAllowOrigin() {
        return getSysConfig("security_access_control_allow_origin");
    }

    @Cacheable(key = "'security_access_control_allow_headers'")
    @Override
    public StSysConfig accessControlAllowHeaders() {
        return getSysConfig("security_access_control_allow_headers");
    }

    @Cacheable(key = "'security_access_control_allow_methods'")
    @Override
    public StSysConfig accessControlAllowMethods() {
        return getSysConfig("security_access_control_allow_methods");
    }

    @Cacheable(key = "'security_access_control_max_age'")
    @Override
    public StSysConfig accessControlMaxAge() {
        return getSysConfig("security_access_control_max_age");
    }

    @Cacheable(key = "'security_access_control_allow_credentials'")
    @Override
    public StSysConfig accessControlAllowCredentials() {
        return getSysConfig("security_access_control_allow_credentials");
    }

    @Cacheable(key = "'security_public_url'")
    @Override
    public StSysConfig publicUrl() {
        return getSysConfig("security_public_url");
    }

    @Cacheable(key = "'security_rbac_is_open'")
    @Override
    public StSysConfig rbacIsOpen() {
        return getSysConfig("security_rbac_is_open");
    }

    @CachePut(key = "'security_system_secret'")
    @Override
    public StSysConfig systemSecret(String value) {
        return setSysConfig("security_system_secret", value);
    }

    @CachePut(key = "'security_login_error_input_max_count'")
    @Override
    public StSysConfig loginErrorInputMaxCount(String value) {
        return setSysConfig("security_login_error_input_max_count", value);
    }

    @CachePut(key = "'init_status'")
    @Override
    public StSysConfig initStatus(String value) {
        return setSysConfig("init_status", value);
    }

    @CachePut(key = "'security_root_account_is_create'")
    @Override
    public StSysConfig rootAccountIsCreate(String value) {
        return setSysConfig("security_root_account_is_create", value);
    }

    @CachePut(key = "'security_access_control_allow_origin'")
    @Override
    public StSysConfig accessControlAllowOrigin(String value) {
        return setSysConfig("security_access_control_allow_origin", value);
    }

    @CachePut(key = "'security_access_control_allow_headers'")
    @Override
    public StSysConfig accessControlAllowHeaders(String value) {
        return setSysConfig("security_access_control_allow_headers", value);
    }

    @CachePut(key = "'security_access_control_allow_methods'")
    @Override
    public StSysConfig accessControlAllowMethods(String value) {
        return setSysConfig("security_access_control_allow_methods", value);
    }

    @CachePut(key = "'security_access_control_max_age'")
    @Override
    public StSysConfig accessControlMaxAge(String value) {
        return setSysConfig("security_access_control_max_age", value);
    }

    @CachePut(key = "'security_access_control_allow_credentials'")
    @Override
    public StSysConfig accessControlAllowCredentials(String value) {
        return setSysConfig("security_access_control_allow_credentials", value);
    }

    @CachePut(key = "'security_public_url'")
    @Override
    public StSysConfig publicUrl(String value) {
        return setSysConfig("security_public_url", value);
    }

    @CachePut(key = "'security_rbac_is_open'")
    @Override
    public StSysConfig rbacIsOpen(String value) {
        return setSysConfig("security_rbac_is_open", value);
    }

    public StSysConfig getSysConfig(String code) {
        return stDao.selectOne(new StWrapper<>(StSysConfig.class).eq("code", code));
    }

    public StSysConfig setSysConfig(String code, String value) {
        StSysConfig stSysConfig = new StSysConfig();
        stSysConfig.setValue(value);
        stDao.update(stSysConfig, new StWrapper<>(StSysConfig.class).eq("code", code));
        return stDao.selectOne(new StWrapper<>(StSysConfig.class).eq("code", code));
    }
}
