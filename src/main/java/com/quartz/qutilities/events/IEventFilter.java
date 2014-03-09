package com.quartz.qutilities.events;

/**
 * {@link com.quartz.qutilities.events.IEventListener}s that wish to filter events prior to receiving them can
 * implement this interface, which will be called by {@link QEventDispatcher} before sending new events.
 *
 * @see InstanceOfEventFilter
 *
 * @author Christian
 * @since Quartz...
 */
public interface IEventFilter
{
    boolean accepts(QEvent pEvent);
}
