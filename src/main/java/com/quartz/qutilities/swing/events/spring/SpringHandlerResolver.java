/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing.events.spring;

import com.quartz.qutilities.swing.events.QEventManagerHandlerResolver;
import com.quartz.qutilities.swing.events.QEventHandler;
import com.quartz.qutilities.swing.events.QEventManagerException;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.BeansException;

/**
 * A {@link QEventManagerHandlerResolver} that looks into the BeanFactory for a bean with given handler name.
 *
 * @author Christian
 * @since Quartz...
 */
public class SpringHandlerResolver implements QEventManagerHandlerResolver, BeanFactoryAware
{

    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private BeanFactory beanFactory;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public SpringHandlerResolver()
    {
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public void setBeanFactory(BeanFactory pBeanFactory) throws BeansException
    {
        beanFactory = pBeanFactory;
    }

    public QEventHandler resolve(String pName) throws QEventManagerException
    {
        try
        {
            return (QEventHandler) beanFactory.getBean(pName);
        }
        catch (NoSuchBeanDefinitionException e)
        {
            return null;    //  bean not found
        }
        catch (BeansException e)
        {
            throw new QEventManagerException(e);
        }
    }
    ///////////////////////////////////////
    ////    INNER CLASSES
}
