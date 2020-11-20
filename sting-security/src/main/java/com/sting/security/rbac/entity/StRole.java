package com.sting.security.rbac.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sting.db.entity.StEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 角色
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class StRole extends Model<StRole> implements StEntity {

    public static final String TABLE_NAME = "sys_role";

    private static final long serialVersionUID = 1L;

    /**
     * PK
     */
    @TableId("id")
    private String id;

    /**
     * 角色名
     */
    @TableField("name")
    private String name;

    /**
     * 角色Key
     */
    @TableField("code")
    private String code;

    /**
     * 排序(默认=1)
     */
    @TableField("sort")
    private Integer sort;
}
