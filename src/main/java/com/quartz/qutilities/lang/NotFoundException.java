/*
 * Copyright (c) 2005 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.lang;

/**
 * When something is not found
 *
 * @author Christian
 * @since Quartz...
 */
public class NotFoundException extends QException
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public NotFoundException(String message)
    {
        super(message);
    }

    public NotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NotFoundException(Throwable cause)
    {
        super(cause);
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    ///////////////////////////////////////
    ////    INNER CLASSES
}
