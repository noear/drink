package io.github.kiryu1223.drink.api.crud.read;

import io.github.kiryu1223.drink.api.crud.read.group.GroupedQuery10;
import io.github.kiryu1223.drink.config.Config;
import io.github.kiryu1223.expressionTree.delegate.Func1;
import io.github.kiryu1223.expressionTree.delegate.Func10;
import io.github.kiryu1223.expressionTree.expressions.Expr;
import io.github.kiryu1223.expressionTree.expressions.ExprTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class LQuery10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> extends QueryBase
{
    // region [INIT]

    public LQuery10(Config config)
    {
        super(config);
    }

    public LQuery10(Config config, Class<?> c1, Class<?> c2, Class<?> c3, Class<?> c4, Class<?> c5, Class<?> c6, Class<?> c7, Class<?> c8, Class<?> c9, Class<?> c10)
    {
        super(config);
        getSqlBuilder().addFrom(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);
    }

    public LQuery10(QueryBase q1, QueryBase q2, QueryBase q3, QueryBase q4, QueryBase q5, QueryBase q6, QueryBase q7, QueryBase q8, QueryBase q9, QueryBase q10)
    {
        super(q1.getConfig());
        getSqlBuilder().addFrom(q1.getSqlBuilder(), q2.getSqlBuilder(), q3.getSqlBuilder(), q4.getSqlBuilder(), q5.getSqlBuilder(), q6.getSqlBuilder(), q7.getSqlBuilder(), q8.getSqlBuilder(), q9.getSqlBuilder(), q10.getSqlBuilder());
    }

    // endregion

    // region [WHERE]
    public LQuery10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> where(@Expr Func10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Boolean> func)
    {
        throw new RuntimeException();
    }

    public LQuery10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> where(ExprTree<Func10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Boolean>> expr)
    {
        where(expr.getTree());
        return this;
    }
    // endregion

    // region [ORDER BY]
    public <R> LQuery10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> orderBy(@Expr Func10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> expr, boolean asc)
    {
        throw new RuntimeException();
    }

    public <R> LQuery10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> orderBy(ExprTree<Func10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R>> expr, boolean asc)
    {
        orderBy(expr.getTree(), asc);
        return this;
    }

    public <R> LQuery10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> orderBy(@Expr Func10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> LQuery10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> orderBy(ExprTree<Func10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R>> expr)
    {
        orderBy(expr.getTree(), true);
        return this;
    }
    // endregion

    // region [LIMIT]
    public LQuery10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> limit(long rows)
    {
        limit0(rows);
        return this;
    }

    public LQuery10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> limit(long offset, long rows)
    {
        limit0(offset, rows);
        return this;
    }
    // endregion

    // region [GROUP BY]
    public <Key> GroupedQuery10<Key, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> groupBy(@Expr Func10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Key> expr)
    {
        throw new RuntimeException();
    }

    public <Key> GroupedQuery10<Key, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> groupBy(ExprTree<Func10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Key>> expr)
    {
        groupBy(expr.getTree());
        return new GroupedQuery10<>(getSqlBuilder());
    }
    // endregion

    // region [SELECT]
    public LQuery<T1> select()
    {
        return new LQuery<>(this);
    }

    public <R> LQuery<R> select(Class<R> r)
    {
        getSqlBuilder().setTargetClass(r);
        return new LQuery<>(this);
    }

    public <R> LQuery<R> select(@Expr Func10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> LQuery<R> select(ExprTree<Func10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R>> expr)
    {
        select(expr.getTree());
        return new LQuery<>(this);
    }

    // endregion

    //region [OTHER]

    public LQuery10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> distinct()
    {
        getSqlBuilder().setDistinct(true);
        return this;
    }

    public LQuery10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> distinct(boolean condition)
    {
        getSqlBuilder().setDistinct(condition);
        return this;
    }
    //endregion
}
