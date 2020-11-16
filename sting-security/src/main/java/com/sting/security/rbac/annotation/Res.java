package com.sting.security.rbac.annotation;

import java.lang.annotation.*;

/**
 * 资源注解
 * 声明当前类为资源，项目启动时，会被扫描更新到数据库中
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