package com.quartz.qutilities.jobrunner;

/**
 * User: belanj03
 * Date: 2006-10-18
 * Time: 15:53:19
 */
public interface Job {

    Object runJob() throws Exception;

    void onCleanExit(Object o);

    void onException(Exception e);

    void onFinally();
}
