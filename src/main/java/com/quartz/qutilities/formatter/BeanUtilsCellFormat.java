/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.formatter;

import com.quartz.qutilities.util.ToString;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class BeanUtilsCellFormat<T extends Object> extends DynamicCellFormat<T>
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private String propertyPath = null;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public BeanUtilsCellFormat()
    {
    }

    public BeanUtilsCellFormat(String pPropertyPath)
    {
        propertyPath = pPropertyPath;
    }

    public BeanUtilsCellFormat(String pTitle, String pPropertyPath)
    {
        super(pTitle);
        propertyPath = pPropertyPath;
    }

    public BeanUtilsCellFormat(String pTitle, String pPropertyPath, ToString pToString, CellPresentation pCellPresentation)
    {
        super(pTitle, pToString, pCellPresentation);
        propertyPath = pPropertyPath;
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public String getPropertyPath()
    {
        return propertyPath;
    }

    public void setPropertyPath(String pPropertyPath)
    {
        propertyPath = pPropertyPath;
    }

    public String getCellContent(T o) throws FormatException
    {
        try
        {
            return this.toString.toString(PropertyUtils.getNestedProperty(o, propertyPath));
        }
        catch (IllegalAccessException e)
        {
            throw new FormatException(e);
        }
        catch (InvocationTargetException e)
        {
            throw new FormatException(e.getTargetException());
        }
        catch (NoSuchMethodException e)
        {
            throw new FormatException("No property " + propertyPath + " on object " + o, e);
        }
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
