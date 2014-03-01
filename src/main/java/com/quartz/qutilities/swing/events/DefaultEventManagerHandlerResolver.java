/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing.events;

import java.util.Map;
import java.util.HashMap;

/**
 * Looks in a pre-configured Map for the {@link QEventHandler} .
 *
 * @author Christian
 * @since Quartz...
 */
public class DefaultEventManagerHandlerResolver implements QEventManagerHandlerResolver
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    final private Map<String, QEventHandler> handlers = new HashMap<String, QEventHandler>();

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public DefaultEventManagerHandlerResolver()
    {
    }
    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public void setHandlers(Map<String, QEventHandler> pHandlers)
    {
        clear();

        for (String name : pHandlers.keySet())
        {
            handlers.put(name, pHandlers.get(name));
        }
    }

    public void addHandler(String pCommand, QEventHandler pHandler)
    {
        handlers.put(pCommand, pHandler);
    }

    public void removeHandler(String pCommand)
    {
        handlers.remove(pCommand);
    }

    public void clear()
    {
        handlers.clear();
    }

    public QEventHandler resolve(String pName)
    {
        return handlers.get(pName);
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
