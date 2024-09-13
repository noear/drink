package io.github.kiryu1223.drink.core.expression.ext.oracle;

import io.github.kiryu1223.drink.config.Config;
import io.github.kiryu1223.drink.core.expression.*;

import java.util.ArrayList;
import java.util.List;

public class OracleQueryableExpression extends SqlQueryableExpression
{
    public OracleQueryableExpression(SqlSelectExpression select, SqlFromExpression from, SqlJoinsExpression joins, SqlWhereExpression where, SqlGroupByExpression groupBy, SqlHavingExpression having, SqlOrderByExpression orderBy, SqlLimitExpression limit)
    {
        super(select, from, joins, where, groupBy, having, orderBy, limit);
    }

    @Override
    public String getSqlAndValue(Config config, List<Object> values)
    {
        List<String> strings = new ArrayList<>();
        if (!from.isEmptyTable() && (limit.onlyHasRows() || limit.hasRowsAndOffset()))
        {
            strings.add("SELECT * FROM (SELECT t.*,ROWNUM AS \"-ROWNUM-\" FROM (");
        }
        strings.add(select.getSqlAndValue(config, values));
        String fromSqlAndValue = from.getSqlAndValue(config, values);
        if (!fromSqlAndValue.isEmpty()) strings.add(fromSqlAndValue);
        String joinsSqlAndValue = joins.getSqlAndValue(config, values);
        if (!joinsSqlAndValue.isEmpty()) strings.add(joinsSqlAndValue);
        String whereSqlAndValue = where.getSqlAndValue(config, values);
        if (!whereSqlAndValue.isEmpty()) strings.add(whereSqlAndValue);
        String groupBySqlAndValue = groupBy.getSqlAndValue(config, values);
        if (!groupBySqlAndValue.isEmpty()) strings.add(groupBySqlAndValue);
        String havingSqlAndValue = having.getSqlAndValue(config, values);
        if (!havingSqlAndValue.isEmpty()) strings.add(havingSqlAndValue);
        String orderBySqlAndValue = orderBy.getSqlAndValue(config, values);
        if (!orderBySqlAndValue.isEmpty()) strings.add(orderBySqlAndValue);
        if (!from.isEmptyTable() && (limit.onlyHasRows() || limit.hasRowsAndOffset()))
        {
            strings.add(") t) WHERE \"-ROWNUM-\"");
            if (limit.onlyHasRows())
            {
                strings.add("<= " + limit.getRows());
            }
            else
            {
                strings.add(String.format("BETWEEN %d AND %d", limit.getOffset() + 1, limit.getOffset() + limit.getRows()));
            }
        }
        return String.join(" ", strings);
    }
}
