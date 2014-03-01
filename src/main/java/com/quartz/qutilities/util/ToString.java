/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.util;

/**
 * Converts the content to a string
 *
 * @author Christian
 * @since Quartz...
 */
public interface ToString<T extends Object>
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    INSTANCE METHODS


    abstract public String toString(T pObject);

    ///////////////////////////////////////
    ////    INNER CLASSES
}
