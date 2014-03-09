package com.quartz.qutilities.jobrunner;

import com.quartz.qutilities.jobrunner.Job;

/**
 * User: belanj03
 * Date: 2006-10-18
 * Time: 15:51:30
 */
public interface JobRunner {

    public void runJob(Job job);
    public void waitForJobs() throws InterruptedException;
}
