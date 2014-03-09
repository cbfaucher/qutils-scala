package com.quartz.qutilities.lang.spring;

import org.springframework.beans.factory.FactoryBean;

/**
 * A {@link org.springframework.beans.factory.FactoryBean} that does <tt>Class.forName(className)</tt>
 *
 * @author Christian
 * @since Quartz...
 */
public class ClassForNameFactoryBean implements FactoryBean
{
    private String className;

    public ClassForNameFactoryBean(String pClassName)
    {
        className = pClassName;
    }

    public ClassForNameFactoryBean()
    {
    }

    public void setClassName(String pClassName)
    {
        className = pClassName;
    }

    public Class getObjectType()
    {
        return Class.class;
    }

    public boolean isSingleton()
    {
        return true;
    }

    public Object getObject() throws Exception
    {
        return Class.forName(className);
    }
}
