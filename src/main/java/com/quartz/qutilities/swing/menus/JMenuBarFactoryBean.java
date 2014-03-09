package com.quartz.qutilities.swing.menus;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.FactoryBean;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * A Spring {@link FactoryBean} for {@link XmlMenuBar} 
 *
 * @author Christian
 * @since Quartz...
 */
public class JMenuBarFactoryBean implements FactoryBean
{
    private Resource menuResource;
    private ActionListener defaultActionListener;
    private boolean validate;

    public JMenuBarFactoryBean()
    {
    }

    public void setMenuResource(Resource pMenuResource)
    {
        menuResource = pMenuResource;
    }

    public void setDefaultActionListener(ActionListener pDefaultActionListener)
    {
        defaultActionListener = pDefaultActionListener;
    }

    public void setValidate(boolean pValidate)
    {
        validate = pValidate;
    }

    public Object getObject() throws Exception
    {
        return XmlMenuBar.load(menuResource.getURL(), defaultActionListener, validate);
    }

    public Class getObjectType()
    {
        return JMenuBar.class;
    }

    public boolean isSingleton()
    {
        return false;
    }
}
