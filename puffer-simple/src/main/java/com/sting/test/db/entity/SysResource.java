package com.sting.test.db.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sting.db.entity.StEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 资源表 实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_resource")
public class SysResource extends Model<SysResource> implements StEntity {
    public static final String TABLE_NAME = "sys_resource";

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String parentName;


    /**
     * PK
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * pid
     */
    @TableField("pid")
    private String pid;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 资源地址
     */
    @TableField("url")
    private String url;

    /**
     * 排序(默认=100)
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 数据创建者
     */
    @TableField(value = "create_uid", fill = FieldFill.INSERT)
    private String createUid;

    /**
     * 数据创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 数据修改者
     */
    @TableField(value = "update_uid", fill = FieldFill.INSERT_UPDATE)
    private String updateUid;

    /**
     * 数据更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否可用(1=可用 , 2=不可用)
     */
    @TableField("is_use")
    private String isUse;

    /**
     * 逻辑删除(1=已删除 , 2=未删除)
     */
    @TableField("is_delete")
    private String isDelete;

}
