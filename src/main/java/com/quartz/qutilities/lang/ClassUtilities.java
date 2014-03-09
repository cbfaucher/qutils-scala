/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.lang;

import org.apache.commons.lang.ClassUtils;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
final public class ClassUtilities
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    static public <T extends Object> T create(String pClassName) throws ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        final Class<T> clazz = (Class<T>) Class.forName(pClassName);
        return clazz.newInstance();
    }

    public static <T>T create(String pClassName, Class<T> pClass) throws IllegalAccessException, InstantiationException, ClassNotFoundException
    {
        final Object o = create(pClassName);

        if (ClassUtils.isAssignable(o.getClass(), pClass))
        {
            return (T) o;
        }
        else
        {
            throw new InstantiationException("Not of type: " + pClass.getName());
        }
    }

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    ///////////////////////////////////////
    ////    INNER CLASSES

    public static Collection<Method> findGetters(Class pClass)
    {
        final Set<Method> getters = new HashSet<Method>();

        for (Method m : pClass.getMethods())
        {
            //  must start with "get"
            if (!m.getName().startsWith("get")) continue;

            //  must return something
            if (m.getReturnType() == Void.class) continue;

            //  skip methods with parameters (not standard bean getters)
            if (m.getParameterTypes().length > 0) continue;

            getters.add(m);
        }

        return getters;
    }
}
