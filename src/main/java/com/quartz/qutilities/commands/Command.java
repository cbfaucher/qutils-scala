package com.quartz.qutilities.commands;

import java.io.IOException;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public interface Command
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    INSTANCE METHODS

	String getCommandName();
	void execute(CommandManager pCommandManager, String[] pArguments) throws InvalidCommandArgumentException, CommandExecutionException, IOException;
	void printHelp(CommandManager pCommandManager) throws IOException;

	///////////////////////////////////////
	////    INNER CLASSES
}
