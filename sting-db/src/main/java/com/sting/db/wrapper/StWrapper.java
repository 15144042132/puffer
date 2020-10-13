package com.sting.db.wrapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.Update;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sting.db.entity.StEntity;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

/**
 * 重写Wrapper条件构造器
 * 继承 AbstractWrapper 并将 QueryWrapper和 UpdateWrapper整合在一起
 * 并做出适当增强
 *
 * @param <T>
 */
public class StWrapper<T>
        extends AbstractWrapper<T, String, StWrapper<T>>
        implements
        Update<StWrapper<T>, String>,
        Query<StWrapper<T>, T, String>,
        StJoin<StWrapper<T>, String> {

    //fromTable=null时，表名从typeClass读取
    private String fromTable = null;

    /**
     * limit 操作
     *
     * @param limit 截取条数
     */
    public StWrapper<T> limit(Object limit) {
        this.last("limit " + limit);
        return typedThis;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public String getFromTable() {
        return fromTable;
    }

    public StWrapper<T> setFromTable(String fromTable) {
        this.fromTable = fromTable;
        return typedThis;
    }


    /**
     * FIND_IN_SET
     *
     * @param condition 是否使用条件
     * @param column    字段
     * @param value     数据
     */
    public StWrapper<T> findInSet(boolean condition, String column, Object value) {
        if (condition) {
            this.findInSet(column, value);
        }
        return typedThis;
    }

    public StWrapper<T> findInSet(String column, Object value) {
        this.apply(" FIND_IN_SET (" + value + " , " + column + ") ");
        return typedThis;
    }


    public StWrapper(Class<T> entityClass) {
        boolean assignableFrom = StEntity.class.isAssignableFrom(entityClass);
        if (assignableFrom) {
            TableName annotation = AnnotationUtils.findAnnotation(entityClass, TableName.class);
            if (annotation != null) {
                this.fromTable = annotation.value();
            }
        }
        this.entityClass = entityClass;
        this.sqlSet = new ArrayList<>();
        super.initNeed();
    }

    public StWrapper(T entity, String... columns) {
        this.sqlSet = new ArrayList<>();
        super.setEntity(entity);
        super.initNeed();
        this.select(columns);
    }

    /**
     * 非对外公开的构造方法,只用于生产嵌套 sql
     *
     * @param entityClass 本不应该需要的
     */
    private StWrapper(T entity, List<String> sqlSet, Class<T> entityClass, AtomicInteger paramNameSeq,
                      Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments,
                      SharedString lastSql, SharedString sqlComment,
                      String joinString, List<String> joinCondition, List<String> sqlJoin) {
        super.setEntity(entity);
        this.sqlSet = sqlSet;
        this.entityClass = entityClass;
        this.paramNameSeq = paramNameSeq;
        this.paramNameValuePairs = paramNameValuePairs;
        this.expression = mergeSegments;
        this.lastSql = lastSql;
        this.sqlComment = sqlComment;
        this.joinString = joinString;
        this.joinCondition = joinCondition;
        this.sqlJoin = sqlJoin;
    }


    /**
     * 用于生成嵌套 sql
     * <p>
     * 故 sqlSelect 不向下传递
     * </p>
     */
    @Override
    protected StWrapper<T> instance() {
        return new StWrapper<>(entity, sqlSet, entityClass,
                paramNameSeq, paramNameValuePairs, new MergeSegments(),
                SharedString.emptyString(), SharedString.emptyString(),
                joinString, joinCondition, sqlJoin
        );
    }


    /**
     * SQL 更新字段内容，例如：name='1', age=2
     */
    private final List<String> sqlSet;

    @Override
    public String getSqlSet() {
        if (CollectionUtils.isEmpty(sqlSet)) {
            return null;
        }
        return String.join(StringPool.COMMA, sqlSet);
    }

    @Override
    public StWrapper<T> set(boolean condition, String column, Object val) {
        if (condition) {
            sqlSet.add(String.format("%s=%s", column, formatSql("{0}", val)));
        }
        return typedThis;
    }

    @Override
    public StWrapper<T> setSql(boolean condition, String sql) {
        if (condition && StringUtils.isNotBlank(sql)) {
            sqlSet.add(sql);
        }
        return typedThis;
    }


    /**
     * 查询字段
     */
    private final SharedString sqlSelect = new SharedString();

    @Override
    public StWrapper<T> select(String... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            this.sqlSelect.setStringValue(String.join(StringPool.COMMA, columns));
        }
        return typedThis;
    }

    public String getSqlSelect() {
        if (sqlSelect.getStringValue() == null || sqlSelect.getStringValue().isEmpty()) {
            return " * ";
        }
        return sqlSelect.getStringValue();
    }


    @Override
    public StWrapper<T> select(Predicate<TableFieldInfo> predicate) {
        return select(entityClass, predicate);
    }

    @Override
    public StWrapper<T> select(Class<T> entityClass, Predicate<TableFieldInfo> predicate) {
        this.entityClass = entityClass;
        this.sqlSelect.setStringValue(TableInfoHelper.getTableInfo(getCheckEntityClass()).chooseSelect(predicate));
        return typedThis;
    }


    /**
     * StJOIN 接口实现
     */
    private String joinString = "";
    private List<String> joinCondition = new ArrayList<>();
    private List<String> sqlJoin = new ArrayList<>();

    @Override
    public StWrapper<T> leftJoin(String tableName) {
        if (!joinString.isEmpty()) {
            sqlJoin.add(joinString + getJoinSqlCondition());
            joinCondition.clear();
        }
        joinString = WrapperConst.LEFT_JOIN + tableName + WrapperConst.ON;
        return typedThis;

    }

    @Override
    public StWrapper<T> rightJoin(String tableName) {
        if (!joinString.isEmpty()) {
            sqlJoin.add(joinString + getJoinSqlCondition());
            joinCondition.clear();
        }
        joinString = WrapperConst.RIGHT_JOIN + tableName + WrapperConst.ON;
        return typedThis;
    }

    @Override
    public StWrapper<T> join(String tableName) {
        if (!joinString.isEmpty()) {
            sqlJoin.add(joinString + getJoinSqlCondition());
            joinCondition.clear();
        }
        joinString = WrapperConst.JOIN + tableName + WrapperConst.ON;
        return typedThis;
    }

    @Override
    public StWrapper<T> on(boolean condition, String column, Object val) {
        if (condition) {
            joinCondition.add(String.format("%s=%s", column, formatSql("{0}", val)));
        }
        return typedThis;
    }

    @Override
    public StWrapper<T> onSql(boolean condition, String sql) {
        if (condition && StringUtils.isNotBlank(sql)) {
            joinCondition.add(sql);
        }
        return typedThis;
    }

    /**
     * 返回完整 JOIN SQL
     */
    @Override
    public String getSqlJoin() {
        if (CollectionUtils.isEmpty(sqlJoin)) {
            return null;
        }
        return String.join(" ", sqlJoin);
    }

    /**
     * 返回当前的 JOIN ON 条件
     */
    private String getJoinSqlCondition() {
        if (CollectionUtils.isEmpty(joinCondition)) {
            return null;
        }
        return String.join(StringPool.COMMA, joinCondition);
    }


}
