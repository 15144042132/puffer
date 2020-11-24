package com.sting.security.rbac.config;

import com.sting.security.rbac.entity.StSysConfig;

/**
 * 配置项，与数据库配置对应
 */
public interface SecurityConfig {
    /**
     * 系统秘钥
     */
    StSysConfig systemSecret();

    /**
     * 登录时，允许错误输入密码最大次数
     */
    StSysConfig loginErrorInputMaxCount();

    /**
     * 初始化状态（1=已完成，2=项目重启后执行初始化）
     */
    StSysConfig initStatus();

    /**
     * root账号，项目首次启动后，必须创建root角色
     */
    StSysConfig rootAccountIsCreate();

    /**
     * 请求访问来源控制
     */
    StSysConfig accessControlAllowOrigin();

    /**
     * 请求头控制
     */
    StSysConfig accessControlAllowHeaders();

    /**
     * 请求允许方法
     */
    StSysConfig accessControlAllowMethods();

    /**
     * 请求最大连接时长
     */
    StSysConfig accessControlMaxAge();

    /**
     * 请求的响应是否允许暴露给页面
     */
    StSysConfig accessControlAllowCredentials();

    /**
     * 不需要检查的路径，支持
     */
    StSysConfig publicUrl();

    /**
     * 权限检是否开启
     */
    StSysConfig rbacIsOpen();

    StSysConfig systemSecret(String value);

    StSysConfig loginErrorInputMaxCount(String value);

    StSysConfig initStatus(String value);

    StSysConfig rootAccountIsCreate(String value);

    StSysConfig accessControlAllowOrigin(String value);

    StSysConfig accessControlAllowHeaders(String value);

    StSysConfig accessControlAllowMethods(String value);

    StSysConfig accessControlMaxAge(String value);

    StSysConfig accessControlAllowCredentials(String value);

    StSysConfig publicUrl(String value);

    StSysConfig rbacIsOpen(String value);

}
