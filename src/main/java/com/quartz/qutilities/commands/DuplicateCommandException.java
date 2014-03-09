package com.quartz.qutilities.commands;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class DuplicateCommandException extends CommandException
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	final private Command duplicateCommand;

	///////////////////////////////////////
	////    CONSTRUCTORS

	public DuplicateCommandException(Command pDuplicateCommand)
	{
		super("Duplicate command: " + pDuplicateCommand.getCommandName());

		this.duplicateCommand = pDuplicateCommand;
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	public Command getDuplicateCommand()
	{
		return duplicateCommand;
	}

	///////////////////////////////////////
	////    INNER CLASSES
}
