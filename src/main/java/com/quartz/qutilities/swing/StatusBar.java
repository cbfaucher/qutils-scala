/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class StatusBar extends JPanel
{
    private final JLabel statusLabel = new JLabel();
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public StatusBar()
    {
        this("Welcome...");
    }

    public StatusBar(String pText)
    {
        setLayout(new BorderLayout(5, 5));

        statusLabel.setFont(getFont().deriveFont(Font.ITALIC, 10.0f));
        statusLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        add(statusLabel, BorderLayout.CENTER);

        statusLabel.setText(pText);
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public void setStatus(String pText)
    {
        statusLabel.setForeground(Color.black);
        statusLabel.setText(pText);
    }

    public void setErrorStatus(String pMsg)
    {
        statusLabel.setForeground(Color.RED);
        statusLabel.setText(pMsg);
    }
}
