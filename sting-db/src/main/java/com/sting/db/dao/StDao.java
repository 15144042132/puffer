package com.sting.db.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.sting.db.entity.StPage;
import com.sting.db.entity.StEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 强化 DAO
 * P:实体类
 * tenantName: 动态数据源
 *
 * @author 王永吉
 */
public interface StDao {
    // 插入一条记录（选择字段，策略插入）
    <P extends StEntity> long insert(P entity);

    // 插入（批量）
    <P extends StEntity> long insertBatch(List<P> entityList);

    // 插入（批量）
    <P extends StEntity> long insertBatch(List<P> entityList, int batchSize);

    // TableId 注解存在更新记录，否插入一条记录
    <P extends StEntity> long insertOrUpdate(P entity);

    // 根据miWrapper尝试更新，否继续执行insertOrUpdate(P)方法
    <P extends StEntity> long insertOrUpdate(P entity, Wrapper<P> miWrapper);

    // 批量修改插入
    <P extends StEntity> long insertOrUpdateBatch(List<P> entityList);

    // 批量修改插入
    <P extends StEntity> long insertOrUpdateBatch(List<P> entityList, int batchSize);


    // 根据 entity 条件，删除记录
    <P extends StEntity> long delete(Wrapper<P> miWrapper);

    // 根据 ID 删除
    <P extends StEntity> long deleteById(Class<P> pClass, Serializable id);

    // 根据 columnMap 条件，删除记录
    <P extends StEntity, MP extends Map<?, ?>> long deleteByMap(Class<P> pClass, MP columnMap);

    // 删除（根据ID 批量删除）
    <P extends StEntity> long deleteByIds(Class<P> pClass, List<?> idList);

    // 根据 UpdateWrapper 条件，更新记录 需要设置sqlset
    <P extends StEntity> long update(Wrapper<P> miWrapper);

    // 根据 whereEntity 条件，更新记录
    <P extends StEntity> long update(P entity, Wrapper<P> miWrapper);

    // 根据 ID 选择修改
    <P extends StEntity> long update(P entity);

    // 根据ID 批量更新
    <P extends StEntity> long updateBatchById(List<P> entityList);

    // 根据ID 批量更新
    <P extends StEntity> long updateBatchById(List<P> entityList, int batchSize);

    // 根据 ID 查询返回实体类
    <P extends StEntity> P selectById(Class<P> pClass, Serializable id);

    // 根据 Wrapper，查询一条记录。结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")
    <P extends StEntity> P selectOne(Wrapper<P> miWrapper);

    // 根据 Wrapper，查询并返回Map
    <P extends StEntity> Map<String, Object> selectMap(Wrapper<P> miWrapper);

    /**
     * 查询一个字段
     * 返回基本数据类型
     *
     * @param miWrapper 条件构造器
     */
    <P extends StEntity> Object selectObj(Wrapper<P> miWrapper);

    // 查询所有
    <P extends StEntity> List<P> list(Class<P> pClass);

    // 查询列表
    <P extends StEntity> List<P> list(Wrapper<P> miWrapper);

    // 查询（根据ID 批量查询）
    <P extends StEntity> List<P> listByIds(Class<P> pClass, List<?> idList);

    /**
     * 查询（根据 mapCondition 条件）,返回 List<P>
     */
    <P extends StEntity> List<P> listMap(Class<P> pClass, Map<String, Object> mapCondition);

    // 查询所有列表
    <P extends StEntity> List<Map<String, Object>> listMap(Class<P> pClass);

    // 查询列表
    <P extends StEntity> List<Map<String, Object>> listMap(Wrapper<P> miWrapper);

    /**
     * 查询一个字段
     * 返回 List<Object>
     *
     * @param miWrapper 条件构造器
     */
    <P extends StEntity> List<Object> listObj(Wrapper<P> miWrapper);

    /**
     * 查询一个字段
     * 返回 List<基本数据类型>
     *
     * @param miWrapper 条件构造器
     * @param tClass,T  基本数据类型
     */
    <P extends StEntity, T> List<T> listObj(Wrapper<P> miWrapper, Class<T> tClass);

    // 查询总记录数
    <P extends StEntity> long count(Class<P> pClass);

    // 根据 Wrapper 条件，查询总记录数
    <P extends StEntity> long count(Wrapper<P> miWrapper);

    // 无条件翻页查询
    <P extends StEntity> StPage<P> page(StPage<P> page, Class<P> pClass);

    // 翻页查询
    <P extends StEntity> StPage<P> page(StPage<P> page, Wrapper<P> miWrapper);

    // 无条件翻页查询
    <P extends StEntity> StPage<Map<String, Object>> pageMap(StPage page, Class<P> pClass);

    // 翻页查询
    <P extends StEntity> StPage<Map<String, Object>> pageMap(StPage page, Wrapper<P> miWrapper);

    /**
     * 非实体类操作
     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    long insert(String sqlString);

    long insert(String tableName, Map map);

    long delete(String sqlString);

    long deleteById(String tableName, Serializable id);

    long deleteByIds(String tableName, List<?> idList);

    long update(String sqlString);

    long update(String tableName, Map map, Serializable id);

    long updateByIds(String tableName, Map map, List<Serializable> ids);

    //返回一个 字段

    /**
     * 查询一个字段
     * 返回基本数据类型
     *
     * @param sqlString 完整的SQL字符串
     */
    Object selectObj(String sqlString);

    //返回一个 Map
    Map<String, Object> selectOne(String sqlString);

    //返回一个 P
    <P extends StEntity> P selectOne(String sqlString, Class<P> tClass);

    /**
     * 查询一个字段
     * 返回 List<Object>
     *
     * @param sqlString 完整的字符串SQL
     */
    List<Object> listObj(String sqlString);

    /**
     * 查询一个字段
     * 返回 List<基本数据类型>
     *
     * @param sqlString 完整的字符串SQL
     * @param tClass,T  基本数据类型
     */
    public <T> List<T> listObj(String sqlString, Class<T> tClass);

    //返回 List<Map>
    List<Map<String, Object>> list(String sqlString);

    //返回 List<P>
    <P extends StEntity> List<P> list(String sqlString, Class<P> tClass);

    long count(String sqlString);

    /**
     * 按照字符串条件 查询分页
     *
     * @param page      分页对象
     * @param pClass,P  返回类型
     * @param sqlString 完整的SQL字符串
     */
    <P extends StEntity> StPage<P> page(StPage page, Class<P> pClass, String sqlString);
}
