/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.formatter;

import com.quartz.qutilities.lang.QException;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class FormatException extends QException
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public FormatException(String message)
    {
        super(message);
    }

    public FormatException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public FormatException(Throwable cause)
    {
        super(cause);
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    ///////////////////////////////////////
    ////    INNER CLASSES
}
