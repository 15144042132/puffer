package com.sting.security.rbac;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sting.db.entity.StEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 关联表（角色-资源） 实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_link_role_resource")
public class StLinkRoleResource extends Model<StLinkRoleResource> implements StEntity {

    public static final String TABLE_NAME = "sys_link_role_resource";

    private static final long serialVersionUID = 1L;

    @TableId("role_id")
    private String roleId;

    @TableField("resource_id")
    private String resourceId;


}
