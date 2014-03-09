/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing.menus.digester;

import org.apache.commons.digester.ObjectCreationFactory;
import org.apache.commons.digester.Digester;
import org.xml.sax.Attributes;
import com.quartz.qutilities.swing.menus.XmlMenu;
import com.quartz.qutilities.swing.menus.MenuFactoryBean;
import com.quartz.qutilities.lang.ClassUtilities;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class XmlMenuFactory implements ObjectCreationFactory
{

    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private Digester digester;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public XmlMenuFactory()
    {
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public Object createObject(Attributes attributes) throws Exception
    {
        final String menuClassName = attributes.getValue("class-name");
        if (menuClassName == null) return new XmlMenu();

        final Object o = ClassUtilities.create(menuClassName);

        if (o instanceof MenuFactoryBean)
        {
            return ((MenuFactoryBean) o).createMenu();
        }
        else if (o instanceof XmlMenu)
        {
            return (XmlMenu) o;
        }
        else
        {
            throw new XmlMenuException("Class " + menuClassName + " is not an XmlMenu or a MenuFactoryBean");
        }
    }

    public Digester getDigester()
    {
        return digester;
    }

    public void setDigester(Digester pDigester)
    {
        digester = pDigester;
    }
    ///////////////////////////////////////
    ////    INNER CLASSES
}
