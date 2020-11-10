package com.sting.security.rbac.annotation;

import java.lang.annotation.*;

/**
 * 放行注解--声明当前资源不需要权限控制
 *
 * @author WangYongJi
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Public {
    String value() default "";
}