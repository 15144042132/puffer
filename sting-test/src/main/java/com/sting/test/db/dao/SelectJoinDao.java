package com.sting.test.db.dao;

import com.sting.db.wrapper.StWrapper;
import com.sting.test.db.entity.SysRole;
import com.sting.test.db.entity.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.io.Serializable;
import java.util.List;

/**
 * 多表联查复杂Dao
 */
@Mapper
public interface SelectJoinDao {

    /**
     * 关联查询
     * 对返回的结果进行级联对象映射复制
     */
    @Select(" SELECT sys_user.id,sys_role.name as roleName FROM sys_user " +
            " LEFT JOIN sys_link_role_user ON sys_user.id=sys_link_role_user.user_id " +
            " LEFT JOIN sys_role ON sys_link_role_user.role_id=sys_role.id " +
            " where sys_user.id = 137")
    @Results({@Result(property = "sysRole.name", column = "roleName")})
    List<SysUser> complexJoin5_2(@Param("ew") StWrapper wrapper);


    /**
     * 查询所有用户，
     * 1.关联查询用户的角色信息
     * 2.关联查询用户的全部角色集合
     */
    @Select(" SELECT sys_user.id,sys_user.name, sys_link_role_user.role_id " +
            " FROM sys_user,sys_link_role_user " +
            " where sys_user.id = 137 and sys_user.id = sys_link_role_user.user_id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "sysRole", column = "role_id",
                    one = @One(select = "getRole", fetchType = FetchType.EAGER)),
            @Result(property = "roleIds", column = "id",
                    many = @Many(select = "getUserRoleIds", fetchType = FetchType.EAGER))
    })
    List<SysUser> complexJoin5_3();


    @Select("SELECT sys_role.id,sys_role.name from sys_role where id=#{id}")
    SysRole getRole(@Param("id") Serializable id);

    @Select("SELECT role_id from sys_link_role_user where user_id=#{id}")
    List<String> getUserRoleIds(@Param("id") Serializable id);

}
