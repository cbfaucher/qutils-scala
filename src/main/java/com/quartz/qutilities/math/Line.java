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
public class Line
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    static public Line compute(float x1, float y1, float x2, float y2)
    {
        final float m = (y2 - y1) / (x2 - x1);

        /*
        y1 = m * x1 + b

        */
        final float b = (y1 - m * x1);

        return new Line(m, b);
    }

    static public Line compute(Dot pDot1, Dot pDot2)
    {
        return compute(pDot1.x, pDot1.y, pDot2.x, pDot2.y);
    }

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private final float m;
    private final float b;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public Line(float pM, float pB)
    {
        m = pM;
        b = pB;
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public float compute(float pX){
        return (m * pX + b);
    }

    public Line derive(float mDelta, float bDelta)
    {
        return new Line(m + mDelta, b + bDelta);
    }

    public Dot intersects(Line pOther)
    {
        /*
        m1x + b1 = m2x + b2
        m1x - m2x = b2 - b1
        x = (b2 - b1)/(m1 - m2)
        */

        final float x = (pOther.b - this.b) / (this.m - pOther.m);
        return new Dot(x, compute(x));
    }

    /**
     * Calculates the value of X for the given Y
     * @param pY Y
     * @return The {@link Dot} with where value is (x, pY)
     */
    public Dot xAt(float pY)
    {
        //  y = mx + b
        //  => y - b = mx
        //  x = y-b / m
        return new Dot( (pY - b) / m, pY);
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Line)) return false;

        final Line line = (Line) o;

        if (b != line.b) return false;
        if (m != line.m) return false;

        return true;
    }

    public int hashCode()
    {
        int result;
        result = m != +0.0f ? Float.floatToIntBits(m) : 0;
        result = 29 * result + b != +0.0f ? Float.floatToIntBits(b) : 0;
        return result;
    }

    public String toString()
    {
        return (m  + "*X + " + b);
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
