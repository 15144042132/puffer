package com.sting.db.dao;

import com.sting.db.entity.StEntity;
import com.sting.db.entity.StPage;
import com.sting.db.wrapper.StWrapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 强化 DAO
 * P:实体类
 * tenantName: 动态数据源
 *
 * @author WangYongJi
 */
public interface StDao {
    long insert(String sqlString);

    long insert(String... sqlString);

    long insert(String tableName, Map map);

    <P extends StEntity> long insert(Class<P> pClass, Map map);

    <P extends StEntity> long insert(P entity);

    <P extends StEntity> long insert(P... entity);

    <P extends StEntity> long insertBatch(List<P> entityList);

    <P extends StEntity> long insertBatch(List<P> entityList, int batchSize);

    <P extends StEntity> long insertOrUpdate(P entity);

    <P extends StEntity> long insertOrUpdate(P entity, StWrapper<P> stWrapper);

    <P extends StEntity> long insertOrUpdateBatch(List<P> entityList);

    <P extends StEntity> long insertOrUpdateBatch(List<P> entityList, int batchSize);

    // 根据 entity 条件，删除记录
    <P extends StEntity> long delete(StWrapper<P> stWrapper);

    <P extends StEntity> long deleteById(Class<P> pClass, Serializable id);

    <MP extends Map<?, ?>> long deleteByMap(String tableName, MP mapCondition);

    <P extends StEntity, MP extends Map<?, ?>> long deleteByMap(Class<P> pClass, MP mapCondition);

    <P extends StEntity> long deleteByIds(Class<P> pClass, List<?> idList);

    long update(String sqlString);

    <P extends StEntity> long update(StWrapper<P> stWrapper);

    <P extends StEntity> long update(P entity, StWrapper<P> stWrapper);

    long update(String tableName, Serializable id, Map map);

    <P extends StEntity> long update(Class<P> pClass, Serializable id, Map map);

    <P extends StEntity> long updateById(P entity);

    <P extends StEntity> long updateBatchById(List<P> entityList);

    <P extends StEntity> long updateBatchById(List<P> entityList, int batchSize);

    long updateByIds(String tableName, List<Serializable> ids, Map map);

    <P extends StEntity> long updateByIds(Class<P> pClass, List<Serializable> ids, Map map);

    <P extends StEntity> P selectOne(Class<P> pClass, Serializable id);

    Object selectObj(String sqlString);

    <P extends StEntity> P selectOne(String sqlString, Class<P> tClass);

    <P extends StEntity> P selectOne(StWrapper<P> stWrapper);

    Map<String, Object> selectMap(String sqlString);

    <P extends StEntity> Map<String, Object> selectMap(StWrapper<P> stWrapper);

    <P extends StEntity> Map<String, Object> selectMap(Class<P> pClass, Serializable id);

    <P extends StEntity> Object selectObj(StWrapper<P> stWrapper);

    <P extends StEntity> List<P> list(Class<P> pClass);

    <P extends StEntity> List<P> list(StWrapper<P> stWrapper);

    <P extends StEntity> List<P> list(Class<P> tClass, String sqlString);

//    <P extends StEntity> List<P> list(Class<P> pClass, Map<String, Object> mapCondition);

    List<Object> listObj(String sqlString);

    <P extends StEntity> List<P> list(Class<P> pClass, List idList);

    List<Map<String, Object>> listMap(String sqlString);

    <P extends StEntity> List<Map<String, Object>> listMap(Class<P> pClass);

    <P extends StEntity> List<Map<String, Object>> listMap(StWrapper<P> stWrapper);

    <P extends StEntity> List<Object> listObj(StWrapper<P> stWrapper);

    long count(String sqlString);

    <P extends StEntity> long count(Class<P> pClass);

    <P extends StEntity> long count(StWrapper<P> stWrapper);

    <P extends StEntity> StPage<P> page(StPage<P> page, Class<P> pClass);

    <P extends StEntity> StPage<P> page(StPage<P> page, StWrapper<P> stWrapper);

//    <P extends StEntity> StPage<Map<String, Object>> pageMap(StPage page, Class<P> pClass);
//
//    <P extends StEntity> StPage<Map<String, Object>> pageMap(StPage page, StWrapper<P> stWrapper);


    long delete(String sqlString);

    long deleteById(String tableName, Serializable id);

    long deleteByIds(String tableName, List<?> idList);

    <P extends StEntity> StPage<P> page(StPage page, Class<P> pClass, String sqlString);

}
