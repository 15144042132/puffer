package com.sting.db.entity;

import com.alibaba.fastjson.JSON;
import org.slf4j.LoggerFactory;

/**
 * 泛型检查
 * 实现这个接口,证明自己是实体类
 * <p>
 *
 * @author 王永吉
 */
public interface StEntity {
    /**
     * 返回JSON类型字符串
     */
    default String jsonString() {
        return JSON.toJSONString(this);
    }

    /**
     * 打印实体信息
     */
    default void print() {
        LoggerFactory.getLogger(getClass()).info(JSON.toJSONString(this));
    }

}
