/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing;

import com.quartz.qutilities.logging.ILog;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.commons.lang.exception.ExceptionUtils;

import javax.swing.*;
import java.util.Date;

/**
 * A Log4J appender that writes logging events to a given {@link javax.swing.JTextArea}
 *
 * @author fauchc02
 * @since 2007-06-11 09:17:49
 */
public class TextAreaAppender extends AppenderSkeleton
{
	static private final ILog LOG = com.quartz.qutilities.logging.LogManager.getLogger(TextAreaAppender.class);

	final private JTextArea textArea;
	final private Logger logger;

	/**
	 * Automatically atatches this appender to the Root Logger.
	 *
	 * @param pTextArea Related TextArea
	 */
	public TextAreaAppender(final JTextArea pTextArea)
	{
		this(pTextArea, null, true);
	}

	/**
	 * Automatically attaches this appender to the named logger.
	 *
	 * @param pTextArea Related TextArea
	 * @param pLoggerName Logger Name, null for root logger.
	 */
    public TextAreaAppender(final JTextArea pTextArea, final String pLoggerName)
	{
		this(pTextArea, pLoggerName, true);
	}

	/**
	 * Constructor
	 *
	 * @param pTextArea Related TextArea
	 * @param pLoggerName Logger Name, null for root logger
	 * @param pAttachIt if true, {@link #attach()} is called.
	 */
	public TextAreaAppender(final JTextArea pTextArea, final String pLoggerName, final boolean pAttachIt)
	{
		textArea = pTextArea;

		logger = (pLoggerName != null ? LogManager.getLogger(pLoggerName) : LogManager.getRootLogger());

		if (pAttachIt) attach();

		LOG.info("TextAreaAppender initialised (Logger Name=" + pLoggerName + ")");		
	}

	/**
	 * Dumps the {@link org.apache.log4j.spi.LoggingEvent} to {@link #textArea}
	 *
	 * @param pLoggingEvent Logging event
	 */
	protected void append(final LoggingEvent pLoggingEvent)
	{
		String msg;

		if (getLayout() != null)
		{
			msg = layout.format(pLoggingEvent);
		}
		else
		{
			msg = new StringBuffer().append(new Date(pLoggingEvent.timeStamp)).
					append(" ").append(pLoggingEvent.level).
					append(" ").append(pLoggingEvent.getRenderedMessage()).toString();
		}

		if (!msg.endsWith("\n'")) msg += "\n";

        if (pLoggingEvent.getThrowableInformation() != null)
        {
            msg += ExceptionUtils.getFullStackTrace(pLoggingEvent.getThrowableInformation().getThrowable());
        }

		final String msg1 = msg;
		SwingUtils.invokeLaterIfNeeded(new Runnable() {

			public void run()
			{
				textArea.append(msg1);
			}
		});
	}

	/**
	 * Does nothing
	 */
	public void close()
	{
		//  nothing to do
	}

	/**
	 * Attaches this appender to the associated logger.
	 */
	public void attach()
	{
		logger.addAppender(this);
	}

	/**
	 * Removes this appender from the associated logger.
	 */
	public void detach()
	{
		logger.removeAppender(this);
	}

	/**
	 *
	 * @return true
	 */
	public boolean requiresLayout()
	{
		return true;  //needs layout
	}
}
