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
 * 文件详细信息 实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("file_info")
public class FileInfo extends Model<FileInfo> implements StEntity {

    public static final String TABLE_NAME = "file_info";

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 原文件名
     */
    @TableField("name")
    private String name;

    /**
     * 访问地址
     */
    @TableField("url")
    private String url;

    /**
     * 后缀
     */
    @TableField("suffix")
    private String suffix;

    /**
     * 文件大小(KB)
     */
    @TableField("size")
    private String size;

    /**
     * 文件MD5
     */
    @TableField("md5")
    private String md5;

    /**
     * 文件类型（图片 image; 视频 video; 其他 other）
     */
    @TableField("type")
    private String type;

    /**
     * 冗余字段
     */
    @TableField("path")
    private String path;

    /**
     * 排序
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


    public static final String ID = "id";
    public static final String ID_TF = "id";
    /**
     * 原文件名
     */
    public static final String NAME = "name";
    public static final String NAME_TF = "name";
    /**
     * 访问地址
     */
    public static final String URL = "url";
    public static final String URL_TF = "url";
    /**
     * 后缀
     */
    public static final String SUFFIX = "suffix";
    public static final String SUFFIX_TF = "suffix";
    /**
     * 文件大小(KB)
     */
    public static final String SIZE = "size";
    public static final String SIZE_TF = "size";
    /**
     * 文件MD5
     */
    public static final String MD5 = "md5";
    public static final String MD5_TF = "md5";
    /**
     * 文件类型（图片 image; 视频 video; 其他 other）
     */
    public static final String TYPE = "type";
    public static final String TYPE_TF = "type";
    /**
     * 冗余字段
     */
    public static final String PATH = "path";
    public static final String PATH_TF = "path";
    /**
     * 排序
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
}
