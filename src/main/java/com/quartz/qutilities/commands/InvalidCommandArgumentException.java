package com.quartz.qutilities.commands;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class InvalidCommandArgumentException extends CommandException
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	final private String[] faultyArguments;

	///////////////////////////////////////
	////    CONSTRUCTORS

	public InvalidCommandArgumentException(String message, String[] pArgs)
	{
		super(message);
		faultyArguments = pArgs;
	}

	public InvalidCommandArgumentException(Throwable cause, String[] pFaultyArguments)
	{
		super(cause);
		faultyArguments = pFaultyArguments;
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	public String[] getFaultyArguments()
	{
		return faultyArguments;
	}

	///////////////////////////////////////
	////    INNER CLASSES
}
