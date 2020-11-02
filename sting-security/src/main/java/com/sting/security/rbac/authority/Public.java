package com.sting.security.rbac.authority;

import java.lang.annotation.*;

/**
 * 放行注解
 *
 * @author WangYongJi
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Public {
    String value() default "";
}