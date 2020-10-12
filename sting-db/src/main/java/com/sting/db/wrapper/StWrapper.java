package com.sting.db.wrapper;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

/**
 * 重写Wrapper条件构造器
 * 继承 AbstractWrapper 并将 QueryWrapper和 UpdateWrapper整合在一起
 * 并做实现Wrapper增强器 StBaseWrapper
 *
 * @param <T>
 */
public class StWrapper<T> extends AbstractWrapper<T, String, StWrapper<T>>
        implements
        Update<StWrapper<T>, String>,
        Query<StWrapper<T>, T, String>,
        StJoin<StWrapper<T>, String> {

    public StWrapper(Class<T> entityClass) {
        this.setEntityClass(entityClass);
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
                      Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments, SharedString lastSql,
                      SharedString sqlComment, List<String> joinCondition, List<String> sqlJoin, String joinString) {
        super.setEntity(entity);
        this.sqlSet = sqlSet;
        this.setEntityClass(entityClass);
        this.paramNameSeq = paramNameSeq;
        this.paramNameValuePairs = paramNameValuePairs;
        this.expression = mergeSegments;
        this.lastSql = lastSql;
        this.sqlComment = sqlComment;
        this.joinCondition = joinCondition;
        this.sqlJoin = sqlJoin;
        this.joinString = joinString;


    }

    /**
     * 用于生成嵌套 sql
     * <p>
     * 故 sqlSelect 不向下传递
     * </p>
     */
    @Override
    protected StWrapper<T> instance() {
        return new StWrapper<>(getEntity(), sqlSet, getEntityClass(), paramNameSeq, paramNameValuePairs, new MergeSegments(),
                SharedString.emptyString(), SharedString.emptyString(), joinCondition,
                sqlJoin,
                joinString);
    }

    @Override
    public void clear() {
        super.clear();
        sqlSelect.toNull();
        sqlSet.clear();
        joinCondition.clear();
        sqlJoin.clear();
        joinString = "";
    }


    /**
     * UPDATE 接口实现
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
     * QUERY接口实现
     */
    private final SharedString sqlSelect = new SharedString();

    @Override
    public StWrapper<T> select(String... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            this.sqlSelect.setStringValue(String.join(StringPool.COMMA, columns));
        }
        return typedThis;
    }

    @Override
    public StWrapper<T> select(Predicate<TableFieldInfo> predicate) {
        return select(getEntityClass(), predicate);
    }

    @Override
    public StWrapper<T> select(Class<T> entityClass, Predicate<TableFieldInfo> predicate) {
        this.setEntityClass(entityClass);
        this.sqlSelect.setStringValue(TableInfoHelper.getTableInfo(getEntityClass()).chooseSelect(predicate));
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
