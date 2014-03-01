package com.quartz.qutilities.commands;

import com.quartz.qutilities.lang.QException;

/**
 * Basic command exception
 *
 * @author Christian
 * @since Quartz...
 */
public class CommandException extends QException
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	///////////////////////////////////////
	////    CONSTRUCTORS

	public CommandException(String message)
	{
		super(message);
	}

	public CommandException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public CommandException(Throwable cause)
	{
		super(cause);
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	///////////////////////////////////////
	////    INNER CLASSES
}
