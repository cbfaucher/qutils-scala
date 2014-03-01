/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.pool;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.JUnit4TestAdapter;
import com.quartz.qutilities.unittests.QTestCase;
import com.mockobjects.dynamic.Mock;
import org.junit.Before;
import org.junit.Assert;

/**
 * Unit Test cases for {@link PooledThread}
 *
 * @author lmcchbf
 * @since 20-Jul-2006
 */
public class PooledThreadTest extends QTestCase
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS
	
    static public final Test suite()
    {
        return new JUnit4TestAdapter(PooledThreadTest.class);
    }	

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private ThreadPool   threadPool = null;
    private PooledThread pooledThread = null;
    private Mock mockRunnable = null;
    private Runnable runnable = null;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public PooledThreadTest()
    {
        super();
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    @Before
    public void setUp() throws Exception
    {
        threadPool = new ThreadPool(0/*no thread for test*/);
        pooledThread = new PooledThread(threadPool);
        mockRunnable = new Mock(Runnable.class);
        runnable = (Runnable) mockRunnable.proxy();
    }

    public void tearDown()
    {
        threadPool = null;
        pooledThread = null;
        mockRunnable = null;
        runnable = null;
    }

    @org.junit.Test
    public void test_execute() throws Exception
    {
        try
        {
            mockRunnable.expect("run");

            pooledThread.executeTask(runnable);

            Thread.sleep(1000); //  give time to call runnable

            mockRunnable.verify();
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);
        }
    }

    @org.junit.Test
    public void test_close() throws Exception
    {
    	pooledThread.close();

        pooledThread.join(2000);

        Assert.assertEquals(false, pooledThread.isAlive());
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
