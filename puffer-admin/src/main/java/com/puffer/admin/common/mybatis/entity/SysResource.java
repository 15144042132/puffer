package com.puffer.admin.common.mybatis.entity;

import com.alibaba.fastjson.JSON;
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


    /**
     * PK
     */
    public static final String ID = "id";
    public static final String ID_TF = "id";
    /**
     * pid
     */
    public static final String PID = "pid";
    public static final String PID_TF = "pid";
    /**
     * 名称
     */
    public static final String NAME = "name";
    public static final String NAME_TF = "name";
    /**
     * 资源地址
     */
    public static final String URL = "url";
    public static final String URL_TF = "url";
    /**
     * 排序(默认=100)
     */
    public static final String SORT = "sort";
    public static final String SORT_TF = "sort";
    /**
     * 数据创建者
     */
    public static final String CREATE_UID = "create_uid";
    public static final String CREATE_UID_TF = "createUid";
    /**
     * 数据创建时间
     */
    public static final String CREATE_TIME = "create_time";
    public static final String CREATE_TIME_TF = "createTime";
    /**
     * 数据修改者
     */
    public static final String UPDATE_UID = "update_uid";
    public static final String UPDATE_UID_TF = "updateUid";
    /**
     * 数据更新时间
     */
    public static final String UPDATE_TIME = "update_time";
    public static final String UPDATE_TIME_TF = "updateTime";
    /**
     * 是否可用(1=可用 , 2=不可用)
     */
    public static final String IS_USE = "is_use";
    public static final String IS_USE_TF = "isUse";
    /**
     * 逻辑删除(1=已删除 , 2=未删除)
     */
    public static final String IS_DELETE = "is_delete";
    public static final String IS_DELETE_TF = "isDelete";

    /**
     * 返回当前对象的JSON字符串
     */
    public String jsonStr() {
        return JSON.toJSONString(this);
    }


    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SysResource) {
            SysResource resource = (SysResource) obj;
            if (id != null) {
                return id.equals(resource.id);
            }
        }
        return super.equals(obj);
    }


}
