package com.quartz.qutilities.commands;

import java.io.Reader;
import java.io.Writer;

/**
 * A generic command manager.
 *
 * @author Christian
 * @since Quartz...
 */
public interface CommandManager
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    INSTANCE METHODS

	void execute(String pCommand, String[] pArgs)
	        throws UnknownCommandException, InvalidCommandArgumentException, CommandExecutionException, CommandException;

	void addCommand(Command pCommand) throws DuplicateCommandException;

	Command getCommand(String pCommand);

	String[] getCommandNames();

	Writer getOut();
	Writer getErr();
	Reader getIn();

	///////////////////////////////////////
	////    INNER CLASSES
}
