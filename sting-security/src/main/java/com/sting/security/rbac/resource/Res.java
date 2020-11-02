package com.sting.security.rbac.resource;

import java.lang.annotation.*;

/**
 * 资源注解
 *
 * @author WangYongJi
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Res {
    //模块
    String value() default "";

}