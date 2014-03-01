/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing.spring;

import javax.swing.*;
import java.awt.*;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class SpringPanel extends JPanel
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public SpringPanel()
    {
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public void setComponents(Component ... pComponents)
    {
        for (Component c : pComponents) add(c);
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
