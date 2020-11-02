package com.sting.security.rbac.resource;

import java.lang.annotation.*;

/**
 * 资源注解
 *
 * @author WangYongJi
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResM {
    //资源名
    String value() default "";
}