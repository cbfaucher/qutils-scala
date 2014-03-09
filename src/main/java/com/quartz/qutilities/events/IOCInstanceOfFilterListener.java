package com.quartz.qutilities.events;

import org.springframework.beans.factory.InitializingBean;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public abstract class IOCInstanceOfFilterListener extends DefaultInstanceOfFilterListener implements InitializingBean
{
    private QEventDispatcher eventDispatcher;

    public IOCInstanceOfFilterListener()
    {
    }

    public void setEventDispatcher(QEventDispatcher pEventDispatcher)
    {
        eventDispatcher = pEventDispatcher;
    }

    public void afterPropertiesSet() throws Exception
    {
        if (eventDispatcher == null) throw new IllegalStateException("EventDispatcher not set.");
        eventDispatcher.addListener(this);
    }
}
