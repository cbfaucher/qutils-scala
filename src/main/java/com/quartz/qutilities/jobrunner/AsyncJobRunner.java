package com.quartz.qutilities.jobrunner;

import java.util.List;
import java.util.ArrayList;

/**
 * User: belanj03
 * Date: 2006-10-18
 * Time: 15:54:08
 */
public class AsyncJobRunner implements JobRunner {

    final private List jobs = new ArrayList();
    private boolean stopped = false;
    private int numthreads;
    private int threadsparked = 0;
    private int maxqueuesize = 0;

    private class JobRunningRunnable implements Runnable {
        public void run() {

            while (true) {

                Job jobtorun;
                synchronized (jobs) {

                    if (stopped == true) {

                        threadsparked ++;
                        notifyMasterThread();
                        return;
                    }

                    if (jobs.size() == 0) {

                        try {

                            threadsparked ++;
                            notifyMasterThread();
                            jobs.wait();
                            threadsparked --;
                        } catch (InterruptedException e) {
                            threadsparked --;
                            stopped = true;
                            jobs.notifyAll();
                            return;
                        }
                    }

                    if ( jobs.size() != 0 ) {

                        jobtorun = (Job) jobs.remove(0);
                    } else {

                        continue;
                    }
                }

                runOneJob(jobtorun);
            }
        }

        private void notifyMasterThread() {

            synchronized (AsyncJobRunner.this) {
                AsyncJobRunner.this.notifyAll();
            }
        }

        private void runOneJob(Job jobtorun) {
            try {
                try {

                    Object o = jobtorun.runJob();
                    jobtorun.onCleanExit(o);
                } catch (Exception e) {

                    jobtorun.onException(e);
                }
                finally
                {
                    jobtorun.onFinally();
                }
            } catch(Exception e) {

                e.printStackTrace();
            }
        }
    }

    public AsyncJobRunner(int numthreads, int maxqueuesize) {

        this.numthreads = numthreads;
        this.maxqueuesize = maxqueuesize;
        startThreads(numthreads);
    }

    private void startThreads(int size) {

        for (int a = 0; a < size; a ++) {

            Thread t = new Thread(new JobRunningRunnable());
            t.setName("ThreadPool-" + a);
            t.setDaemon(true);
            t.start();
        }
    }

    public void runJob(Job job) {

        synchronized (this) {

            while(true) {
                if ( jobs.size() >= maxqueuesize ) {

                    try {
                        this.wait();
                    } catch (InterruptedException e) {

                    }
                } else {

                    break;
                }
            }

            jobs.add(job);
        }

        synchronized(jobs) {
            jobs.notify();
        }
    }

    public void waitForJobs() throws InterruptedException {

        synchronized(this) {

            while(true) {
                if ( jobs.size() == 0 && threadsparked == numthreads ) {

                    return;
                }
                this.wait();
            }
        }
    }

    public void stop() {

        synchronized(jobs) {

            stopped = true;
            jobs.notifyAll();
        }
    }


    public static void main(String[] args) throws Exception {

        AsyncJobRunner asjr = new AsyncJobRunner(5, 3);


        for ( int a = 0; a < 10; a ++ ) {
            asjr.runJob(new DefaultJob() {

                public Object runJob() throws Exception {
                    Thread.sleep(3000);
                    System.err.println(Thread.currentThread() + " finished ");
                    return null;
                }
            });
        }

        asjr.waitForJobs();
        System.err.println("done waiting");

        System.in.read();
    }
}
