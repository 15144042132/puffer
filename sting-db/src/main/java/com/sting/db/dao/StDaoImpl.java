package com.sting.db.dao;

import com.sting.db.entity.StEntity;
import com.sting.db.mapper.StMapper;
import com.sting.db.util.MiHelp;
import com.sting.db.wrapper.StWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StDaoImpl implements StDao {
    @Autowired
    private StMapper miMapper;

    @Override
    public <P extends StEntity> long insert(P entity) {
        if (entity == null) return 0;
        //Insert填充
        MiHelp.insertFill(entity);
        return miMapper._insert_by_entity_(MiHelp.getTableName(entity.getClass()), MiHelp.getNotNullColumn(entity), entity);
    }

    @Override
    public <P extends StEntity> long insertBatch(List<P> entityList) {
        return insertBatch(entityList, 500);
    }

    @Override
    public <P extends StEntity> long insertBatch(List<P> entityList, int batchSize) {
        long addCount = 0;
        if (entityList.size() == 0) return 0;
        MiHelp.insertFill(entityList);

        String tableName = MiHelp.getTableName(entityList.get(0));
        HashMap<String, Object> notNullColumn = MiHelp.getNotNullColumn(entityList);

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
        Object idValue = MiHelp.getIdValue(entity);
        if (idValue == null || miMapper._count_by_id_(MiHelp.getTableName(entity.getClass()), MiHelp.getTableId(entity), idValue) <= 0) {
            return insert(entity);
        }
        return 0;
    }

    @Override
    public <P extends StEntity> long insertOrUpdate(P entity, StWrapper<P> miWrapper) {
        return 0;
    }

    @Override
    public <P extends StEntity> long insertOrUpdateBatch(List<P> entityList) {
        return 0;
    }

    @Override
    public <P extends StEntity> long insertOrUpdateBatch(List<P> entityList, int batchSize) {
        return 0;
    }

    @Override
    public long insert(String fullSqlString) {
        return miMapper._insert_by_str_(fullSqlString);
    }

    @Override
    public long insert(String tableName, Map<String, Object> map) {
        return miMapper._insert_by_map_(tableName, map);
    }

}
