package com.sting.security.rbac.resource;

import java.lang.annotation.*;

/**
 * 资源注解
 *
 * @author WangYongJi
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResC {
    //模块
    String value() default "";

    //模块
    String parent() default "";
}