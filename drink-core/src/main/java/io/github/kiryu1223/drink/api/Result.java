package io.github.kiryu1223.drink.api;

import io.github.kiryu1223.expressionTree.expressions.annos.Getter;
import io.github.kiryu1223.expressionTree.expressions.annos.Setter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Result
{
    @Override
    public String toString()
    {
        try
        {
            List<String> strings = new ArrayList<>();
            for (Field field : getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                int modifiers = field.getModifiers();
                if (Modifier.isFinal(modifiers)
                        || Modifier.isStatic(modifiers)
                        || Modifier.isTransient(modifiers)) continue;
                String name = field.getName();
                strings.add(name + "=" + field.get(this));
            }
            return "(" + String.join(",", strings) + ")";
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }
}
