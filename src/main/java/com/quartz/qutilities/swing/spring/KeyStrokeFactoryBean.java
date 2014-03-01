/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing.spring;

import org.springframework.beans.factory.FactoryBean;

import javax.swing.*;
import java.awt.event.InputEvent;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class KeyStrokeFactoryBean implements FactoryBean
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private String keyStroke = null;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public KeyStrokeFactoryBean()
    {
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public Object getObject() throws Exception
    {
        return KeyStroke.getKeyStroke(keyStroke);
    }

    public Class getObjectType()
    {
        return KeyStroke.class;
    }

    public boolean isSingleton()
    {
        return false;
    }

    public String getKeyStroke()
    {
        return keyStroke;
    }

    public void setKeyStroke(String pKeyStroke)
    {
        keyStroke = pKeyStroke;
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
    public String toString()
    {
        return "KeyStrokeFactoryBean{" +
                "keyChain='" + keyStroke + "'" +
                "}";
    }
}
