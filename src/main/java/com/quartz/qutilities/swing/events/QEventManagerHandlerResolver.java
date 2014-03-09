/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing.events;

/**
 * A resolver that returns the {@link QEventHandler} for the given name
 *
 * @author Christian
 * @since Quartz...
 */
public interface QEventManagerHandlerResolver
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    /**
     *
     * @param pName Handler name
     * @return THe handler for the name, null if not found
     */
    public QEventHandler resolve(String pName) throws QEventManagerException;

    ///////////////////////////////////////
    ////    INNER CLASSES
}
