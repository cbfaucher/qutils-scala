package com.quartz.qutilities.commands;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

/**
 * A quick and dirty Help, that prints names of commands.
 *
 * @author Christian
 * @since Quartz...
 */
public class HelpCommand extends DefaultCommand
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	///////////////////////////////////////
	////    CONSTRUCTORS

	public HelpCommand()
	{
		super("help", "Displays this help message");
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	public void execute(CommandManager pCommandManager, String[] pArguments) throws InvalidCommandArgumentException, CommandExecutionException, IOException
	{
        final String[] commandNames = pCommandManager.getCommandNames();
		Arrays.sort(commandNames);  //  order names;

		final Writer out = pCommandManager.getOut();

		//  header
		out.write("Available commands (some may have detailed help):\n\n");

		for (int i = 0; i < commandNames.length; i++)
		{
			final String commandName = commandNames[i];
			final Command command = pCommandManager.getCommand(commandName);
			command.printHelp(pCommandManager);
		}

		out.write("\n");
		out.flush();
    }

	///////////////////////////////////////
	////    INNER CLASSES
}
