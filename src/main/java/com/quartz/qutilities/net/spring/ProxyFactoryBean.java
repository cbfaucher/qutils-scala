/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.net.spring;

import org.springframework.beans.factory.FactoryBean;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class ProxyFactoryBean implements FactoryBean
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private boolean proxyEnabled = false;
    private String proxyType;
    private String proxyHost;
    private int proxyPort;

    private Proxy proxy = null;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public ProxyFactoryBean()
    {
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public void setProxyEnabled(boolean pProxyEnabled)
    {
        proxyEnabled = pProxyEnabled;
    }

    public void setProxyType(String pProxyType)
    {
        proxyType = pProxyType;
    }

    public void setProxyHost(String pProxyHost)
    {
        proxyHost = pProxyHost;
    }

    public void setProxyPort(int pProxyPort)
    {
        proxyPort = pProxyPort;
    }

    public Object getObject() throws Exception
    {
        if (proxy == null)
        {
            createProxyInstance();
        }

        return proxy;
    }

    public Class getObjectType()
    {
        return Proxy.class;
    }

    public boolean isSingleton()
    {
        return true;
    }

    private void createProxyInstance()
    {
        if (proxyEnabled == false)
        {
            proxy = Proxy.NO_PROXY;
            return;
        }

        if (proxyType == null) throw new IllegalArgumentException("Proxy Type is not specified.   Must be HTTP, SOCKS or DIRECT");
        if (proxyHost == null) throw new IllegalArgumentException("Proxy Host is not specified.");

        final Proxy.Type type = guessProxyType();
        proxy = new Proxy(type,
                          (type == Proxy.Type.DIRECT
                            ? null
                            : new InetSocketAddress(proxyHost, proxyPort)));
    }

    private Proxy.Type guessProxyType()
    {
        if ("DIRECT".equalsIgnoreCase(proxyType)) return Proxy.Type.DIRECT;
        if ("HTTP".equalsIgnoreCase(proxyType)) return Proxy.Type.HTTP;
        if ("SOCKS".equalsIgnoreCase(proxyType)) return Proxy.Type.SOCKS;

        throw new IllegalArgumentException("Invalid Proxy Type: " + proxyType);
    }


    ///////////////////////////////////////
    ////    INNER CLASSES
}
