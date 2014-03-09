package com.quartz.qutilities.util.spring;

import org.springframework.beans.factory.FactoryBean;
import com.quartz.qutilities.util.IProperties;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class GetPropertyFactoryBean implements FactoryBean
{
    private IProperties properties;
    private String defaultValue = null;
    private String propertyName;

    public GetPropertyFactoryBean()
    {
    }

    public void setProperties(IProperties pProperties)
    {
        properties = pProperties;
    }

    public void setDefaultValue(String pDefaultValue)
    {
        defaultValue = pDefaultValue;
    }

    public void setPropertyName(String pPropertyName)
    {
        propertyName = pPropertyName;
    }

    public Object getObject() throws Exception
    {
        return properties.getProperty(propertyName, defaultValue);
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
