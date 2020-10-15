package com.sting.db.dao;

import com.sting.db.entity.StEntity;
import com.sting.db.entity.StPage;
import com.sting.db.mapper.StMapper;
import com.sting.db.util.DbHelp;
import com.sting.db.wrapper.StWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 增强Dao实现类
 *
 * @author 王永吉
 */
@Service
public class StDaoImpl implements StDao {

    @Autowired
    private StMapper miMapper;

    @Override
    public <P extends StEntity> long insert(P entity) {
        if (entity == null) return 0;
        DbHelp.insertFill(entity);
        return miMapper._insert_by_entity_(DbHelp.getTableName(entity.getClass()), DbHelp.getNotNullColumn(entity), entity);
    }

    @Override
    public <P extends StEntity> long insert(P... entity) {
        int count = 0;
        for (P e : entity) {
            count += insert(e);
        }
        return count;
    }

    @Override
    public long insert(String sqlString) {
        return miMapper._insert_by_str_(sqlString);
    }

    @Override
    public long insert(String... sqlString) {
        int count = 0;
        for (String sql : sqlString) {
            count += insert(sql);
        }
        return count;
    }

    @Override
    public long insert(String tableName, Map map) {
        return miMapper._insert_by_map_(tableName, map);
    }

    @Override
    public <P extends StEntity> long insert(Class<P> pClass, Map map) {
        return insert(DbHelp.getTableName(pClass), map);
    }

    @Override
    public <P extends StEntity> long insertBatch(List<P> entityList) {
        return insertBatch(entityList, 1000);
    }

    @Override
    public <P extends StEntity> long insertBatch(List<P> entityList, int batchSize) {
        long addCount = 0;
        if (entityList.size() == 0) return 0;
        DbHelp.insertFill(entityList);

        String tableName = DbHelp.getTableName(entityList.get(0));
        HashMap<String, Object> notNullColumn = DbHelp.getNotNullColumn(entityList);

        int count = entityList.size() / batchSize;
        for (int i = 0; i < count; i++) {
            addCount += miMapper._insert_batch_by_entity_(tableName, notNullColumn, entityList.subList(i * batchSize, (i + 1) * batchSize));
        }

        int yu = entityList.size() % batchSize;
        if (yu > 0) {
            addCount += miMapper._insert_batch_by_entity_(tableName, notNullColumn, entityList.subList(count * batchSize, (count * batchSize) + yu));
        }

        return addCount;
    }

    @Override
    public <P extends StEntity> long insertOrUpdate(P entity) {
        Object idValue = DbHelp.getIdValue(entity);
        if (idValue == null || miMapper._count_by_id_(DbHelp.getTableName(entity.getClass()), DbHelp.getTableId(entity), idValue) <= 0) {
            return insert(entity);
        }
        return update(entity);
    }

    @Override
    public <P extends StEntity> long insertOrUpdate(P entity, StWrapper<P> stWrapper) {
        if (miMapper._count_by_wrapper_((stWrapper).getFromTable(), (stWrapper).getSqlJoin(), 1, (stWrapper)) <= 0) {
            return insert(entity);
        }
        return miMapper._update_by_wrapper_(DbHelp.getTableName(entity), DbHelp.getNotNullColumn(entity), entity, (stWrapper));
    }

    @Override
    public <P extends StEntity> long insertOrUpdateBatch(List<P> entityList) {
        return insertOrUpdateBatch(entityList, 1000);
    }

    @Override
    public <P extends StEntity> long insertOrUpdateBatch(List<P> listEntity, int batchSize) {
        if (listEntity.size() == 0) return 0;
        String tableName = DbHelp.getTableName(listEntity.get(0));
        String tableId = DbHelp.getTableId(listEntity.get(0));

        List<P> listAdd = new ArrayList<>();
        List<P> listUpdate = new ArrayList<>();
        for (P p : listEntity) {
            Object idValue = DbHelp.getIdValue(p);
            if (idValue != null) {
                long count = miMapper._count_by_id_(tableName, tableId, idValue);
                if (count <= 0) {
                    listAdd.add(p);
                } else {
                    listUpdate.add(p);
                }
            } else {
                listAdd.add(p);
            }
        }

        long l = 0;
        if (listAdd.size() > 0) {
            l = insertBatch(listAdd, batchSize);
        }
        long l1 = updateBatchById(listUpdate, batchSize);
        return (l + l1);
    }


