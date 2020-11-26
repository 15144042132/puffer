package com.sting.test.db.entity;

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
 * 字典表 实体
 * </p>
 *
 * @author WangYongJi
 * @date 2020-01-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dict")
public class SysDict extends Model<SysDict> implements StEntity {

    public static final String TABLE_NAME = "sys_dict";

    private static final long serialVersionUID = 1L;


    /**
     * PK
     */
    @TableId("id")
    private String id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 键
     */
    @TableField("code")
    private String code;

    /**
     * 值
     */
    @TableField("value")
    private String value;

    /**
     * 排序（默认=1）
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 描述
     */
    @TableField("des")
    private String des;

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
     * 版本号
     */
    @TableField("version")
    private String version;


    /**
     * PK
     */
    public static final String ID = "id";
    public static final String ID_TF = "id";
    /**
     * 名称
     */
    public static final String NAME = "name";
    public static final String NAME_TF = "name";
    /**
     * 键
     */
    public static final String CODE = "code";
    public static final String CODE_TF = "code";
    /**
     * 值
     */
    public static final String VALUE = "value";
    public static final String VALUE_TF = "value";
    /**
     * 排序（默认=1）
     */
    public static final String SORT = "sort";
    public static final String SORT_TF = "sort";
    /**
     * 描述
     */
    public static final String DES = "des";
    public static final String DES_TF = "des";
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
