/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.formatter;

import java.util.Collection;
import java.util.Iterator;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class MultipleRowsFormat<S extends Collection, T extends Object> extends RowFormat<S, T>
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public MultipleRowsFormat()
    {
    }

    public MultipleRowsFormat(CellFormat[] pFormats)
    {
        super(pFormats);
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public String format(S pCollection) throws FormatException, IllegalArgumentException
    {
        final StringBuffer buffer = new StringBuffer(1024);

        for (Iterator iterator = pCollection.iterator(); iterator.hasNext();)
        {
            final T rowElement = (T) iterator.next();

            if (buffer.length() > 0) buffer.append("\n");

            buffer.append(formatRow(rowElement));
        }

        return buffer.toString();
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
