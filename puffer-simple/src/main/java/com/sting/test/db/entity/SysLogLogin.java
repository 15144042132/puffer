package com.sting.test.db.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * 登录日志 实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_log_login")
public class SysLogLogin extends Model<SysLogLogin> implements StEntity {

    public static final String TABLE_NAME = "sys_log_login";

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 原文件名
     */
    @TableField("user_id")
    private String userId;

    /**
     * 时间
     */
    @TableField("time")
    private LocalDateTime time;

    /**
     * IP
     */
    @TableField("ip")
    private String ip;

    /**
     * 返回当前对象的JSON字符串
     */
    public String jsonStr() {
        return JSON.toJSONString(this);
    }
}
