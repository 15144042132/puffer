package com.sting.security.rbac.annotation;

import java.lang.annotation.*;

/**
 * 资源注解
 * 声明当前路由为资源，项目启动时扫描并更新到数据库中
 *
 * @author WangYongJi
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Res {
    //资源名
    String value() default "";

    //描述信息
    String desc() default "";
}