/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.util;



/**
 * A default message handler that writes to system.out, system.err
 *
 * @author Christian
 * @since Quartz...
 */
public class DefaultMessageHandler implements MessageHandler
{
    public void info(String pMsg)
    {
        System.out.println(pMsg);
    }

    public void error(String pMsg)
    {
        System.err.println(pMsg);
    }

    public void error(String pMsg, Throwable pThrowable)
    {
        System.err.println(pMsg);
        if (pThrowable != null) pThrowable.printStackTrace(System.err);
    }
}
