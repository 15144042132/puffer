package com.sting.security.rbac;

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
 * 安全配置类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_security_config")
public class StConfig extends Model<StConfig> implements StEntity {
    public static final String TABLE_NAME = "sys_security_config";

    private static final long serialVersionUID = 1L;

    /*PK*/
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /*配置名称*/
    @TableField("name")
    private String name;

    /*键*/
    @TableField("code")
    private String code;

    /*值*/
    @TableField("value")
    private String value;

    /*描述*/
    @TableField("des")
    private String des;

    /*排序(默认=100)*/
    @TableField("sort")
    private String sort;

}
