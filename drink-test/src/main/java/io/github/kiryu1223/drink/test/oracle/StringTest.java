package io.github.kiryu1223.drink.test.oracle;

import io.github.kiryu1223.drink.ext.SqlFunctions;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringTest extends BaseTest
{
    private static final Logger log = LoggerFactory.getLogger(StringTest.class);

    @Test
    public void concat()
    {
        String c1 = client.queryEmptyTable()
                .endSelect(() -> SqlFunctions.concat("aa", "bb"))
                .first();
        Assert.assertEquals(c1,"aabb");
        String c2 = client.queryEmptyTable()
                .endSelect(() -> SqlFunctions.concat("aa", "bb", "cc", "dd", "ee", "ff", "gg"))
                .first();
        Assert.assertEquals(c2,"aabbccddeeffgg");
    }

    @Test
    public void join()
    {
        String j1 = client.queryEmptyTable()
                .endSelect(() -> SqlFunctions.join("+", "aa", "bb"))
                .first();
        Assert.assertEquals(j1,"aa+bb");
        String j2 = client.queryEmptyTable()
                .endSelect(() ->
                        SqlFunctions.join("|", "aa", "bb", "cc", "dd", "ee", "ff", "gg")
                ).first();
        Assert.assertEquals(j2,"aa|bb|cc|dd|ee|ff|gg");
    }
}
