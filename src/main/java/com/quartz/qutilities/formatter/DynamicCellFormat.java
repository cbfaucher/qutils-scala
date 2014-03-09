/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.formatter;

import com.quartz.qutilities.util.ToString;
import com.quartz.qutilities.util.DefaultToString;
import com.quartz.qutilities.util.DefaultToString;
import com.quartz.qutilities.util.ToString;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class DynamicCellFormat<T extends Object> extends CellFormat
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    protected ToString      toString;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public DynamicCellFormat()
    {
        this(null, null);
    }

    public DynamicCellFormat(String pTitle)
    {
        this(pTitle, null);
    }

    public DynamicCellFormat(String pTitle, ToString pToString)
    {
        this(pTitle, pToString, null);
    }

    public DynamicCellFormat(String pTitle, ToString pToString, CellPresentation pCellPresentation)
    {
        super(pTitle, pCellPresentation);

        toString = (pToString != null ? pToString : new DefaultToString(""));
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public ToString getToString()
    {
        return toString;
    }

    public void setToString(ToString pToString)
    {
        toString = pToString;
    }

    public String getCellContent(T pObject) throws FormatException
    {
        return toString.toString(pObject);
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
