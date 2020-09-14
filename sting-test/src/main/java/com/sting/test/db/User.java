package com.sting.test.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sting.db.entity.StEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements StEntity {
    public static final String TABLE_NAME = "user";

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Object id;
    @TableField("name")
    private String name;
    @TableField("date")
    private LocalDate date;
    @TableField("time")
    private LocalTime time;
    @TableField("date_time")
    private LocalDateTime dateTime;

}
