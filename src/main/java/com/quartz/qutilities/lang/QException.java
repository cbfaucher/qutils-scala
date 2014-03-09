package com.quartz.qutilities.lang;

/**
 * Basic exception for Quartz stuff
 *
 * @see QRuntimeException
 *
 * @author Christian
 * @since Quartz...
 */
public class QException extends Exception
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	///////////////////////////////////////
	////    CONSTRUCTORS

	public QException()
	{
	}

	public QException(String message)
	{
		super(message);
	}

	public QException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public QException(Throwable cause)
	{
		super(cause);
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	///////////////////////////////////////
	////    INNER CLASSES
}
