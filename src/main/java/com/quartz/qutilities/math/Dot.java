/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.math;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class Dot
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    final public float x;
    final public float y;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public Dot(float pX, float pY)
    {
        x = pX;
        y = pY;
    }
    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Dot)) return false;

        final Dot dot = (Dot) o;

        if (x != dot.x) return false;
        if (y != dot.y) return false;

        return true;
    }

    public int hashCode()
    {
        int result;
        result = x != +0.0f ? Float.floatToIntBits(x) : 0;
        result = 29 * result + y != +0.0f ? Float.floatToIntBits(y) : 0;
        return result;
    }

    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
    ///////////////////////////////////////
    ////    INNER CLASSES
}
