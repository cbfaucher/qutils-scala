/*
 * Copyright (c) 2006 Your Corporation. All Rights Reserved.
 */
package com.quartz.qutilities.swing.menus;

import com.quartz.qutilities.swing.menus.AbstractXmlMenuItem;

import javax.swing.*;

/**
 * INSERT YOUR COMMENT HERE
 *
 * @author fauchc02
 * @since 2006-12-04 14:12:29
 */
public class XmlCheckboxMenuItem extends AbstractXmlMenuItem
{
    public XmlCheckboxMenuItem()
    {
        super(new JCheckBoxMenuItem());
    }

    public void setState(String pState)
    {
        ((JCheckBoxMenuItem) getMenuItem()).setState(new Boolean(pState).booleanValue());
    }
}
