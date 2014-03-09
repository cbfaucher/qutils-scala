package com.quartz.qutilities.swing.table;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
class GetMethodColumnGetter implements IColumnGetter
{
    final private Method method;

    GetMethodColumnGetter(Method pMethod)
    {
        super();

        method = pMethod;
    }

    public <T> Object get(T pFrom)
    {
        try
        {
            return method.invoke(pFrom);
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
        catch (InvocationTargetException e)
        {
            throw new RuntimeException(e.getTargetException());
        }
    }
}
