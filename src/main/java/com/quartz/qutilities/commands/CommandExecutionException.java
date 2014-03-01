package com.quartz.qutilities.commands;

/**
 * If commaand execution fails.
 *
 * @author Christian
 * @since Quartz...
 */
public class CommandExecutionException extends CommandException
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	///////////////////////////////////////
	////    CONSTRUCTORS

	public CommandExecutionException(String message)
	{
		super(message);
	}

	public CommandExecutionException(Throwable cause)
	{
		super(cause);
	}

	public CommandExecutionException(String message, Throwable cause)
	{
		super(message, cause);
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	///////////////////////////////////////
	////    INNER CLASSES
}
