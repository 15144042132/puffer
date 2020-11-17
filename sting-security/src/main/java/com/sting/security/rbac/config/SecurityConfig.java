package com.sting.security.rbac.config;

import com.sting.security.rbac.entity.StConfig;

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
    StConfig systemSecret();

    /**
     * 登录时，允许错误输入密码最大次数
     */
    StConfig loginErrorInputMaxCount();

    /**
     * 初始化状态（1=已完成，2=项目重启后执行初始化）
     */
    StConfig initStatus();

    /**
     * root账号，项目首次启动后，必须修改
     */
    StConfig rootAccount();

    /**
     * root密码，项目首次启动后，必须修改
     */
    StConfig rootPassword();

    /**
     * 请求访问来源控制
     */
    StConfig accessControlAllowOrigin();

    /**
     * 请求头控制
     */
    StConfig accessControlAllowHeaders();

    /**
     * 请求允许方法
     */
    StConfig accessControlAllowMethods();

    /**
     * 请求最大连接时长
     */
    StConfig accessControlMaxAge();

    /**
     * 请求的响应是否允许暴露给页面
     */
    StConfig accessControlAllowCredentials();

}
