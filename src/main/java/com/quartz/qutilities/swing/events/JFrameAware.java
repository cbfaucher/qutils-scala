/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing.events;

import javax.swing.*;

/**
 * For {@link QEventHandler}s that need a reference to the owning JFrame.
 *
 * @author Christian
 * @since Quartz...
 */
public interface JFrameAware<T extends JFrame>
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public void setFrame(T pFrame);

    ///////////////////////////////////////
    ////    INNER CLASSES
}
