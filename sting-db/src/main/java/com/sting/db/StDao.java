package com.sting.db;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

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
    /**
     * 添加操作
     */
    <P extends StEntity> long insert(P entity);

    <P extends StEntity> long insertBatch(List<P> entityList);

    <P extends StEntity> long insertBatch(List<P> entityList, int batchSize);

    <P extends StEntity> long insertOrUpdate(P entity);

    <P extends StEntity> long insertOrUpdate(P entity, Wrapper<P> miWrapper);

    <P extends StEntity> long insertOrUpdateBatch(List<P> entityList);

    <P extends StEntity> long insertOrUpdateBatch(List<P> entityList, int batchSize);

    long insert(String sqlString);

    long insert(String tableName, Map<String, Object> map);

//
//    <P extends StEntity> long delete(Wrapper<P> wrapper);
//
//    <P extends StEntity> long delete(List<Class<P>> pClassList, Wrapper<P> wrapper);
//
//    <P extends StEntity> long deleteById(Class<P> pClass, Serializable id);
//
//    <P extends StEntity> long deleteByIds(Class<P> pClass, List<?> idList);
//
//    <P extends StEntity, MP extends Map<?, ?>> long deleteByMap(Class<P> pClass, MP columnMap);
//
//
//    <P extends StEntity> long update(Wrapper<P> wrapper);
//
//    <P extends StEntity> long update(P entity, Wrapper<P> wrapper);
//
//    <P extends StEntity> long updateById(P entity);
//
//    <P extends StEntity> long updateBatchById(List<P> entityList);
//
//    <P extends StEntity> long updateBatchById(List<P> entityList, int batchSize);
//
//
//    <P extends StEntity> P selectById(Class<P> pClass, Serializable id);
//
//    <P extends StEntity> P selectOne(Wrapper<P> miWrapper);
//
//    <P extends StEntity> Map<String, Object> selectMap(Wrapper<P> miWrapper);
//
//    <P extends StEntity> Object selectObj(Wrapper<P> miWrapper);
//
//    <P extends StEntity> List<P> list(Class<P> pClass);
//
//    <P extends StEntity> List<P> list(Wrapper<P> miWrapper);
//
//    <P extends StEntity> List<P> listByIds(Class<P> pClass, List<?> idList);
//
//    <P extends StEntity> List<P> listByMap(Class<P> pClass, Map<String, Object> mapCondition);
//
//    <P extends StEntity> List<Map<String, Object>> listMap(Class<P> pClass);
//
//    <P extends StEntity> List<Map<String, Object>> listMap(Wrapper<P> miWrapper);
//
//    <P extends StEntity> List<Object> listObj(Wrapper<P> miWrapper);
//
//    <P extends StEntity, T> List<T> listObj(Wrapper<P> miWrapper, Class<T> tClass);
//
//    <P extends StEntity> long count(Class<P> pClass);
//
//    <P extends StEntity> long count(Wrapper<P> miWrapper);
//
//    <P extends StEntity> long count(Map<String, Object> mapCondition);
//
//    <P extends StEntity> StPage<P> page(StPage<P> page, Class<P> pClass);
//
//    <P extends StEntity> StPage<P> page(StPage<P> page, Wrapper<P> miWrapper);
//
//    <P extends StEntity> StPage<Map<String, Object>> pageMap(StPage page, Class<P> pClass);
//
//    <P extends StEntity> StPage<Map<String, Object>> pageMap(StPage page, Wrapper<P> miWrapper);

    /**
     * 非实体类操作
     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */

//
//    long delete(String sqlString);
//
//    long deleteById(String tableName, Serializable id);
//
//    long deleteByIds(String tableName, List<?> idList);
//
//    long update(String sqlString);
//
//    long update(String tableName, Map map, Serializable id);
//
//    long updateByIds(String tableName, Map map, List<Serializable> ids);

    //返回一个 字段

    /**
     * 查询一个字段
     * 返回基本数据类型
     *
     * @param sqlString 完整的SQL字符串
     */
//    Object selectObj(String sqlString);
//
    //返回一个 Map
//    Map<String, Object> selectOne(String sqlString);

    //返回一个 P
//    <P extends StEntity> P selectOne(String sqlString, Class<P> tClass);

    /**
     * 查询一个字段
     * 返回 List<Object>
     *
     * @param sqlString 完整的字符串SQL
     */
//    List<Object> listObj(String sqlString);

    /**
     * 查询一个字段
     * 返回 List<基本数据类型>
     *
     * @param sqlString 完整的字符串SQL
     * @param tClass,T  基本数据类型
     */
//    public <T> List<T> listObj(String sqlString, Class<T> tClass);
//
//    //返回 List<Map>
//    List<Map<String, Object>> list(String sqlString);
//
//    //返回 List<P>
//    <P extends StEntity> List<P> list(String sqlString, Class<P> tClass);
//
//    long count(String sqlString);
//
//    /**
//     * 按照字符串条件 查询分页
//     *
//     * @param page      分页对象
//     * @param pClass,P  返回类型
//     * @param sqlString 完整的SQL字符串
//     */
//    <P extends StEntity> StPage<P> page(StPage page, Class<P> pClass, String sqlString);
}
