/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing.events;

import java.awt.*;
import java.util.EventObject;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @see JFrameAware
 *
 * @author Christian
 * @since Quartz...
 */
public interface QEventHandler
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    abstract public void handleEvent(QEventManager pEventManager, EventObject pEvent, String pCommand);

    ///////////////////////////////////////
    ////    INNER CLASSES
}
