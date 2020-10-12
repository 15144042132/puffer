package com.sting.test.db;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sting.db.entity.StEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 菜单表 实体
 * </p>
 *
 * @author WangYongJi
 * @date 2020-01-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> implements StEntity {

    public static final String TABLE_NAME = "sys_menu";

    private static final long serialVersionUID = 1L;

    /**
     * PK
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 父菜单ID
     */
    @TableField("pid")
    private String pid;

    /**
     * 访问路径
     */
    @TableField("path")
    private String path;

    /**
     * 打开新的路由，是否添加TAG，默认添加（1=添加，2=不添加）
     */
    @TableField("add_tag")
    private String addTag;

    /**
     * 重定向地址
     */
    @TableField("redirect")
    private String redirect;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 组件名
     */
    @TableField("name")
    private String name;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 展示状态（1=展示，2=隐藏）
     */
    @TableField("hidden")
    private String hidden;

    /**
     * 关闭状态（1=可以关闭，2=不可以关闭）
     */
    @TableField("always_show")
    private String alwaysShow;

    /**
     * 标签状态（1=可以关闭，2=不可以关闭）
     */
    @TableField("affix")
    private String affix;

    /**
     * 如果设置了路径，则侧边栏将突出显示您设置的路径
     */
    @TableField("active_menu")
    private String activeMenu;

    /**
     * 面包屑状态（1=展示，2=隐藏）
     */
    @TableField("breadcrumb")
    private String breadcrumb;

    /**
     * 缓存（1=开启，2=关闭）
     */
    @TableField("no_cache")
    private String noCache;

    /**
     * 预留
     */
    @TableField("parent_path")
    private String parentPath;

    /**
     * 预留
     */
    @TableField("button_param")
    private String buttonParam;

    /**
     * 排序(默认=1)
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
     * 数据状态(默认=0，删除=DELETED)
     */
    @TableField("data_states")
    private String dataStates;
}
