/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.quartz.qutilities.swing.menus;

import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * INSERT YOUR COMMENT HERE
 *
 * @author fauchc02
 * @since 2005-07-14 14:41:32
 */
public class XmlMenu extends JMenu implements IMenuHandlerAware, IMenuHandler
{
    final private Map buttonGroups = new HashMap();
    final private Map<JMenuItem, AbstractXmlMenuItem> menuItems = new HashMap<JMenuItem, AbstractXmlMenuItem>();

    private String      id = null;
    private IMenuHandler menuHandler;

    public XmlMenu()
    {
    }

    public void setMenuHandler(IMenuHandler pHandler)
    {
        menuHandler = pHandler;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String pId)
    {
        id = pId;
    }

    public void setMnemonic(String pMnemonic)
    {
        if (pMnemonic.length() >= 1)
        {
            setMnemonic(pMnemonic.charAt(0));
        }
    }

    public JMenuItem add(final JMenuItem menuItem)
    {
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                final AbstractXmlMenuItem xmlItem = menuItems.get(menuItem);

                menuHandler.handleMenuAction(menuItem, e, xmlItem != null ? xmlItem.getActionListener() : null);
            }
        });

        return super.add(menuItem);
    }

    public void add(XmlMenu pMenu)
    {
        super.add(pMenu);      
        pMenu.setMenuHandler(this);
    }

    public JMenuItem add(AbstractXmlMenuItem pXmlMenuItem)
    {
        final JMenuItem menuItem = pXmlMenuItem.getMenuItem();
        pXmlMenuItem.setMenuHandler(this);

        if (pXmlMenuItem instanceof XmlRadioMenuItem)
        {
            final XmlRadioMenuItem radioMI = (XmlRadioMenuItem) pXmlMenuItem;
            final String buttonGroupName = radioMI.getButtonGroupName();
            if (StringUtils.isBlank(buttonGroupName)) throw new IllegalStateException("Button Group Name not set...");
            radioMI.setButtonGroup(fetchButtonGroup(buttonGroupName));
        }

        menuItems.put(menuItem, pXmlMenuItem);

        return add(menuItem);
    }

    public String toString()
    {
        final Component[] components = getPopupMenu().getComponents();
        final ArrayList children = new ArrayList();

        for (int i = 0; i < components.length; i++)
        {
            final Component component = components[i];
            children.add(component);
        }
        return "XmlMenu:" + getText() + "/Mnemonic=" + getMnemonic() + "/Children" + children;
    }

    public void handleMenuAction(JMenuItem pMenuItem, ActionEvent pEvent, ActionListener pActionClass)
    {
        //xmlMenuBar.handleMenuAction(pMenuItem, pEvent, pActionClass);
        menuHandler.handleMenuAction(pMenuItem, pEvent, pActionClass);
    }

    private ButtonGroup fetchButtonGroup(String pGroupName)
    {
        ButtonGroup group = (ButtonGroup) buttonGroups.get(pGroupName);

        if (group == null)
        {
            group = new ButtonGroup();
            buttonGroups.put(pGroupName, group);
        }

        return group;
    }    
}
