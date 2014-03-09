package com.quartz.qutilities.swing.table;

import java.lang.reflect.Field;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class FieldColumnGetter implements IColumnGetter
{
    final private Field field;

    public FieldColumnGetter(Field pField)
    {
        field = pField;
    }

    public <T> Object get(T pFrom)
    {
        try
        {
            if (!field.isAccessible()) field.setAccessible(true);

            return field.get(pFrom);
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }

    }
}
