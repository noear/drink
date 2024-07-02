package io.github.kiryu1223.drink.core.builder;

import sun.misc.Unsafe;

import java.io.Serializable;
import java.lang.invoke.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Supplier;

public class FastCreator<T>
{
    private static final Unsafe unsafe;

    static
    {
        try
        {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        }
        catch (IllegalAccessException | NoSuchFieldException e)
        {
            throw new RuntimeException(e);
        }
    }

    private final Class<T> target;
    private final boolean isAnonymousClass;
    //private final MethodHandles.Lookup lookup;

    public FastCreator(Class<T> target)
    {
        this.target = target;
        //this.lookup = lookup;
        this.isAnonymousClass = target.isAnonymousClass();
    }

    public Supplier<T> getCreator()
    {
        if (isAnonymousClass)
        {
            return () ->
            {
                try
                {
                    return (T) unsafe.allocateInstance(target);
                }
                catch (InstantiationException e)
                {
                    throw new RuntimeException(e);
                }
            };
        }
        else
        {
            try
            {
                MethodType constructorType = MethodType.methodType(void.class);
                MethodHandles.Lookup caller = MethodHandles.lookup();
                MethodHandle constructorHandle = caller.findConstructor(target, constructorType);

                CallSite site = LambdaMetafactory.altMetafactory(caller,
                        "get",
                        MethodType.methodType(Supplier.class),
                        constructorHandle.type().generic(),
                        constructorHandle,
                        constructorHandle.type(), 1);
                return (Supplier<T>) site.getTarget().invokeExact();
            }
            catch (Throwable e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public Getter<Object, ?> getGetter(Class<?> propertyType, Method readMethod)
    {
        String getFunName = readMethod.getName();
        final MethodHandles.Lookup caller = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(propertyType, target);
        final CallSite site;

        try
        {
            site = LambdaMetafactory.altMetafactory(caller,
                    "get",
                    MethodType.methodType(Getter.class),
                    methodType.erase().generic(),
                    caller.findVirtual(target, getFunName, MethodType.methodType(propertyType)),
                    methodType, 1);
            return (Getter<Object, ?>) site.getTarget().invokeExact();
        }
        catch (Throwable e)
        {
            throw new RuntimeException(e);
        }
    }

    public Setter<Object> getSetter(Method writeMethod)
    {
        return getSetter(writeMethod.getParameterTypes()[0],writeMethod);
    }

    public Setter<Object> getSetter(Class<?> propertyType, Method writeMethod)
    {
        MethodHandles.Lookup caller = MethodHandles.lookup();
        MethodType setter = MethodType.methodType(writeMethod.getReturnType(), propertyType);
        String getFunName = writeMethod.getName();
        try
        {
            MethodType instantiatedMethodType = MethodType.methodType(void.class, target, propertyType);
            MethodHandle targetHandle = caller.findVirtual(target, getFunName, setter);
            MethodType samMethodType = MethodType.methodType(void.class, Object.class, Object.class);
            CallSite site = LambdaMetafactory.metafactory(
                    caller,
                    "set",
                    MethodType.methodType(Setter.class),
                    samMethodType,
                    targetHandle,
                    instantiatedMethodType
            );

            VoidSetter<Object, Object> voidSetter = (VoidSetter<Object, Object>) site.getTarget().invokeExact();
            return voidSetter::set;
        }
        catch (Throwable e)
        {
            throw new RuntimeException(e);
        }
    }

    public interface Getter<T, R> extends Serializable
    {
        R get(T t);
    }

    private interface VoidSetter<T1, T2> extends Serializable
    {
        void set(T1 t, T2 value);
    }

    public interface Setter<T>
    {
        void set(T t, Object value);
    }
}
