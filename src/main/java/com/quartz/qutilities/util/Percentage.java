package com.quartz.qutilities.util;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class Percentage
{
    final private double percent;

    public Percentage(double pPercent)
    {
        percent = pPercent;
    }

    public double getInternal()
    {
        return percent;
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Percentage that = (Percentage) o;

        if (Double.compare(that.percent, percent) != 0) return false;

        return true;
    }

    public int hashCode()
    {
        long temp = percent != +0.0d ? Double.doubleToLongBits(percent) : 0L;
        return (int) (temp ^ (temp >>> 32));
    }

    public String toString()
    {
        return percent + "%";
    }
}
