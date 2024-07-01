package io.github.kiryu1223.drink.core.context;

import io.github.kiryu1223.drink.config.Config;
import io.github.kiryu1223.drink.config.inter.IDBConfig;

import java.util.List;

public class SqlPropertyContext extends SqlContext
{
    private final String property;
    private final int tableIndex;

    public SqlPropertyContext(String property, int tableIndex)
    {
        this.property = property;
        this.tableIndex = tableIndex;
    }

    public String getProperty()
    {
        return property;
    }

    public int getTableIndex()
    {
        return tableIndex;
    }

    @Override
    public String getSqlAndValue(Config config, List<Object> values)
    {
        return getSql(config);
    }

    @Override
    public String getSql(Config config)
    {
        IDBConfig dbConfig = config.getDbConfig();
        return getTableAsName(tableIndex) + "." + dbConfig.propertyDisambiguation(property);
    }
}
