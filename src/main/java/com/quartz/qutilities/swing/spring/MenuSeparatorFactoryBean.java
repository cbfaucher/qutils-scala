/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing.spring;

import org.springframework.beans.factory.FactoryBean;

import javax.swing.*;

/**
 * A {@link FactoryBean} for separators
 *
 * @see javax.swing.JMenu#addSeparator()
 *
 * @author Christian
 * @since Quartz...
 */
public class MenuSeparatorFactoryBean implements FactoryBean
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public MenuSeparatorFactoryBean()
    {
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public Object getObject() throws Exception
    {
        return new Separator();
    }

    public Class getObjectType()
    {
        return Separator.class;
    }

    public boolean isSingleton()
    {
        return false;
    }

    ///////////////////////////////////////
    ////    INNER CLASSES

    static class Separator extends JMenuItem
    {
        public Separator()
        {
        }
    }
}
