package com.sting.test.db;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sting.db.entity.StEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 角色表 实体
 * </p>
 *
 * @author 王永吉
 * @date 2020-01-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class SysRole extends Model<SysRole> implements StEntity {

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
    @TableField("role_key")
    private String roleKey;

    /**
     * 角色编号
     */
    @TableField("code")
    private String code;

    /**
     * 角色标签数组【管理员，老板，美工】
     */
    @TableField("tags")
    private String tags;

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


    /**
     * PK
     */
    public static final String ID = "id";
    public static final String ID_TF = "id";
    /**
     * 角色名
     */
    public static final String NAME = "name";
    public static final String NAME_TF = "name";
    /**
     * 角色Key
     */
    public static final String ROLE_KEY = "role_key";
    public static final String ROLE_KEY_TF = "roleKey";
    /**
     * 角色编号
     */
    public static final String CODE = "code";
    public static final String CODE_TF = "code";
    /**
     * 角色标签数组【管理员，老板，美工】
     */
    public static final String TAGS = "tags";
    public static final String TAGS_TF = "tags";
    /**
     * 排序(默认=1)
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
     * 数据状态(默认=0，删除=DELETED)
     */
    public static final String DATA_STATES = "data_states";
    public static final String DATA_STATES_TF = "dataStates";

    /**
     * 返回当前对象的JSON字符串
     */
    public String jsonStr() {
        return JSON.toJSONString(this);
    }
}
