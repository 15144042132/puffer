package com.sting.security.rbac.config;

import com.sting.security.rbac.entity.StConfig;

/**
 * 配置项，与数据库配置对应
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
     * root账号，项目首次启动后，必须创建root角色
     */
    StConfig rootAccountIsCreate();

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

    /**
     * 不需要检查的路径，支持
     */
    StConfig publicUrl();

    /**
     * 权限检是否开启
     */
    StConfig rbacIsOpen();
}
