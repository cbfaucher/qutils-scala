/*
 * Copyright (c) 2006 Your Corporation. All Rights Reserved.
 */
package com.quartz.qutilities.swing.menus;

import com.quartz.qutilities.lang.ClassUtilities;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * INSERT YOUR COMMENT HERE
 *
 * @author fauchc02
 * @since 2006-12-04 14:46:42
 */
abstract class AbstractXmlMenuItem implements IMenuHandlerAware
{
    final private JMenuItem menuItem;
    private String id;
    private ActionListener actionListener;
    private IMenuHandler menuHandler;

    protected AbstractXmlMenuItem(final JMenuItem pMenuItem)
    {
        menuItem = pMenuItem;

/*
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                menuHandler.handleMenuAction(pMenuItem, e, actionClass);
            }
        });
*/
    }

    public void setMenuHandler(IMenuHandler pHandler)
    {
        menuHandler = pHandler;
    }

    JMenuItem getMenuItem()
    {
        return menuItem;
    }

    final public String getId()
    {
        return id;
    }

    final public void setId(String pId)
    {
        id = pId;
    }

    final public void setActionListener(String pActionListener) throws ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        if (StringUtils.isBlank(pActionListener)) return;

        actionListener = ClassUtilities.create(pActionListener, ActionListener.class);
    }

    final public void setMnemonic(String pMnemonic)
    {
        if (pMnemonic.length() >= 1)
        {
            menuItem.setMnemonic(pMnemonic.charAt(0));
        }
    }

    public void setActionCommand(String pCommand)
    {
        menuItem.setActionCommand(pCommand);
    }

    public void setText(String pText)
    {
        menuItem.setText(pText);
    }

    public void setAccelerator(String pAccelerator)
    {
        menuItem.setAccelerator(KeyStroke.getKeyStroke(pAccelerator));
    }

    public ActionListener getActionListener()
    {
        return actionListener;
    }
}
