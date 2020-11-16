package com.sting.security.rbac;

/**
 * 配置接口
 * 与数据库配置对应
 * 提供默认实现类 SecurityConfigDefaultImpl.class
 * 实现接口即可覆盖
 */
public interface SecurityConfig {
    /**
     * 系统秘钥
     */
    String systemSecret();

    /**
     * 登录时，允许错误输入密码最大次数
     */
    String loginErrorInputMaxCount();

}
