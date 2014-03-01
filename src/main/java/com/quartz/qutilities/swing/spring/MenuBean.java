/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing.spring;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.BeansException;

import javax.swing.*;
import java.util.List;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class MenuBean extends JMenu implements BeanFactoryAware
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    protected BeanFactory beanFactory;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public MenuBean()
    {
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public void setBeanFactory(BeanFactory pBeanFactory) throws BeansException
    {
        beanFactory = pBeanFactory; 
    }

    public void setMenuItems(List<JComponent> pMenuItems)
    {
        for (JComponent c : pMenuItems)
        {
            if (c instanceof JSeparator)
            {
                addSeparator();
            }
            else if (c instanceof MenuSeparatorFactoryBean.Separator)
            {
                addSeparator();
            }
            else
            {
                add(c);
            }
        }
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
