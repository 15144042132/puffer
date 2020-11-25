package com.puffer.admin.common.mybatis.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sting.db.entity.StEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 关联表（角色-菜单） 实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_link_role_user")
public class SysLinkRoleUser extends Model<SysLinkRoleUser> implements StEntity {

    public static final String TABLE_NAME = "sys_link_role_user";

    private static final long serialVersionUID = 1L;


    @TableId("role_id")
    private String roleId;

    @TableField("user_id")
    private String userId;


    public static final String ROLE_ID = "role_id";
    public static final String ROLE_ID_TF = "roleId";
    public static final String USER_ID = "user_id";
    public static final String USER_ID_TF = "userId";

    /**
     * 返回当前对象的JSON字符串
     */
    public String jsonStr() {
        return JSON.toJSONString(this);
    }
}
