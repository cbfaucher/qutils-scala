package com.quartz.qutilities.commands;

/**
 * Exception thrown when a command is not found.
 *
 * @author Christian
 * @since Quartz...
 */
public class UnknownCommandException extends CommandException
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	final private String commandName;

	///////////////////////////////////////
	////    CONSTRUCTORS

	public UnknownCommandException(String pCommandName)
	{
		super("Unknown command: " + pCommandName);
		commandName = pCommandName;
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	public String getCommandName()
	{
		return commandName;
	}

	///////////////////////////////////////
	////    INNER CLASSES
}
