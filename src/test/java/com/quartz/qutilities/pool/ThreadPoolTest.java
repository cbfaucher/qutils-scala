/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.pool;

import junit.framework.TestSuite;
import junit.framework.JUnit4TestAdapter;
import com.quartz.qutilities.unittests.QTestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * Unit Test cases for {@link ThreadPool}
 *
 * @author lmcchbf
 * @since 20-Jul-2006
 */
public class ThreadPoolTest extends QTestCase
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS
	
    static public junit.framework.Test suite()
    {
        return new JUnit4TestAdapter(ThreadPoolTest.class);
    }	

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private ThreadPool   pool = null;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public ThreadPoolTest()
    {
        super();
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    @Before
    public void setUp() throws Exception
    {
        pool = new ThreadPool(3);
    }

    @Test
    public void test_close() throws Exception
    {
        Thread.sleep(1000);

        Assert.assertEquals(3, pool.getNumberOfThreads());
    	Assert.assertEquals(3, pool.getNumberOfAvailableThreads());
    	Assert.assertEquals(false, pool.isClosed());

        pool.close();

        Thread.sleep(1000); //give time to cleanup...

        Assert.assertEquals(true, pool.isClosed());
        Assert.assertEquals(3, pool.getNumberOfThreads());
        Assert.assertEquals(0, pool.getNumberOfAvailableThreads());
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
