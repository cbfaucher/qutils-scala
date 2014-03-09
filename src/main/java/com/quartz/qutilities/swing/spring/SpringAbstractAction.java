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

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public abstract class SpringAbstractAction extends AbstractAction implements BeanFactoryAware
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    protected BeanFactory beanFactory = null;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    protected SpringAbstractAction()
    {
        
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    final public void setBeanFactory(BeanFactory pBeanFactory) throws BeansException
    {
        beanFactory = pBeanFactory;
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
