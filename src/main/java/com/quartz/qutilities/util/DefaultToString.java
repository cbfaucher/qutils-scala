/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.util;

/**
 * A simple {@link ToString} implementation which return <tt>Object.toString()</tt> or {@link #stringIfNull}  if null.
 *
 * @author Christian
 * @since Quartz...
 */
public class DefaultToString implements ToString<Object>
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private String stringIfNull = "null";

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public DefaultToString()
    {
    }

    public DefaultToString(String pStringIfNull)
    {
        stringIfNull = pStringIfNull;
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public String getStringIfNull()
    {
        return stringIfNull;
    }

    public void setStringIfNull(String pStringIfNull)
    {
        stringIfNull = pStringIfNull;
    }

    public String toString(Object pObject)
    {
        return (pObject != null ? pObject.toString() : stringIfNull);
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
