package io.github.kiryu1223.drink.api.crud.delete;

import io.github.kiryu1223.drink.api.crud.base.CRUD;
import io.github.kiryu1223.drink.api.crud.builder.DeleteSqlBuilder;
import io.github.kiryu1223.drink.config.Config;
import io.github.kiryu1223.drink.core.builder.SqlSession;
import io.github.kiryu1223.drink.core.builder.SqlSessionBuilder;

import java.util.ArrayList;
import java.util.List;

public abstract class DeleteBase extends CRUD
{
    private final DeleteSqlBuilder sqlBuilder;

    public DeleteBase(Config config)
    {
        this.sqlBuilder = new DeleteSqlBuilder(config);
    }

    protected DeleteSqlBuilder getSqlBuilder()
    {
        return sqlBuilder;
    }

    public Config getConfig()
    {
        return sqlBuilder.getConfig();
    }

    protected void setDeleteTable(Class<?> c)
    {
        sqlBuilder.setDeleteTable(c);
    }

    public long executeRows()
    {
        checkHasWhere();
        List<Object> values = new ArrayList<>();
        String sql = sqlBuilder.getSqlAndValue(values);
        System.out.println("===> " + sql);
        String key = sqlBuilder.getConfig().getDsKey();
        System.out.println("使用数据源: " + (key == null ? "默认" : key));
        SqlSession session = SqlSessionBuilder.getSession(getConfig().getDsKey());
        return session.executeUpdate(sql, values);
    }

    public String toSql()
    {
        return sqlBuilder.getSql();
    }

    private void checkHasWhere()
    {
        if (!sqlBuilder.hasWhere())
        {
            throw new RuntimeException("DELETE没有条件");
        }
    }
}
