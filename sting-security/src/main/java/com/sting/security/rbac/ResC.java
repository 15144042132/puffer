package com.sting.security.rbac;

import java.lang.annotation.*;

/**
 * 资源注解
 *
 * @author 王永吉
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