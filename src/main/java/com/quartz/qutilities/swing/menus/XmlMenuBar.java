/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.quartz.qutilities.swing.menus;

import com.quartz.qutilities.digester.DigesterHelper;
import com.quartz.qutilities.logging.ILog;
import com.quartz.qutilities.logging.LogManager;
import com.quartz.qutilities.xml.ResourceEntityResolver;
import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * INSERT YOUR COMMENT HERE
 *
 * @author fauchc02
 * @since 2005-07-14 14:23:20
 */
public class XmlMenuBar extends JMenuBar implements IMenuHandler
{
    static private final ILog LOG = LogManager.getLogger(XmlMenuBar.class);

    static public void main(String[] pArgs) throws Exception
    {
        XmlMenuBar menuBar = XmlMenuBar.load(
                XmlMenuBar.class.getResource("xml-menu-sample.xml"),
                null/*not used*/,
                true);
        System.out.println(menuBar);
    }


    static public XmlMenuBar load(URL pMenuDescriptorURL, boolean pValidate) throws IOException, SAXException
    {
        return load(pMenuDescriptorURL, null, pValidate);
    }

    static public XmlMenuBar load(URL pMenuDescriptorURL, ActionListener pMenuActionListener, boolean pValidate) throws IOException, SAXException
    {
        try
        {
            LOG.debug("Loading AssociationHandlers...");
            final Digester digester = DigesterHelper.loadFromXmlRuleSet(XmlMenuBar.class.getResource("XmlMenu-digester-rules.xml"));
            digester.setEntityResolver(new ResourceEntityResolver("/com/quartz/qutilities/swing/menus"));
            digester.setValidating(pValidate);
            final InputStream is = pMenuDescriptorURL.openStream();
            if (is == null) throw new IOException("Invalid URL: " + pMenuDescriptorURL.toString());

            final XmlMenuBar menuBar = (XmlMenuBar) digester.parse(is);
            menuBar.setDefaultActionListener(pMenuActionListener);
            LOG.info("XML Menu Bar LOADED successfully.");
            return menuBar;
        }
        catch (ParserConfigurationException e)
        {
            throw new SAXException(e);
        }
    }

    final private Map menus = new HashMap();
    private ActionListener menuActionListener;

    public XmlMenuBar()
    {
    }

    public JMenu add(XmlMenu pMenu)
    {
        System.out.println("XmlMenuBar: Adding Menu: " + pMenu.getText());

        pMenu.setMenuHandler(this);

        menus.put(pMenu.getText(), pMenu);

        return super.add(pMenu);
    }

    public String toString()
    {
        final Component[] components = getComponents();
        final ArrayList children = new ArrayList();

        for (int i = 0; i < components.length; i++)
        {
            final Component component = components[i];
            children.add(component);
        }
        return "XmlMenuBar" + children.toString();
    }

    private void setDefaultActionListener(ActionListener pMenuActionListener)
    {
        menuActionListener = pMenuActionListener;
    }

    public void handleMenuAction(JMenuItem pMenuItem, ActionEvent pEvent, ActionListener pActionListener)
    {
        if (pActionListener != null)
        {
            pActionListener.actionPerformed(pEvent);
        }
        else
        {
            if (menuActionListener != null)
            {
                menuActionListener.actionPerformed(pEvent);
            }
            else
            {
                LOG.warn("Cannot handle ActionEvent: " + pEvent.getActionCommand());
            }
        }
    }
}
