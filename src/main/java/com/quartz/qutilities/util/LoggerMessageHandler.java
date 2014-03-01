/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.util;

import com.quartz.qutilities.logging.ILog;
import com.quartz.qutilities.logging.LogManager;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class LoggerMessageHandler implements MessageHandler
{
    static private final ILog LOG = LogManager.getLogger(LoggerMessageHandler.class);

    public LoggerMessageHandler()
    {
    }

    public void info(String pMsg)
    {
        LOG.info(pMsg);
    }

    public void error(String pMsg)
    {
        LOG.error(pMsg);
    }

    public void error(String pMsg, Throwable pThrowable)
    {
        LOG.error(pMsg, pThrowable);
    }
}
