package com.quartz.qutilities.logging;

import org.apache.log4j.Logger;

/**
 * Implementation of Log interface
 */
final class Log4jImpl implements ILog
{

    private Logger l;
    
    /**
     * Create a Logger instance for a specified Class.
     * 
     * @param c The class
     */
    Log4jImpl(final Class c)
    {
        this.l = Logger.getLogger(c);
    }
    
    /**
     * Create a Logger instance for specified logger name.
     * 
     * @param logger The logger name
     */
    Log4jImpl(final String logger)
    {
        this.l = Logger.getLogger(logger);
    }

    /**
     * @see com.quartz.qutilities.logging.ILog#debug(java.lang.Object)
     */
    public void debug(final Object o)
    {
        l.debug(o);
    }
    
    /**
     * @see com.quartz.qutilities.logging.ILog#info(java.lang.Object)
     */
    public void info(final Object o)
    {
        l.info(o);
    }

    /**
     * @see com.quartz.qutilities.logging.ILog#warn(java.lang.Object)
     */
    public void warn(final Object pMsg)
    {
        l.warn(pMsg);
    }
    
    /**
     * @see com.quartz.qutilities.logging.ILog#warn(java.lang.Object, java.lang.Throwable)
     */
    public void warn(final Object pMsg, final Throwable pThrowable)
    {
        l.warn(pMsg, pThrowable);
    }

    /**
     * @see com.quartz.qutilities.logging.ILog#error(java.lang.Object)
     */
    public void error(final Object o)
    {
        l.error(o);
    }

    /**
     * @see com.quartz.qutilities.logging.ILog#error(java.lang.Object, java.lang.Throwable)
     */
    public void error(final Object o, final Throwable pThrowable)
    {
        l.error(o, pThrowable);
    }

    /**
     * @see com.quartz.qutilities.logging.ILog#fatal(java.lang.Object)
     */
    public void fatal(final Object o)
    {
        l.fatal(o);
    }

    /**
     * @see com.quartz.qutilities.logging.ILog#fatal(java.lang.Object, java.lang.Throwable)
     */
    public void fatal(final Object o, final Throwable pThrowable)
    {
        l.fatal(o, pThrowable);
    }
    
    /**
     * @see com.quartz.qutilities.logging.ILog#debug(java.lang.Object, java.lang.Throwable)
     */
    public void debug(final Object pO, final Throwable pThrowable)
    {
        l.debug(pO, pThrowable);
    }

    public boolean isDebugEnabled()
    {
        return l.isDebugEnabled();
    }

    public boolean isInfoEnabled()
    {
        return l.isInfoEnabled();
    }
}