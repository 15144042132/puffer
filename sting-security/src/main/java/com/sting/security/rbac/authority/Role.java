package com.sting.security.rbac.authority;

import java.lang.annotation.*;

/**
 * Role
 * 角色注解--注明当前资源数，属于哪些角色
 * <p>
 * Role 小于 RoleExclude
 *
 * @author WangYongJi
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Role {
    //需要当前资源的角色数据
    String[] value() default {};

    //描述信息
    String desc() default "";
}