    @Override
    public <P extends StEntity> long delete(StWrapper<P> stWrapper) {
        return miMapper._delete_by_wrapper_((stWrapper).getFromTable(), (stWrapper));
    }

    @Override
    public <P extends StEntity> long deleteById(Class<P> pClass, Serializable id) {
        return miMapper._delete_by_id_(DbHelp.getTableName(pClass), id);
    }

    @Override
    public <P extends StEntity, MP extends Map<?, ?>> long deleteByMap(Class<P> pClass, MP columnMap) {
        return miMapper._delete_by_map_(DbHelp.getTableName(pClass), columnMap);
    }

    @Override
    public <P extends StEntity> long deleteByIds(Class<P> pClass, List<?> idList) {
        if (idList == null || idList.size() == 0) return 0;
        return miMapper._delete_by_ids_(DbHelp.getTableName(pClass), idList);
    }

    @Override
    public <P extends StEntity> long update(StWrapper<P> stWrapper) {

        if ((stWrapper).getSqlSet() == null) return 0;

        return miMapper._update_by_wrapper_sql_set_((stWrapper).getFromTable(), (stWrapper).getSqlSet(), (stWrapper));
    }

    @Override
    public <P extends StEntity> long update(P entity, StWrapper<P> stWrapper) {
        if (entity == null) return 0;

        DbHelp.updateFill(entity);
        return miMapper._update_by_wrapper_(DbHelp.getTableName(entity), DbHelp.getNotNullColumn(entity), entity, (stWrapper));
    }

    @Override
    public <P extends StEntity> long update(P entity) {
        if (entity == null) return 0;
        DbHelp.updateFill(entity);
        return miMapper._update_by_id_(DbHelp.getTableName(entity), DbHelp.getNotNullColumn(entity), entity, DbHelp.getTableId(entity), DbHelp.getIdValue(entity));
    }

    @Override
    public <P extends StEntity> long updateBatchById(List<P> entityList) {
        return updateBatchById(entityList, 1000);
    }

    @Override
    public <P extends StEntity> long updateBatchById(List<P> entityList, int batchSize) {
        long addCount = 0;
        if (entityList.size() == 0) return addCount;
        DbHelp.updateFill(entityList);

        String tableName = DbHelp.getTableName(entityList.get(0));
        HashMap<String, Object> notNullColumn = DbHelp.getNotNullColumn(entityList);

        int count = entityList.size() / batchSize;
        for (int i = 0; i < count; i++) {
            addCount += miMapper._update_batch_by_id_(tableName, notNullColumn, entityList.subList(i * batchSize, (i + 1) * batchSize));
        }

        int yu = entityList.size() % batchSize;
        if (yu > 0) {
            addCount += miMapper._update_batch_by_id_(tableName, notNullColumn, entityList.subList(count * batchSize, (count * batchSize) + yu));
        }

        return addCount;
    }

    @Override
    public <P extends StEntity> P selectById(Class<P> pClass, Serializable id) {
        Map<?, ?> map = miMapper._select_one_map_by_id_(DbHelp.getTableName(pClass), DbHelp.getTableId(pClass), id);
        return DbHelp.parsObj(map, pClass);
    }

    @Override
    public <P extends StEntity> P selectOne(StWrapper<P> stWrapper) {
        Map<?, ?> map = miMapper._select_one_map_by_wrapper_(stWrapper.getSqlSelect(), (stWrapper).getFromTable(), (stWrapper).getSqlJoin(), (stWrapper));
        return DbHelp.parsObj(map, (stWrapper).getEntityClass());
    }

    @Override
    public <P extends StEntity> Map<String, Object> selectMap(StWrapper<P> stWrapper) {
        return miMapper._select_one_map_by_wrapper_(stWrapper.getSqlSelect(), (stWrapper).getFromTable(), (stWrapper).getSqlJoin(), (stWrapper));
    }

