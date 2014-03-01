/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing.spring;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.FactoryBean;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class PropertyReferenceFactoryBean implements FactoryBean
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private Object bean;
    private String property;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public PropertyReferenceFactoryBean()
    {
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public void setBean(Object pBean)
    {
        bean = pBean;
    }

    public void setProperty(String pProperty)
    {
        property = pProperty;
    }

    public Object getObject() throws Exception
    {
        final Object value = PropertyUtils.getNestedProperty(bean, property);
        return value;
    }

    public Class getObjectType()
    {
        return Object.class;
    }

    public boolean isSingleton()
    {
        return false;
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
