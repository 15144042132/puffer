package com.sting.security.rbac.annotation;

import java.lang.annotation.*;

/**
 * RoleExclude -- 排除注解
 * 作用于方法，声明当前资源数，不属于哪些角色
 * <p>
 * Role的权限 小于RoleExclude
 *
 * @author WangYongJi
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleExclude {
    //需要排除当前资源的角色数组
    String[] value() default {};

    //描述信息
    String desc() default "";
}