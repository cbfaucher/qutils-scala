/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing;

import com.quartz.qutilities.logging.ILog;
import com.quartz.qutilities.logging.LogManager;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public interface DefaultStatusBarLogger
{
    String DEFAULT_STATUS_BAR_LOGGER_NAME = "STATUS-BAR";

    ILog STATUS = LogManager.getLogger(DEFAULT_STATUS_BAR_LOGGER_NAME);
}
