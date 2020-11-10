package com.sting.security.rbac.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sting.db.entity.StEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户表 实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class StUser extends Model<StUser> implements StEntity {
    public static final String TABLE_NAME = "sys_user";

    private static final long serialVersionUID = 1L;

    /*PK*/
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /*账号*/
    @TableField("account")
    private String account;

    /*密码*/
    @TableField("password")
    private String password;

    /*手机号*/
    @TableField("phone")
    private String phone;

    /*邮箱*/
    @TableField("email")
    private String email;

    /*状态*/
    @TableField("status")
    private String status;


}
