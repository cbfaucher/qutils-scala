/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.util;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class ToPercentString implements ToString<Object>
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private int decimals;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public ToPercentString()
    {
        this(2);
    }

    public ToPercentString(int pDecimals)
    {
        decimals = pDecimals;
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS



    public String toString(Object pObject)
    {
        if (pObject instanceof Number)
        {
            final Number n = (Number) pObject;

            double v = n.doubleValue();

            final int multiplicator  = (int) Math.pow(10, decimals);

            v *= multiplicator;

            v = Math.round(v);

            v /= multiplicator;

            return String.valueOf(v) + "%";
        }
        else
        {
            return new DefaultToString().toString(pObject);
        }
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
