package com.quartz.qutilities.lang;

/**
 * Basic runtime exception for Quartz stuff...
 *
 * @see QException
 *
 * @author Christian
 * @since Quartz...
 */
public class QRuntimeException extends RuntimeException
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	///////////////////////////////////////
	////    CONSTRUCTORS

	public QRuntimeException()
	{
	}

	public QRuntimeException(String message)
	{
		super(message);
	}

	public QRuntimeException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public QRuntimeException(Throwable cause)
	{
		super(cause);
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	///////////////////////////////////////
	////    INNER CLASSES
}
