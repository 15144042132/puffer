package com.sting.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.sting.core.spring.ContextKit;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 帮助类
 *
 * @author 王永吉
 */
@Data
public class MiHelp {

    public static <P extends StEntity> void add(P entity) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entity.getClass());
        if (tableInfo == null) TableInfoHelper.initTableInfo(null, entity.getClass());
    }

    public static <P extends StEntity> void add(Class<P> tClass) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(tClass);
        if (tableInfo == null) TableInfoHelper.initTableInfo(null, tClass);
    }

    public static <P extends StEntity> String getTableName(Class<P> tClass) {
        add(tClass);
        return TableInfoHelper.getTableInfo(tClass).getTableName();
    }

    public static <P extends StEntity> String getTableName(P entity) {
        add(entity);
        return TableInfoHelper.getTableInfo(entity.getClass()).getTableName();
    }

    public static <P extends StEntity> String getTableId(Class<P> tClass) {
        add(tClass);
        return TableInfoHelper.getTableInfo(tClass).getKeyColumn();
    }

    public static <P extends StEntity> String getTableId(P entity) {
        add(entity.getClass());
        return TableInfoHelper.getTableInfo(entity.getClass()).getKeyColumn();
    }

    /**
     * 非空字段
     *
     * @return <非空属性，column>
     */
    public static <P extends StEntity> HashMap<String, Object> getNotNullColumn(List<P> entitys) {
        HashMap<String, Object> columnMap = new HashMap<>();
        for (P entity : entitys) {
            Class<?> mClass = entity.getClass();
            Field[] declaredFields = mClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                TableId tableId = declaredField.getAnnotation(TableId.class);
                TableField tableField = declaredField.getAnnotation(TableField.class);
                String column = "";

                //空属性不存储
                if (tableField == null && tableId == null) continue;
                if (tableField != null && !tableField.exist()) continue;
                declaredField.setAccessible(true);

                //updateEntity <非空属性字段，值>
                if (tableId != null) column = tableId.value();
                if (tableField != null) column = tableField.value();
                try {
                    Object tableValue = declaredField.get(entity);
                    if (tableValue != null) {
                        columnMap.put(declaredField.getName(), column);
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return columnMap;
    }


    /**
     * 非空字段
     *
     * @return <非空属性，column>
     */
    public static HashMap<String, Object> getNotNullColumn(Object entity) {
        HashMap<String, Object> columnMap = new HashMap<>();
        Class<?> mClass = entity.getClass();
        Field[] declaredFields = mClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            TableId tableId = declaredField.getAnnotation(TableId.class);
            TableField tableField = declaredField.getAnnotation(TableField.class);
            String column = "";

            //空属性不存储
            if (tableField == null && tableId == null) continue;
            if (tableField != null && !tableField.exist()) continue;
            declaredField.setAccessible(true);

            //updateEntity <非空属性字段，值>
            if (tableId != null) column = tableId.value();
            if (tableField != null) column = tableField.value();
            try {
                Object tableValue = declaredField.get(entity);
                if (tableValue != null) {
                    columnMap.put(declaredField.getName(), column);
                }
            } catch (Exception ignored) {
            }
        }
        return columnMap;
    }

    public static <P extends StEntity> Object getIdValue(P entity) {
        String idValue = null;
        Class<?> mClass = entity.getClass();
        Field[] declaredFields = mClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            TableId tableId = declaredField.getAnnotation(TableId.class);
            //设置ID
            if (tableId != null) {
                declaredField.setAccessible(true);
                try {
                    idValue = declaredField.get(entity).toString();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return idValue;
    }

    /**
     * 填充
     */
    public static <P extends StEntity> void insertFill(P entity) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entity.getClass());
        if (tableInfo == null) TableInfoHelper.initTableInfo(null, entity.getClass());
        tableInfo = TableInfoHelper.getTableInfo(entity.getClass());

        FillHandler handler = ContextKit.getBean(FillHandler.class);
        List<TableFieldInfo> insert = tableInfo.getFieldList().stream().filter(TableFieldInfo::isWithInsertFill).collect(Collectors.toList());

        //循环遍历
        for (TableFieldInfo tableFieldInfo : insert) {
            String property = tableFieldInfo.getProperty();
            HashMap<String, Object> insertFill = new HashMap<>();
            handler.insertFill(insertFill);
            Object o = insertFill.get(property);
            try {
                setNotNullProperty(entity, property, o);
            } catch (Exception ignored) {

            }
        }
    }

    /**
     * 填充
     */
    public static <P extends StEntity> void updateFill(P entity) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entity.getClass());
        if (tableInfo == null) TableInfoHelper.initTableInfo(null, entity.getClass());
        tableInfo = TableInfoHelper.getTableInfo(entity.getClass());

        FillHandler handler = ContextKit.getBean(FillHandler.class);
        List<TableFieldInfo> update = tableInfo.getFieldList().stream().filter(TableFieldInfo::isWithUpdateFill).collect(Collectors.toList());

        //循环遍历
        for (TableFieldInfo tableFieldInfo : update) {
            String property = tableFieldInfo.getProperty();
            HashMap<String, Object> updateFill = new HashMap<>();
            handler.updateFill(updateFill);
            Object o = updateFill.get(property);
            try {
                setNotNullProperty(entity, property, o);
            } catch (Exception ignored) {
            }
        }
    }


    /**
     * 填充
     */
    public static <P extends StEntity> void insertFill(List<P> entityList) {
        for (P entity : entityList) {
            insertFill(entity);
        }
    }

    /**
     * 填充
     */
    public static <P extends StEntity> void updateFill(List<P> entityList) {
        for (P entity : entityList) {
            updateFill(entity);
        }
    }

    /**
     * 设置值-条件为null
     *
     * @param entity   实体
     * @param property 属性
     * @param value    值
     */
    public static <P extends StEntity> void setNotNullProperty(P entity, String property, Object value) throws IllegalAccessException {
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            TableField tableField = field.getAnnotation(TableField.class);
            if (tableField != null && !tableField.exist()) continue;
            field.setAccessible(true);

            //
            Object filedValue = field.get(entity);
            if (filedValue == null) {
                if (field.getName().equals(property)) {
                    field.set(entity, value);
                }
            }
        }
    }

}
