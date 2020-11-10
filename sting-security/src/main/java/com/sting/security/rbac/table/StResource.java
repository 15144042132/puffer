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
 * 资源表 实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_resource")
public class StResource extends Model<StResource> implements StEntity {
    public static final String TABLE_NAME = "sys_resource";

    private static final long serialVersionUID = 1L;


    @TableField(exist = false)
    private String parentName;

    /*PK*/
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /*PK*/
    @TableField("pid")
    private String pid;

    /*资源名称*/
    @TableField("name")
    private String name;

    /*url*/
    @TableField("url")
    private String url;

    /*资源描述*/
    @TableField("details")
    private String details;

}
