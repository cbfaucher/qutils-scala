/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.util.spring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;

import java.net.URL;
import java.io.IOException;

import com.quartz.qutilities.util.QProperties;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class QPropertiesFactoryBean implements FactoryBean
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private URL propertiesUrl = null;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public QPropertiesFactoryBean()
    {
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public void setResource(Resource pResource) throws IOException
    {
        propertiesUrl = pResource.getURL();
    }

    public Object getObject() throws Exception
    {
        return QProperties.load(this.propertiesUrl);
    }

    public Class getObjectType()
    {
        return QProperties.class;
    }

    public boolean isSingleton()
    {
        return false;  
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
