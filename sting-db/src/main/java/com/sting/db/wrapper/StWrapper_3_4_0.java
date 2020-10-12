package com.sting.db.wrapper;//package com.sting.db.wrapper;
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
// * 并做实现Wrapper增强器 StBaseWrapper
// *
// * @param <T>
// */
//public class StWrapper_3_4_0<T> extends AbstractWrapper<T, String, StWrapper_3_4_0<T>>
//        implements
//        Update<StWrapper_3_4_0<T>, String>,
//        Query<StWrapper_3_4_0<T>, T, String>,
//        StJoin<StWrapper_3_4_0<T>, String> {
//
//    public StWrapper_3_4_0(Class<T> entityClass) {
//        this.setEntityClass(entityClass);
//        this.sqlSet = new ArrayList<>();
//        super.initNeed();
//    }
//
//    public StWrapper_3_4_0(T entity, String... columns) {
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
//    private StWrapper_3_4_0(T entity, List<String> sqlSet, Class<T> entityClass, AtomicInteger paramNameSeq,
//                            Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments, SharedString lastSql,
//                            SharedString sqlComment, List<String> joinCondition, List<String> sqlJoin, String joinString) {
//        super.setEntity(entity);
//        this.sqlSet = sqlSet;
//        this.setEntityClass(entityClass);
//        this.paramNameSeq = paramNameSeq;
//        this.paramNameValuePairs = paramNameValuePairs;
//        this.expression = mergeSegments;
//        this.lastSql = lastSql;
//        this.sqlComment = sqlComment;
//        this.joinCondition = joinCondition;
//        this.sqlJoin = sqlJoin;
//        this.joinString = joinString;
//
//
//    }
//
//    /**
//     * 用于生成嵌套 sql
//     * <p>
//     * 故 sqlSelect 不向下传递
//     * </p>
//     */
//    @Override
//    protected StWrapper_3_4_0<T> instance() {
//        return new StWrapper_3_4_0<>(getEntity(), sqlSet, getEntityClass(), paramNameSeq, paramNameValuePairs, new MergeSegments(),
//                SharedString.emptyString(), SharedString.emptyString(), joinCondition,
//                sqlJoin,
//                joinString);
//    }
//
//    @Override
//    public void clear() {
//        super.clear();
//        sqlSelect.toNull();
//        sqlSet.clear();
//        joinCondition.clear();
//        sqlJoin.clear();
//        joinString = "";
//    }
//
//
//    /**
//     * UPDATE 接口实现
//     */
//    private final List<String> sqlSet;
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
//    public StWrapper_3_4_0<T> set(boolean condition, String column, Object val) {
//        if (condition) {
//            sqlSet.add(String.format("%s=%s", column, formatSql("{0}", val)));
//        }
//        return typedThis;
//    }
//
//    @Override
//    public StWrapper_3_4_0<T> setSql(boolean condition, String sql) {
//
//        if (condition && StringUtils.isNotBlank(sql)) {
//            sqlSet.add(sql);
//        }
//        return typedThis;
//    }
//
//
//    /**
//     * QUERY接口实现
//     */
//    private final SharedString sqlSelect = new SharedString();
//
//    @Override
//    public StWrapper_3_4_0<T> select(String... columns) {
//        if (ArrayUtils.isNotEmpty(columns)) {
//            this.sqlSelect.setStringValue(String.join(StringPool.COMMA, columns));
//        }
//        return typedThis;
//    }
//
//    @Override
//    public StWrapper_3_4_0<T> select(Predicate<TableFieldInfo> predicate) {
//        return select(getEntityClass(), predicate);
//    }
//
//    @Override
//    public StWrapper_3_4_0<T> select(Class<T> entityClass, Predicate<TableFieldInfo> predicate) {
//        this.setEntityClass(entityClass);
//        this.sqlSelect.setStringValue(TableInfoHelper.getTableInfo(getEntityClass()).chooseSelect(predicate));
//        return typedThis;
//    }
//
//
//    /**
//     * StJOIN 接口实现
//     */
//    private String joinString = "";
//    private List<String> joinCondition = new ArrayList<>();
//    private List<String> sqlJoin = new ArrayList<>();
//
//    @Override
//    public StWrapper_3_4_0<T> leftJoin(String tableName) {
//        if (!joinString.isEmpty()) {
//            sqlJoin.add(joinString + getJoinSqlCondition());
//            joinCondition.clear();
//        }
//        joinString = WrapperConst.LEFT_JOIN + tableName + WrapperConst.ON;
//        return typedThis;
//
//    }
//
//    @Override
//    public StWrapper_3_4_0<T> rightJoin(String tableName) {
//        if (!joinString.isEmpty()) {
//            sqlJoin.add(joinString + getJoinSqlCondition());
//            joinCondition.clear();
//        }
//        joinString = WrapperConst.RIGHT_JOIN + tableName + WrapperConst.ON;
//        return typedThis;
//    }
//
//    @Override
//    public StWrapper_3_4_0<T> join(String tableName) {
//        if (!joinString.isEmpty()) {
//            sqlJoin.add(joinString + getJoinSqlCondition());
//            joinCondition.clear();
//        }
//        joinString = WrapperConst.JOIN + tableName + WrapperConst.ON;
//        return typedThis;
//    }
//
//    @Override
//    public StWrapper_3_4_0<T> on(boolean condition, String column, Object val) {
//        if (condition) {
//            joinCondition.add(String.format("%s=%s", column, formatSql("{0}", val)));
//        }
//        return typedThis;
//    }
//
//    @Override
//    public StWrapper_3_4_0<T> onSql(boolean condition, String sql) {
//        if (condition && StringUtils.isNotBlank(sql)) {
//            joinCondition.add(sql);
//        }
//        return typedThis;
//    }
//
//    /**
//     * 返回完整 JOIN SQL
//     */
//    @Override
//    public String getSqlJoin() {
//        if (CollectionUtils.isEmpty(sqlJoin)) {
//            return null;
//        }
//        return String.join(" ", sqlJoin);
//    }
//
//    /**
//     * 返回当前的 JOIN ON 条件
//     */
//    private String getJoinSqlCondition() {
//        if (CollectionUtils.isEmpty(joinCondition)) {
//            return null;
//        }
//        return String.join(StringPool.COMMA, joinCondition);
//    }
//
//}
