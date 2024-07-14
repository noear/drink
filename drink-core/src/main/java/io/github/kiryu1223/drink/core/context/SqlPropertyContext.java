package io.github.kiryu1223.drink.core.context;

import io.github.kiryu1223.drink.config.Config;
import io.github.kiryu1223.drink.config.inter.IDBConfig;
import io.github.kiryu1223.drink.core.builder.PropertyMetaData;

import java.util.List;

public class SqlPropertyContext extends SqlContext
{
    private final PropertyMetaData propertyMetaData;
    private final String property;
    private int tableIndex;

    public SqlPropertyContext(PropertyMetaData propertyMetaData, int tableIndex)
    {
        this.propertyMetaData = propertyMetaData;
        this.property = propertyMetaData.getColumn();
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

    public void setTableIndex(int tableIndex)
    {
        this.tableIndex = tableIndex;
    }

    public PropertyMetaData getPropertyMetaData()
    {
        return propertyMetaData;
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
        return "t" + tableIndex + "." + dbConfig.propertyDisambiguation(property);
    }
}
