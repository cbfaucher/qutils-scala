package com.quartz.qutilities.util;

import com.quartz.qutilities.lang.QRuntimeException;

/**
 * If loading of {@link QProperties} failed for some reason (see root cause)
 *
 * @author Christian
 * @since Quartz...
 */
public class QPropertiesException extends QRuntimeException
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	///////////////////////////////////////
	////    CONSTRUCTORS

	public QPropertiesException(String message)
	{
		super(message);
	}

	public QPropertiesException(Throwable cause)
	{
		super(cause);
	}

	public QPropertiesException(String message, Throwable cause)
	{
		super(message, cause);
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	///////////////////////////////////////
	////    INNER CLASSES
}
