package com.quartz.qutilities.events;

import java.util.Set;
import java.util.HashSet;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class QEventDispatcher
{
    final private Set<IEventListener> listeners = new HashSet<IEventListener>();

    public QEventDispatcher()
    {
        setListeners();
    }

    public QEventDispatcher(IEventListener ... pListeners)
    {
        setListeners(pListeners);
    }

    public void add(IEventListener pListener)
    {
        listeners.add(pListener);
    }

    public void setListeners(IEventListener ... pListeners)
    {
        listeners.clear();

        for (IEventListener l : pListeners) listeners.add(l);
    }

    public void fireEvent(QEvent pEvent)
    {
        if (pEvent == null) return;

        for (IEventListener l : listeners)
        {
            if (l instanceof IEventFilter)
            {
                final IEventFilter filter = (IEventFilter) l;
                if (!filter.accepts(pEvent)) continue;
            }

            l.onEvent(pEvent);
        }
    }

    public void addListener(IEventListener pListener)
    {
        if (pListener == null) return;
        listeners.add(pListener);
    }
}
