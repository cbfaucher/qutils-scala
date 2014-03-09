package com.quartz.qutilities.jobrunner;

/**
 * User: belanj03
 * Date: 2006-10-25
 * Time: 13:24:34
 */
public class JobException extends RuntimeException {

    public JobException() {
    }

    public JobException(String message) {
        super(message);
    }

    public JobException(Throwable cause) {
        super(cause);
    }

    public JobException(String message, Throwable cause) {
        super(message, cause);
    }
}
