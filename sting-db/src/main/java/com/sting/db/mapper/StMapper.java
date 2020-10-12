package com.sting.db.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.sting.db.wrapper.StWrapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author 王永吉
 */
@Component
public interface StMapper extends StBaseMapper {
    String TABLE_NAME = "tableName";
    String SQL_SET = "sqlSet";
    String ENTITY = "entity";
    String ENTITY_COLUMN = "entityColumn";
    String JOIN = "join";
    String COLUMN = "column";
    String EW = "ew";
    String ENTITY_LIST = "entityList";
    String ID_COLUMN = "idColumn";
    String ID = "id";
    String IDS = "ids";
    String SQL_STRING = "sqlString";

    long _insert_by_str_(@Param(SQL_STRING) String sqlString);

    long _insert_by_map_(@Param(TABLE_NAME) String tableName, @Param(ENTITY) Map<?, ?> entity);

    long _update_by_str_(@Param(SQL_STRING) String sqlString);

    long _count_by_str_(@Param(SQL_STRING) String sqlString);

    Map<String, Object> _select_one_map_by_str_(@Param(SQL_STRING) String sqlString);

    Object _select_one_obj_by_str_(@Param(SQL_STRING) String sqlString);

    List<Map<String, Object>> _select_list_map_by_str_(@Param(SQL_STRING) String sqlString);

    List<Object> _select_list_obj_by_str_(@Param(SQL_STRING) String sqlString);

    long _update_by_map_id_(@Param(TABLE_NAME) Object tableName, @Param(ENTITY) Map<?, ?> entity, @Param(ID) Object id);

    long _update_by_map_ids_(@Param(TABLE_NAME) Object tableName, @Param(ENTITY) Map<?, ?> entity, @Param(IDS) List<Serializable> ids);

    List<Map<String, Object>> _page_map_by_str_(
            @Param(SQL_STRING) String sqlString,
            @Param("limit") long limit,
            @Param("offset") long offset);


    long _insert_by_entity_(
            @Param(TABLE_NAME) String tableName,
            @Param(ENTITY_COLUMN) Map entityColumn,
            @Param(ENTITY) Object entity
    );

    long _insert_batch_by_entity_(
            @Param(TABLE_NAME) String tableName,
            @Param(ENTITY_COLUMN) Map entityColumn,
            @Param(ENTITY_LIST) List<?> entityList
    );


    long _delete_by_str_(@Param(SQL_STRING) String sqlString);


    long _delete_by_id_(@Param(TABLE_NAME) String tableName, @Param(ID) Serializable id);

    long _delete_by_ids_(@Param(TABLE_NAME) String tableName, @Param(IDS) List<?> idList);

    long _delete_by_map_(@Param(TABLE_NAME) String tableName, @Param(COLUMN) Map column);

    long _delete_by_wrapper_(@Param(TABLE_NAME) String tableName, @Param(EW) StWrapper<?> wrapper);


    long _update_by_id_(
            @Param(TABLE_NAME) String tableName,
            @Param(COLUMN) Map column,
            @Param(ENTITY) Object entity,
            @Param(ID_COLUMN) Object idColumn,
            @Param(ID) Object id
    );

    long _update_by_wrapper_(
            @Param(TABLE_NAME) String tableName,
            @Param(COLUMN) Map column,
            @Param(ENTITY) Object entity,
            @Param(EW) StWrapper<?> wrapper
    );

    long _update_by_wrapper_sql_set_(
            @Param(TABLE_NAME) Object tableName,
            @Param(SQL_SET) String sqlSet,
            @Param(EW) StWrapper<?> wrapper
    );

    long _update_batch_by_id_(
            @Param(TABLE_NAME) String tableName,
            @Param(COLUMN) Map column,
            @Param(ENTITY_LIST) List<?> entityList
    );

    long _count_by_id_(
            @Param(TABLE_NAME) String tableName,
            @Param(COLUMN) Object column,
            @Param(ID) Object id
    );

    long _count_(@Param(TABLE_NAME) String tableName);

    long _count_by_wrapper_(
            @Param(TABLE_NAME) String tableName,
            @Param(JOIN) String join,
            @Param(COLUMN) Object column,
            @Param(EW) StWrapper<?> wrapper
    );

    List<Map<String, Object>> _select_list_map_(@Param(TABLE_NAME) String tableName);

    List<Object> _select_list_obj_(@Param(TABLE_NAME) String tableName);

    List<Map<String, Object>> _select_list_by_wrapper_(
            @Param(COLUMN) String column,
            @Param(TABLE_NAME) String tableName,
            @Param("join") String join,
            @Param(EW) StWrapper<?> wrapper
    );

    List<Object> _select_list_obj_by_wrapper_(
            @Param(COLUMN) String column,
            @Param(TABLE_NAME) String tableName,
            @Param("join") String join,
            @Param(EW) StWrapper<?> wrapper
    );

    List<Map> _select_list_by_map_(
            @Param(TABLE_NAME) String tableName,
            @Param(COLUMN) Map column
    );


    Map _select_one_map_by_id_(
            @Param(TABLE_NAME) String tableName,
            @Param(ID_COLUMN) String idColumn,
            @Param(ID) Serializable id);

    Map _select_one_map_by_wrapper_(
            @Param(COLUMN) String column,
            @Param(TABLE_NAME) String tableName,
            @Param("join") String join,
            @Param(EW) StWrapper<?> wrapper
    );


    Object _select_one_obj_by_wrapper_(
            @Param(COLUMN) String column,
            @Param(TABLE_NAME) String tableName,
            @Param("join") String join,
            @Param(EW) StWrapper<?> wrapper
    );

    List<Map<String, Object>> _page_map_by_wrapper_(
            @Param(COLUMN) String column,
            @Param(TABLE_NAME) String tableName,
            @Param("join") String join,
            @Param("limit") long limit, @Param("offset") long offset,
            @Param(EW) Wrapper<?> ew);

}
