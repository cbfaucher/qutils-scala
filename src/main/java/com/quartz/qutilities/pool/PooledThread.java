/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.pool;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class PooledThread extends Thread
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    final private ThreadPool threadPool;

    private Runnable task = null;
    private boolean finished = false;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public PooledThread(ThreadPool pThreadPool)
    {
        threadPool = pThreadPool;

        this.setDaemon(true);

        this.start();
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    synchronized public void executeTask(Runnable pRunnable) throws IllegalStateException
    {
        if (task != null) throw new IllegalStateException("Already running a task!");

        task = pRunnable;

        this.notify();
    }

    synchronized void close()
    {
        finished = true;

        this.notify();
    }

    public void run()
    {
        while (!finished)
        {
            synchronized(this)
            {
                try
                {
                    threadPool.notifyAvailable(this);
                    this.wait();
                }
                catch (InterruptedException e)
                {
                    //  ok
                }
            }

            if (task != null)
            {
                try
                {
                    task.run();
                }
                catch (Exception e)
                {
                    e.printStackTrace(System.err);
                }
                finally
                {
                    task = null;
                }
            }
        }

        threadPool.removeMe(this);

        System.out.println("Thread " + getName() + " leaving");
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
