/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.math;

import com.quartz.qutilities.unittests.QTestCase;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import org.junit.Assert;

/**
 * Unit Test cases for {@link Line}
 *
 * @author lmcchbf
 * @since 25-Aug-2006
 */
public class LineTest extends QTestCase
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS
	
    static public final Test suite()
    {
        return new JUnit4TestAdapter(LineTest.class);
    }	

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public LineTest()
    {
        super();
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    @org.junit.Test
    public void test_compute() throws Exception
    {
    	final Line line = new Line(3, 2);

        Assert.assertEquals(2f, line.compute(0), 0.001);
        Assert.assertEquals(5f, line.compute(1), 0.001);
        Assert.assertEquals(-13f, line.compute(-5), 0.001);
    }

    @org.junit.Test
    public void test_derive() throws Exception
    {
        final Line line = new Line(5, -2);

        final Line other = line.derive(-1.5f, 1.5f);

        Assert.assertEquals(new Line(3.5f, -0.5f), other);
    }

    @org.junit.Test
    public void test_intersect_1() throws Exception
    {
    	final Line l1 = new Line(4f, 2f);
        final Line l2 = new Line(3f, 2f);

        Assert.assertEquals(new Dot(0f, 2f), l1.intersects(l2));
    }

    @org.junit.Test
    public void test_intersect_2() throws Exception
    {
    	final Line l1 = new Line(1f, 0f);
        final Line l2 = new Line(2f, -2f);

        Assert.assertEquals(new Dot(2f, 2f), l1.intersects(l2));
    }

    @org.junit.Test
    public void test_computeLine() throws Exception
    {
    	//  expect: -3x + 5
        final Dot dot1 = new Dot(5f, -10);
        final Dot dot2 = new Dot(8f, -19);

        Assert.assertEquals(new Line(-3f, 5), Line.compute(dot1, dot2));
    }

    @org.junit.Test
    public void test_xAt() throws Exception
    {
    	final Line l = new Line(-3f, 5);

        Assert.assertEquals(new Dot(0, 5), l.xAt(5));
        Assert.assertEquals(new Dot(-2, 11), l.xAt(11));
        Assert.assertEquals(new Dot(10, -25), l.xAt(-25));
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
