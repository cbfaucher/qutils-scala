/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing.spring;

import com.quartz.qutilities.logging.ILog;
import com.quartz.qutilities.logging.LogManager;
import com.quartz.qutilities.swing.events.QEventManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class SpringFrame extends JFrame implements BeanFactoryAware
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    static private final ILog LOG = LogManager.getLogger(SpringFrame.class);

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    protected BeanFactory beanFactory = null;
    protected QEventManager eventManager = null;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public SpringFrame() throws HeadlessException
    {
    }


    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public void setWindowListener(WindowListener pListener)
    {
        addWindowListener(pListener);
    }

    public void setBeanFactory(BeanFactory pBeanFactory) throws BeansException
    {
        LOG.info("Spring BeanFactory SET!");
        this.beanFactory = pBeanFactory;
    }

    public void setComponent(Component c)
    {
        add(c);
    }

    public void setWidth(int pWidth)
    {
        setSize(pWidth, getHeight());
    }

    public void setHeight(int pHeight)
    {
        setSize(getWidth(), pHeight);
    }

    public void setX(int pX)
    {
        setLocation(pX, getY());
    }

    public void setY(int pY)
    {
        setLocation(getX(), pY);
    }

    public void setSwingMenuBar(JMenuBar pMenuBar)
    {
        LOG.info("Setting JMenuBar");
        setJMenuBar(pMenuBar);
    }

    public QEventManager getEventManager()
    {
        return eventManager;
    }

    public void setEventManager(QEventManager pEventManager)
    {
        eventManager = pEventManager;
    }
}
