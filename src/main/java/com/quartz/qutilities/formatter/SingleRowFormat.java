/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.formatter;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class SingleRowFormat<T extends Object> extends RowFormat<T, T>
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public SingleRowFormat()
    {
    }

    public SingleRowFormat(CellFormat[] pFormats)
    {
        super(pFormats);
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public String format(T o) throws FormatException, IllegalArgumentException
    {
        return formatRow(o);
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