    @Override
    public <P extends StEntity> Object selectObj(StWrapper<P> stWrapper) {
        return miMapper._select_one_obj_by_wrapper_(stWrapper.getSqlSelect(), (stWrapper).getFromTable(), (stWrapper).getSqlJoin(), (stWrapper));
    }

    @Override
    public <P extends StEntity> List<P> list(Class<P> pClass) {
        List<Map<String, Object>> maps = miMapper._select_list_map_(DbHelp.getTableName(pClass));
        return DbHelp.parsArray(maps, pClass);
    }

    @Override
    public <P extends StEntity> List<P> list(StWrapper<P> stWrapper) {
        List<Map<String, Object>> maps = miMapper._select_list_by_wrapper_(stWrapper.getSqlSelect(), stWrapper.getFromTable(), stWrapper.getSqlJoin(), stWrapper);
        return DbHelp.parsArray(maps, stWrapper.getEntityClass());
    }

    @Override
    public <P extends StEntity> List<P> listByIds(Class<P> pClass, List<?> idList) {
        if (idList == null || idList.size() == 0) return new ArrayList<>();

        StWrapper<P> stWrapper = new StWrapper<>(pClass);
        (stWrapper).in(DbHelp.getTableId(pClass), idList);
        List<Map<String, Object>> maps = miMapper._select_list_by_wrapper_(stWrapper.getSqlSelect(), stWrapper.getFromTable(), stWrapper.getSqlJoin(), (stWrapper));
        return DbHelp.parsArray(maps, (stWrapper).getEntityClass());
    }

    @Override
    public <P extends StEntity> List<P> list(Class<P> pClass, Map<String, Object> mapCondition) {
        List<Map> maps = miMapper._select_list_by_map_(DbHelp.getTableName(pClass), mapCondition);
        return DbHelp.parsArray(maps, pClass);
    }

    @Override
    public <P extends StEntity> List<Map<String, Object>> listMap(Class<P> pClass) {
        return miMapper._select_list_map_(DbHelp.getTableName(pClass));
    }

    @Override
    public <P extends StEntity> List<Map<String, Object>> listMap(StWrapper<P> stWrapper) {
        return miMapper._select_list_by_wrapper_(stWrapper.getSqlSelect(), (stWrapper).getFromTable(), (stWrapper).getSqlJoin(), (stWrapper));
    }

    @Override
    public <P extends StEntity> List<Object> listObj(StWrapper<P> stWrapper) {
        return miMapper._select_list_obj_by_wrapper_(stWrapper.getSqlSelect(), (stWrapper).getFromTable(), (stWrapper).getSqlJoin(), (stWrapper));
    }

    @Override
    public <P extends StEntity, T> List<T> listObj(StWrapper<P> stWrapper, Class<T> tClass) {
        List<Object> objects = miMapper._select_list_obj_by_wrapper_(stWrapper.getSqlSelect(), (stWrapper).getFromTable(), (stWrapper).getSqlJoin(), (stWrapper));
        return DbHelp.parsArray(objects, tClass);
    }

    @Override
    public <P extends StEntity> long count(Class<P> pClass) {
        return list(pClass).size();
//        return miMapper._count_(MiHelp.getTableName(pClass));
    }

    @Override
    public <P extends StEntity> long count(StWrapper<P> stWrapper) {
        return list(stWrapper).size();
//         miMapper._count_by_wrapper_(( stWrapper).getFromTable(), ( stWrapper).getSqlJoin(), 1, ( stWrapper));
    }

    @Override
    public <P extends StEntity> StPage<P> page(StPage<P> page, Class<P> pClass) {
        StWrapper<P> stWrapper = new StWrapper<P>(pClass);
        stWrapper.eq("1", 1);
        return page(page, stWrapper);
    }

