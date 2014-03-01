/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing.events;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An {@link ActionListener} that deleguates to a {@link QEventManager}  the action handling.
 *
 * @author Christian
 * @since Quartz...
 */
final public class QEventActionListener implements ActionListener
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    final private JFrame        frame;
    final private QEventManager eventManager;
    final private String        command;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public QEventActionListener(QEventManager pEventManager)
    {
        this(null, pEventManager, null);
    }

    public QEventActionListener(QEventFrame pEventFrame)
    {
        this(pEventFrame.getFrame(), pEventFrame.getEventManager(), null);
    }

    public QEventActionListener(QEventFrame pEventFrame, String pCommand)
    {
        this(pEventFrame.getFrame(), pEventFrame.getEventManager(), pCommand);
    }

    public QEventActionListener(JFrame pFrame, QEventManager pEventManager)
    {
        this(pFrame, pEventManager, null);
    }

    public QEventActionListener(JFrame pFrame, QEventManager pEventManager, String pCommand)
    {
        if (pEventManager == null) throw new IllegalArgumentException("QEventManager is null.");

        frame = pFrame;
        eventManager = pEventManager;
        command = pCommand;
    }


    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public void actionPerformed(ActionEvent e)
    {
        eventManager.handleEvent(frame, e, command != null ? command : e.getActionCommand());
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
