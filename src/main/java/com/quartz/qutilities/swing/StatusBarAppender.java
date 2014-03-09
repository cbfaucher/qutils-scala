/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

/**
 * A Log4J {@link org.apache.log4j.Appender} that writes {@link org.apache.log4j.spi.LoggingEvent}s to
 * a {@link com.quartz.qutilities.swing.StatusBar}.  Warnings and less are written as regular status
 * and ERROR and above as Error Status.
 * <p>
 * Default name for Status Logger is <tt>"STATUS"</tt>, but that can be specified.
 *
 * @see DefaultStatusBarLogger
 * @see DefaultStatusBarLogger#DEFAULT_STATUS_BAR_LOGGER_NAME
 * @see DefaultStatusBarLogger#STATUS
 *
 * @author fauchc02
 * @since 2007-06-11 13:05:20
 */
public class StatusBarAppender extends AppenderSkeleton
{

	final private StatusBar statusBar;
	final private Logger logger;

	/**
	 * Appender attached to the default status logger.
	 *
	 * @param pStatusBar Status bar
	 *
	 * @see DefaultStatusBarLogger#DEFAULT_STATUS_BAR_LOGGER_NAME
	 */
	public StatusBarAppender(final StatusBar pStatusBar)
	{
		this(pStatusBar, DefaultStatusBarLogger.DEFAULT_STATUS_BAR_LOGGER_NAME);
	}

	/**
	 * Appender attached to the named status logger.
	 * @param pStatusBar Status bar
	 * @param pStatusBarLoggerName Status logger name
	 */
	public StatusBarAppender(final StatusBar pStatusBar, final String pStatusBarLoggerName)
	{
		statusBar = pStatusBar;

		logger = LogManager.getLogger(pStatusBarLoggerName);

		attach();

		logger.setLevel(Level.INFO);
		logger.info("StatusBar appender initialised successfully.");
	}

	public void attach()
	{
		logger.addAppender(this);
	}

	public void detach()
	{
		logger.removeAppender(this);
	}

	public boolean requiresLayout()
	{
		return false;
	}

	protected void append(final LoggingEvent pLoggingEvent)
	{
		final Level actualLevel = pLoggingEvent.getLevel();

		final String msg = pLoggingEvent.getRenderedMessage();

		if (actualLevel == Level.ERROR
			|| actualLevel == Level.FATAL)
		{
			statusBar.setErrorStatus(msg);
		}
		else
		{
			statusBar.setStatus(msg);
		}
	}

	public void close()
	{
		//  nothing to do
	}
}
