/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.pool;

import com.quartz.qutilities.logging.ILog;
import com.quartz.qutilities.logging.LogManager;

import java.util.List;
import java.util.ArrayList;

/**
 * A pool of worker threads
 *
 * @author Christian
 * @since Quartz...
 */
final public class ThreadPool
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    static private final ILog LOG = LogManager.getLogger(ThreadPool.class);

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    final private int   numberOfThreads;
    final private List<PooledThread> availableThreads = new ArrayList<PooledThread>();
    private boolean isClosed = false;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public ThreadPool(int pNumberOfThreads)
    {
        numberOfThreads = pNumberOfThreads;

        LOG.info("Thread Pool size: " + pNumberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) new PooledThread(this);
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public boolean isClosed()
    {
        return isClosed;
    }

    public int getNumberOfThreads()
    {
        return numberOfThreads;
    }

    synchronized public int getNumberOfAvailableThreads()
    {
        return availableThreads.size();
    }

    synchronized void notifyAvailable(PooledThread pPooledThread)
    {
        availableThreads.add(pPooledThread);

        this.notifyAll();
    }

    synchronized public PooledThread getThread() throws InterruptedException
    {
        while (availableThreads.isEmpty())
        {
            this.wait();
        }

        return availableThreads.remove(0);
    }

    synchronized public void waitForAvailable(int pNumberAvailable) throws InterruptedException
    {
        while (availableThreads.size() < pNumberAvailable)
        {
            this.wait();
        }
    }

    synchronized public void close()
    {
        while (availableThreads.size() < numberOfThreads)
        {
            try
            {
                this.wait(500);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        for (PooledThread t : availableThreads)
        {
            t.close();
        }

        isClosed = true;
    }

    synchronized public void removeMe(PooledThread pPooledThread)
    {
        availableThreads.remove(pPooledThread);
    }


    ///////////////////////////////////////
    ////    INNER CLASSES

}
