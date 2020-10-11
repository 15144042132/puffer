//package com.sting.db.wrapper;
//
//import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
//import com.baomidou.mybatisplus.core.conditions.SharedString;
//import com.baomidou.mybatisplus.core.conditions.query.Query;
//import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
//import com.baomidou.mybatisplus.core.conditions.update.Update;
//import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
//import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
//import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
//import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.function.Predicate;
//
///**
// * 重写Wrapper条件构造器
// * 继承 AbstractWrapper 并将 QueryWrapper和 UpdateWrapper整合在一起
// * 并做出适当增强
// *
// * @param <T>
// */
//public class StWrapper_version_3_3_0<T>
//        extends AbstractWrapper<T, String, StWrapper_version_3_3_0<T>>
//        implements Update<StWrapper_version_3_3_0<T>, String>, Query<StWrapper_version_3_3_0<T>, T, String> {
//    /**
//     * SQL 更新字段内容，例如：name='1', age=2
//     */
//    private final List<String> sqlSet;
//
//    /**
//     * 查询字段
//     */
//    private final SharedString sqlSelect = new SharedString();
//
//    public StWrapper_version_3_3_0(Class<T> entityClass) {
//        this.entityClass = entityClass;
//        this.sqlSet = new ArrayList<>();
//        super.initNeed();
//    }
//
//    public StWrapper_version_3_3_0(T entity, String... columns) {
//        this.sqlSet = new ArrayList<>();
//        super.setEntity(entity);
//        super.initNeed();
//        this.select(columns);
//    }
//
//    /**
//     * 非对外公开的构造方法,只用于生产嵌套 sql
//     *
//     * @param entityClass 本不应该需要的
//     */
//    private StWrapper_version_3_3_0(T entity, List<String> sqlSet, Class<T> entityClass, AtomicInteger paramNameSeq,
//                                    Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments,
//                                    SharedString lastSql, SharedString sqlComment) {
//        super.setEntity(entity);
//        this.sqlSet = sqlSet;
//        this.entityClass = entityClass;
//        this.paramNameSeq = paramNameSeq;
//        this.paramNameValuePairs = paramNameValuePairs;
//        this.expression = mergeSegments;
//        this.lastSql = lastSql;
//        this.sqlComment = sqlComment;
//    }
//
//    @Override
//    public String getSqlSet() {
//        if (CollectionUtils.isEmpty(sqlSet)) {
//            return null;
//        }
//        return String.join(StringPool.COMMA, sqlSet);
//    }
//
//    @Override
//    public StWrapper_version_3_3_0<T> set(boolean condition, String column, Object val) {
//        if (condition) {
//            sqlSet.add(String.format("%s=%s", column, formatSql("{0}", val)));
//        }
//        return typedThis;
//    }
//
//    @Override
//    public StWrapper_version_3_3_0<T> setSql(boolean condition, String sql) {
//        if (condition && StringUtils.isNotBlank(sql)) {
//            sqlSet.add(sql);
//        }
//        return typedThis;
//    }
//
//
//    @Override
//    public StWrapper_version_3_3_0<T> select(String... columns) {
//        if (ArrayUtils.isNotEmpty(columns)) {
//            this.sqlSelect.setStringValue(String.join(StringPool.COMMA, columns));
//        }
//        return typedThis;
//    }
//
//    @Override
//    public StWrapper_version_3_3_0<T> select(Predicate<TableFieldInfo> predicate) {
//        return select(entityClass, predicate);
//    }
//
//    @Override
//    public StWrapper_version_3_3_0<T> select(Class<T> entityClass, Predicate<TableFieldInfo> predicate) {
//        this.entityClass = entityClass;
//        this.sqlSelect.setStringValue(TableInfoHelper.getTableInfo(getCheckEntityClass()).chooseSelect(predicate));
//        return typedThis;
//    }
//
//
//    /**
//     * 用于生成嵌套 sql
//     * <p>
//     * 故 sqlSelect 不向下传递
//     * </p>
//     */
//    @Override
//    protected StWrapper_version_3_3_0<T> instance() {
//        return new StWrapper_version_3_3_0<>(entity, sqlSet, entityClass, paramNameSeq, paramNameValuePairs, new MergeSegments(),
//                SharedString.emptyString(), SharedString.emptyString());
//    }
//}