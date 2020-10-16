package com.sting.db.wrapper;

import com.sting.db.entity.StEntity;

import java.io.Serializable;

/**
 * 单表 Join 多表
 * join
 * left join
 * right join
 *
 * @author WangYongJi
 * @date 2020/10/12 9:40
 */
public interface StJoin<Children, R> extends Serializable {

    /**
     * 设置 LEFT_JOIN 联查表名
     *
     * @param tableName 表名
     * @return children
     */
    Children leftJoin(String tableName);

    <P extends StEntity> Children leftJoin(Class<P> tClass);

    /**
     * 设置 RIGHT_JOIN 联查表名
     *
     * @param tableName 表名
     * @return children
     */
    Children rightJoin(String tableName);

    <P extends StEntity> Children rightJoin(Class<P> tClass);

    /**
     * 设置 JOIN 联查表名
     *
     * @param tableName 表名
     * @return children
     */
    Children join(String tableName);

    <P extends StEntity> Children join(Class<P> tClass);


    /**
     * 拼接 条件对象
     *
     * @param condition 是否拼接
     * @param column    字段列
     * @param val       值
     * @return Children
     */
    Children on(boolean condition, R column, Object val);


    /**
     * 拼接 条件字符串
     *
     * @param condition 是否拼接
     * @param val       完整的字符串条件
     * @return Children
     */
    Children onSql(boolean condition, String val);

    /**
     * 返回 完整 联查条件
     */
    String getSqlJoin();

    default Children on(R column, Object val) {
        return on(true, column, val);
    }

    default Children onSql(String val) {
        return onSql(true, val);
    }

}
