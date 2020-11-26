package com.puffer.admin.common.mybatis.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sting.db.entity.StEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户表 实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser extends Model<SysUser> implements StEntity {

    public static final String TABLE_NAME = "sys_user";

    private static final long serialVersionUID = 1L;

    //接收参数
    @TableField(exist = false)
    private List paramRoleIds;

    //角色数组-分页查询使用
    @TableField(exist = false)
    private Object roleIds;

    public String[] getRoleIds() {
        if (roleIds == null) return null;
        return roleIds.toString().split(",");
    }

    //角色数组-分页查询使用
    @TableField(exist = false)
    private Object roleNames;

    public String[] getRoleNames() {
        if (roleNames == null) return null;
        return roleNames.toString().split(",");
    }

    /**
     * PK
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 昵称
     */
    @TableField("name")
    private String name;


    /**
     * 账号
     */
    @TableField("account")
    private String account;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 随机盐
     */
    @TableField("salt")
    private String salt;

    /**
     * 头像
     */
    @TableField("head")
    private String head;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 座机号
     */
    @TableField("telephone")
    private String telephone;

    /**
     * 身份证号
     */
    @TableField("id_card")
    private String idCard;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 家庭住址
     */
    @TableField("address")
    private String address;

    /**
     * 用户状态（1=正常，2=因为违规已停用）
     */
    @TableField("states")
    private String states;

    /**
     * 最后一次登录IP
     */
    @TableField("login_ip")
    private String loginIp;

    /**
     * 最后一次登录时间
     */
    @TableField("login_time")
    private LocalDateTime loginTime;

    /**
     * 刷新Token
     */
    @TableField("refresh_token")
    private String refreshToken;

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
     * 昵称
     */
    public static final String NAME = "name";
    public static final String NAME_TF = "name";
    /**
     * 账号
     */
    public static final String ACCOUNT = "account";
    public static final String ACCOUNT_TF = "account";
    /**
     * 密码
     */
    public static final String PASSWORD = "password";
    public static final String PASSWORD_TF = "password";
    /**
     * 头像
     */
    public static final String HEAD = "head";
    public static final String HEAD_TF = "head";
    /**
     * 手机号
     */
    public static final String PHONE = "phone";
    public static final String PHONE_TF = "phone";
    /**
     * 座机号
     */
    public static final String TELEPHONE = "telephone";
    public static final String TELEPHONE_TF = "telephone";
    /**
     * 身份证号
     */
    public static final String ID_CARD = "id_card";
    public static final String ID_CARD_TF = "idCard";
    /**
     * 邮箱
     */
    public static final String EMAIL = "email";
    public static final String EMAIL_TF = "email";
    /**
     * 家庭住址
     */
    public static final String ADDRESS = "address";
    public static final String ADDRESS_TF = "address";
    /**
     * 用户状态（1=正常，2=因为违规已停用）
     */
    public static final String STATES = "states";
    public static final String STATES_TF = "states";
    /**
     * 最后一次登录IP
     */
    public static final String LOGIN_IP = "login_ip";
    public static final String LOGIN_IP_TF = "loginIp";
    /**
     * 最后一次登录时间
     */
    public static final String LOGIN_TIME = "login_time";
    public static final String LOGIN_TIME_TF = "loginTime";
    /**
     * 刷新Token
     */
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String REFRESH_TOKEN_TF = "refreshToken";
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
}
