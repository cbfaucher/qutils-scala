/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing.spring;

import org.springframework.beans.factory.FactoryBean;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class MnemonicFactoryBean implements FactoryBean
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private String character;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public MnemonicFactoryBean()
    {
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public Object getObject() throws Exception
    {
        final Field field = KeyEvent.class.getField("VK_" + character);
        return field.getInt(null);
    }

    public Class getObjectType()
    {
        return int.class;
    }

    public boolean isSingleton()
    {
        return false;
    }

    public String getCharacter()
    {
        return character;
    }

    public void setCharacter(String pCharacter)
    {
        character = pCharacter;
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
