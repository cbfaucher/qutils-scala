package com.quartz.qutilities.events;

import org.springframework.beans.factory.InitializingBean;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class ActionListenerDecorator implements ActionListener, InitializingBean
{
    private QEventDispatcher eventDispatcher;

    public ActionListenerDecorator()
    {
    }

    public void setEventDispatcher(QEventDispatcher pEventDispatcher)
    {
        eventDispatcher = pEventDispatcher;
    }

    public void afterPropertiesSet() throws Exception
    {
        if (eventDispatcher == null) throw new IllegalStateException("EventDispatcher not set.");
    }

    public void actionPerformed(ActionEvent e)
    {
        eventDispatcher.fireEvent(new QActionEvent(e.getSource(), e));
    }
}
