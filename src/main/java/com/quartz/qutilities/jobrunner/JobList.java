/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.jobrunner;

import java.util.List;
import java.util.ArrayList;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class JobList
{
    final private List<Job> jobs = new ArrayList<Job>();

    public JobList()
    {
    }

    synchronized public void add(Job pJob)
    {
        jobs.add(pJob);
    }

    synchronized public void remove(Job pJog)
    {
        jobs.remove(pJog);
        this.notify();
    }

    synchronized public void waitForJobs() throws InterruptedException
    {
        while (jobs.isEmpty() == false) wait();   
    }
}
