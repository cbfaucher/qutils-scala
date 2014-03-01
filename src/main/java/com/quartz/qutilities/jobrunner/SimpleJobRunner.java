package com.quartz.qutilities.jobrunner;

import com.quartz.qutilities.jobrunner.Job;
import com.quartz.qutilities.jobrunner.JobException;
import com.quartz.qutilities.jobrunner.JobRunner;

/**
 * User: belanj03
 * Date: 2006-10-18
 * Time: 17:25:12
 */
public class SimpleJobRunner implements JobRunner {

    public void runJob(Job job) {

        runOneJob(job);
    }

    private void runOneJob(Job jobtorun) throws JobException {
            try {

                Object o = jobtorun.runJob();
                jobtorun.onCleanExit(o);
            } catch (Exception e) {

                jobtorun.onException(e);
                throw new JobException(e);
            }
            finally
            {
                jobtorun.onFinally();
            }
    }

    public void waitForJobs() throws InterruptedException {

    }
}
