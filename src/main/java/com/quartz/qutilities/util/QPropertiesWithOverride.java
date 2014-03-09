package com.quartz.qutilities.util;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class QPropertiesWithOverride extends AbstractProperties
{
    final private QProperties       properties;
    final private QUserProperties   userProperties;

    public QPropertiesWithOverride(QProperties pProperties, QUserProperties pUserProperties)
    {
        properties = pProperties;
        userProperties = pUserProperties;
    }

    public QPropertiesWithOverride(QProperties pDefaultProperties, String pApplicationName)
    {
        this(pDefaultProperties, new QUserProperties(pApplicationName, true));
    }

    public String getProperty(String key)
    {
        final String value = userProperties.getProperty(key, null);

        if (value != null)
        {
            return value;
        }

        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue)
    {
        final String value = userProperties.getProperty(key, null);

        if (value != null)
        {
            return value;
        }
        
        return properties.getProperty(key, defaultValue);
    }

    public synchronized Object setProperty(String key, String value)
    {
        final String old = getProperty(key);
        userProperties.setProperty(key, value);
        return old;
    }
}
