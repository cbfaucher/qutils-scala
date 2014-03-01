package com.quartz.qutilities.events;

import java.awt.event.ActionEvent;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class QActionEvent extends QEvent
{
    final public ActionEvent actionEvent;

    public QActionEvent(Object source, ActionEvent pActionEvent)
    {
        super(source);
        actionEvent = pActionEvent;
    }
}
