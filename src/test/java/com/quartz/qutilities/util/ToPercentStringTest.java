/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.util;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.JUnit4TestAdapter;
import com.quartz.qutilities.unittests.QTestCase;
import org.junit.Before;
import org.junit.Assert;

/**
 * Unit Test cases for {@link ToPercentString}
 *
 * @author lmcchbf
 * @since 13-Jul-2006
 */
public class ToPercentStringTest extends QTestCase
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS
	
    static public final Test suite()
    {
        return new JUnit4TestAdapter(ToPercentStringTest.class);
    }	

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private ToPercentString toPercentString = null;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public ToPercentStringTest()
    {
        super();
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    @Before
    public void setUp() throws Exception
    {
        toPercentString = new ToPercentString(3);
    }

    @org.junit.Test
    public void test_toString() throws Exception
    {
    	Assert.assertEquals("3.14%", toPercentString.toString(3.14f));
    	Assert.assertEquals("3.142%", toPercentString.toString(3.1416d));
    	Assert.assertEquals("123.0%", toPercentString.toString(123l));

    	Assert.assertEquals("hello", toPercentString.toString("hello"));
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