    @Override
    public <P extends StEntity> StPage<P> page(StPage<P> page, StWrapper<P> stWrapper) {
        long current = page.getCurrent();
        long size = page.getSize();
        page.setTotal(count(stWrapper));
        long pages = (page.getTotal() + size - 1) / size;
        page.setPages(pages);
        List<Map<String, Object>> maps = miMapper._page_map_by_wrapper_(stWrapper.getSqlSelect(), (stWrapper).getFromTable(), (stWrapper).getSqlJoin(), ((current - 1) * size), size, (stWrapper));
        page.setFirst(current == 1);
        page.setLast(current == page.getPages());
        page.setRecords(DbHelp.parsArray(maps, (stWrapper).getEntityClass()));
        return page;
    }

//    @Override
//    public <P extends StEntity> StPage<Map<String, Object>> pageMap(StPage page, Class<P> pClass) {
//        StWrapper<P> stWrapper = new StWrapper<>(pClass);
//        (stWrapper).eq("1", 1);
//        return pageMap(page, (stWrapper));
//    }

//    @Override
//    public <P extends StEntity> StPage<Map<String, Object>> pageMap(StPage page, StWrapper<P> stWrapper) {
//        long current = page.getCurrent();
//        long size = page.getSize();
//        page.setTotal(count(stWrapper));
//        long pages = (page.getTotal() + size - 1) / size;
//        page.setPages(pages);
//        List<Map<String, Object>> maps = miMapper._page_map_by_wrapper_(stWrapper.getSqlSelect(), (stWrapper).getFromTable(), (stWrapper).getSqlJoin(), ((current - 1) * size), size, (stWrapper));
//        page.setFirst(current == 1);
//        page.setLast(current == page.getPages());
//        page.setRecords(maps);
//        return page;
//    }


    @Override
    public long delete(String sqlString) {
        return miMapper._delete_by_str_(sqlString);
    }

    @Override
    public long deleteById(String tableName, Serializable id) {
        return miMapper._delete_by_id_(tableName, id);
    }

    @Override
    public long deleteByIds(String tableName, List<?> idList) {
        if (idList == null || idList.size() == 0) return 0;

        return miMapper._delete_by_ids_(tableName, idList);
    }

    @Override
    public long update(String sqlString) {
        return miMapper._update_by_str_(sqlString);
    }

    @Override
    public long update(String tableName, Map map, Serializable id) {
        return miMapper._update_by_map_id_(tableName, map, id);
    }

    @Override
    public long updateByIds(String tableName, Map map, List<Serializable> ids) {
        if (ids == null || ids.size() == 0) return 0;

        return miMapper._update_by_map_ids_(tableName, map, ids);
    }

    @Override
    public Map<String, Object> selectMap(String sqlString) {
        return miMapper._select_one_map_by_str_(sqlString);
    }

    @Override
    public <P extends StEntity> P selectOne(String sqlString, Class<P> tClass) {
        Map<String, Object> stringObjectMap = miMapper._select_one_map_by_str_(sqlString);
        return DbHelp.parsObj(stringObjectMap, tClass);
    }

    @Override
    public List<Object> listObj(String sqlString) {
        return miMapper._select_list_obj_by_str_(sqlString);
    }

    @Override
    public Object selectObj(String sqlString) {
        return miMapper._select_one_obj_by_str_(sqlString);
    }

    @Override
    public List<Map<String, Object>> listMap(String sqlString) {
        return miMapper._select_list_map_by_str_(sqlString);
    }

    @Override
    public <T> List<T> listObj(String sqlString, Class<T> tClass) {
        List<Object> objects = miMapper._select_list_obj_by_str_(sqlString);
        return DbHelp.parsArray(objects, tClass);
    }

    @Override
    public <P extends StEntity> List<P> list(String sqlString, Class<P> tClass) {
        List<Map<String, Object>> maps = miMapper._select_list_map_by_str_(sqlString);
        return DbHelp.parsArray(maps, tClass);
    }

    @Override
    public long count(String sqlString) {
        return miMapper._select_list_map_(sqlString).size();
//        return miMapper._count_by_str_(sqlString);
    }

    @Override
    public <P extends StEntity> StPage<P> page(StPage page, Class<P> pClass, String sqlString) {
        long current = page.getCurrent();
        long size = page.getSize();
        page.setTotal(count(sqlString));
        long pages = (page.getTotal() + size - 1) / size;
        page.setPages(pages);
        List<Map<String, Object>> maps = miMapper._page_map_by_str_(sqlString, ((current - 1) * size), size);
        page.setFirst(current == 1);
        page.setLast(current == page.getPages());
        page.setRecords(maps);
        return page;
    }

}
