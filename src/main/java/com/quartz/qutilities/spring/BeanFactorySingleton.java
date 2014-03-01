/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.spring;

import org.springframework.beans.factory.BeanFactory;

/**
 * A singleton keeper 
 *
 * @author Christian
 * @since Quartz...
 */
final public class BeanFactorySingleton
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    static private BeanFactory beanFactory;

    ///////////////////////////////////////
    ////    STATIC METHODS

    public static void setBeanFactory(BeanFactory pBeanFactory)
    {
        beanFactory = pBeanFactory;
    }

    static public <T>T getBean(String pName)
    {
        return (T) beanFactory.getBean(pName);
    }

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    private BeanFactorySingleton()
    {
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS



    ///////////////////////////////////////
    ////    INNER CLASSES
}
