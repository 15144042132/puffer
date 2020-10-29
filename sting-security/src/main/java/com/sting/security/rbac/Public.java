package com.sting.security.rbac;

import java.lang.annotation.*;

/**
 * 放行注解
 *
 * @author 王永吉
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Public {
    //描述信息
    String value() default "";
}