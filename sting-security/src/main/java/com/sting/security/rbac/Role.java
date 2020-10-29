package com.sting.security.rbac;

import java.lang.annotation.*;

/**
 * 权限注解
 *
 * @author 王永吉
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Role {
    //权限数组
    String[] value() default {};

    //描述信息
    String desc() default "";
}