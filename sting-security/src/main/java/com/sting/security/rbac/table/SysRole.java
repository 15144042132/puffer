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
 * <p>
 * 角色表 实体
 * </p>
 *
 * @author WangYongJi
 * @date 2020-01-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class SysRole extends Model<SysRole> implements StEntity {
    public static final String TABLE_NAME = "sys_role";

    private static final long serialVersionUID = 1L;

    /*PK*/
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /*角色名*/
    @TableField("name")
    private String name;

    /*识别Key*/
    @TableField("role_key")
    private String roleKey;

}
