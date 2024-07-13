package io.github.kiryu1223.drink.api.crud.read.group;


import io.github.kiryu1223.drink.api.crud.builder.QuerySqlBuilder;
import io.github.kiryu1223.drink.api.crud.read.EndQuery;
import io.github.kiryu1223.drink.api.crud.read.LQuery;
import io.github.kiryu1223.drink.api.crud.read.QueryBase;
import io.github.kiryu1223.expressionTree.delegate.Func1;
import io.github.kiryu1223.expressionTree.expressions.Expr;
import io.github.kiryu1223.expressionTree.expressions.ExprTree;

import java.util.List;

public class GroupedQuery7<Key, T1, T2, T3, T4, T5, T6, T7> extends QueryBase
{
    public GroupedQuery7(QuerySqlBuilder sqlBuilder)
    {
        super(sqlBuilder);
    }

    // region [HAVING]
    public GroupedQuery7<Key, T1, T2, T3, T4, T5, T6, T7> having(@Expr Func1<Group7<Key, T1, T2, T3, T4, T5, T6, T7>, Boolean> func)
    {
        throw new RuntimeException();
    }

    public GroupedQuery7<Key, T1, T2, T3, T4, T5, T6, T7> having(ExprTree<Func1<Group7<Key, T1, T2, T3, T4, T5, T6, T7>, Boolean>> expr)
    {
        having(expr.getTree());
        return this;
    }

    // endregion

    // region [ORDER BY]
    public <R> GroupedQuery7<Key, T1, T2, T3, T4, T5, T6, T7> orderBy(@Expr Func1<Group7<Key, T1, T2, T3, T4, T5, T6, T7>, R> expr, boolean asc)
    {
        throw new RuntimeException();
    }

    public <R> GroupedQuery7<Key, T1, T2, T3, T4, T5, T6, T7> orderBy(ExprTree<Func1<Group7<Key, T1, T2, T3, T4, T5, T6, T7>, R>> expr, boolean asc)
    {
        orderBy(expr.getTree(), asc);
        return this;
    }

    public <R> GroupedQuery7<Key, T1, T2, T3, T4, T5, T6, T7> orderBy(@Expr Func1<Group7<Key, T1, T2, T3, T4, T5, T6, T7>, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> GroupedQuery7<Key, T1, T2, T3, T4, T5, T6, T7> orderBy(ExprTree<Func1<Group7<Key, T1, T2, T3, T4, T5, T6, T7>, R>> expr)
    {
        orderBy(expr, true);
        return this;
    }
    // endregion

    // region [SELECT]
    public <R> LQuery<R> select(@Expr Func1<Group7<Key, T1, T2, T3, T4, T5, T6, T7>, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> LQuery<R> select(ExprTree<Func1<Group7<Key, T1, T2, T3, T4, T5, T6, T7>, R>> expr)
    {
        singleCheck(select(expr.getTree()));
        return new LQuery<>(getSqlBuilder());
    }

    public <R> EndQuery<R> selectSingle(@Expr Func1<Group7<Key, T1, T2, T3, T4, T5, T6, T7>, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> EndQuery<R> selectSingle(ExprTree<Func1<Group7<Key, T1, T2, T3, T4, T5, T6, T7>, R>> expr)
    {
        select(expr.getTree());
        return new EndQuery<>(this);
    }
    // endregion

    @Override
    public List<Key> toList()
    {
        return super.toList();
    }

    public GroupedQuery7<Key, T1, T2, T3, T4, T5, T6, T7> distinct()
    {
        distinct0(true);
        return this;
    }

    public GroupedQuery7<Key, T1, T2, T3, T4, T5, T6, T7> distinct(boolean condition)
    {
        distinct0(condition);
        return this;
    }
}
