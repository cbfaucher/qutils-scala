/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing.spring;

import javax.swing.*;
import java.util.List;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class MenuBarBean extends JMenuBar
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public MenuBarBean()
    {
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public void setMenus(List<JMenu> pMenus)
    {
        for (JMenu m : pMenus) add(m);
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
