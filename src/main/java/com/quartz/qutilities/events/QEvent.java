package com.quartz.qutilities.events;

import java.util.EventObject;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public abstract class QEvent extends EventObject
{
    public QEvent(Object source)
    {
        super(source);
    }
}
