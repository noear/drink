package io.github.kiryu1223.drink.core.context;

import io.github.kiryu1223.drink.config.Config;

import java.util.ArrayList;
import java.util.List;

public class SqlFuncContext extends SqlContext
{
    private final String name;

    private final List<SqlContext> args;

    public SqlFuncContext(String name, List<SqlContext> args)
    {
        this.name = name;
        this.args = args;
    }

    public String getName()
    {
        return name;
    }

    public List<SqlContext> getArgs()
    {
        return args;
    }

    @Override
    public String getSqlAndValue(Config config, List<Object> values)
    {
        List<String> strings = new ArrayList<>();
        for (SqlContext arg : args)
        {
            strings.add(arg.getSqlAndValue(config, values));
        }
        return name + "(" + String.join(",", strings) + ")";
    }

    @Override
    public String getSql(Config config)
    {
        List<String> strings = new ArrayList<>();
        for (SqlContext arg : args)
        {
            strings.add(arg.getSql(config));
        }
        return name + "(" + String.join(",", strings) + ")";
    }
}
