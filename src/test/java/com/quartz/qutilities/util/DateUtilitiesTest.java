/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.util;

import org.junit.Test;
import junit.framework.TestSuite;
import junit.framework.JUnit4TestAdapter;
import com.quartz.qutilities.unittests.QTestCase;
import org.joda.time.YearMonthDay;
import org.junit.Assert;

/**
 * Unit Test cases for {@link DateUtilities}
 *
 * @author lmcchbf
 * @since 10-Mar-2007
 */
public class DateUtilitiesTest extends QTestCase
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS
	
    static public final junit.framework.Test suite()
    {
        return new JUnit4TestAdapter(DateUtilitiesTest.class);
    }	

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public DateUtilitiesTest()
    {
        super();
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    @Test
    public void test_ctor_string_1() throws Exception
    {
        Assert.assertEquals(new YearMonthDay(2007, 9, 9), DateUtilities.toYearMonthDay("2007/09/09"));
    }

    @Test
    public void test_ctor_string_2() throws Exception
    {
        Assert.assertEquals(new YearMonthDay(2007, 9, 9), DateUtilities.toYearMonthDay("2007-09-09"));
    }

    @Test
    public void test_ctor_string_3() throws Exception
    {
        Assert.assertEquals(new YearMonthDay(1999, 1, 3), DateUtilities.toYearMonthDay("1999-1-3"));
    }

    @Test
    public void test_ctor_string_4() throws Exception
    {
        Assert.assertEquals(new YearMonthDay(2004, 10, 31), DateUtilities.toYearMonthDay("2004/10/31"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ctor_string_5() throws Exception
    {
        DateUtilities.toYearMonthDay("2004/10/31-5");
        Assert.fail("Exception expected.");
    }


    ///////////////////////////////////////
    ////    INNER CLASSES
}
