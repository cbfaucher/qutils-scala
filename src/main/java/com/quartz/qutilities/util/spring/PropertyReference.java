/*
 * Copyright (c) 2007 Your Corporation. All Rights Reserved.
 */
package com.quartz.qutilities.util.spring;

import org.springframework.beans.factory.FactoryBean;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * In Spring, you cannot (easily) reference a property of another bean for setting
 * a given property.  You can refer to a bean, but not to one of its properties.
 * <p>
 * This class gives access to nested properties of a nested bean.  Property can be nested
 * (e.g. person.phone[0].areaCode for instance).
 *
 * @author fauchc02
 * @since 2007-03-28 07:33:02
 */
public class PropertyReference implements FactoryBean
{
    private Object bean;
    private String propertyName;

    public PropertyReference()
    {

    }

    public void setBean(final Object pBean)
    {
        bean = pBean;
    }

    public void setPropertyName(final String pPropertyName)
    {
        propertyName = pPropertyName;
    }

    public Object getObject() throws Exception
    {
        if (bean == null) throw new IllegalStateException("Bean not set");
        if (propertyName == null) throw new IllegalStateException("Property Name not set");
        return PropertyUtils.getNestedProperty(bean, propertyName);
    }

    public Class getObjectType()
    {
        return Object.class;
    }

    public boolean isSingleton()
    {
        return false;
    }
}
