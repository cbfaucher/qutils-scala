/*
 * Copyright (c) 2006 Your Corporation. All Rights Reserved.
 */
package com.quartz.qutilities.swing.menus;

import javax.swing.*;

/**
 * INSERT YOUR COMMENT HERE
 *
 * @author fauchc02
 * @since 2006-12-04 14:50:46
 */
public class XmlRadioMenuItem extends AbstractXmlMenuItem
{
    private String buttonGroupName;

    public XmlRadioMenuItem()
    {
        super(new JRadioButtonMenuItem());
    }

    public void setButtonGroupName(String pButtonGroupName)
    {
        buttonGroupName = pButtonGroupName;
    }

    public String getButtonGroupName()
    {
        return buttonGroupName;
    }

    void setButtonGroup(ButtonGroup pButtonGroup)
    {
        pButtonGroup.add(getMenuItem());
    }
}
