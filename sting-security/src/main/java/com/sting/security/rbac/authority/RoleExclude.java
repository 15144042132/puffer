package com.sting.security.rbac.authority;

import java.lang.annotation.*;

/**
 * RoleExclude
 * 排除注解--注明当前资源数，不属于哪些角色
 * <p>
 * Role 小于 RoleExclude
 *
 * @author WangYongJi
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleExclude {
    //需要排除当前资源的角色数组
    String[] value() default {};

    //描述信息
    String desc() default "";
}