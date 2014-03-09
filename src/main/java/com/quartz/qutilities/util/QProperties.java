package com.quartz.qutilities.util;

import java.util.Properties;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;

/**
 * An helper class to load {@link Properties} more easily
 *
 * @author Christian
 * @since Quartz...
 */
public class QProperties extends Properties
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	static public QProperties load(String pFilename) throws QPropertiesException
	{
    	return load(new File(pFilename));
	}

	static public QProperties load(File pFile) throws QPropertiesException
	{
		try
		{
			return load(pFile.toURL());
		}
		catch (MalformedURLException e)
		{
			throw new QPropertiesException(e);
		}
	}

	static public QProperties load(URL pURL) throws QPropertiesException
	{
		InputStream is = null;
		try
		{
			is = pURL.openStream();
			return loadFromStream(is);
		}
		catch (java.io.IOException e)
		{
			throw new QPropertiesException("Could not find Properties file: " + pURL.toExternalForm(), e);
		}
		finally
		{
			if (is != null)
			{
				try
				{
					is.close();
				}
				catch (IOException e)
				{
					System.err.println("WARNING:  Could not close stream for URL: " + pURL.toExternalForm());
				}
			}
		}
	}

	static public QProperties loadFromStream(InputStream pInputStream) throws QPropertiesException
	{
		try
		{
			final QProperties properties = new QProperties();
			properties.load(pInputStream);
			return properties;
		}
		catch (IOException e)
		{
			throw new QPropertiesException(e);
		}
	}

    public int getPropertyAsInt(String pPropName, int pDefaultValue)
    {
        return Integer.parseInt(getProperty(pPropName, "" + pDefaultValue));
    }
}
