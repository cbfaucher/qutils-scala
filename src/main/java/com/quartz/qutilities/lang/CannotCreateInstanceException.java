/*
 * Copyright (c) 2005 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.lang;

import com.quartz.qutilities.lang.QException;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class CannotCreateInstanceException extends QException
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public CannotCreateInstanceException(Throwable cause)
    {
        super(cause);
    }

    public CannotCreateInstanceException(String message, Throwable cause)
    {
        super(message, cause);
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    ///////////////////////////////////////
    ////    INNER CLASSES
}
