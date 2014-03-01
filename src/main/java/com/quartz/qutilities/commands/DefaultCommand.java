package com.quartz.qutilities.commands;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.Writer;

/**
 * An helper implementation for {@link Command}
 *
 * @author Christian
 * @since Quartz...
 */
public abstract class DefaultCommand implements Command
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	public static final int COMMAND_NAME_PADDING = 25;

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	final protected String commandName;
	final protected String commandDescription;

	///////////////////////////////////////
	////    CONSTRUCTORS

	protected DefaultCommand(String pCommandName)
	{
		this(pCommandName, "");
	}

	public DefaultCommand(String pCommandName, String pDescription)
	{
		commandName = pCommandName;
		commandDescription = pDescription;
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	public String getCommandName()
	{
		return this.commandName;
	}

	/**
	 * This implementation prints the command name, followed by the description.
	 * @param pCommandManager Owning {@link CommandManager}
	 * @throws IOException If failure writing.
	 */
	public void printHelp(CommandManager pCommandManager) throws IOException
	{
		final Writer out = pCommandManager.getOut();

		out.write(StringUtils.rightPad(commandName, COMMAND_NAME_PADDING));
		out.write(this.commandDescription);
		out.write("\n");
	}

	///////////////////////////////////////
	////    INNER CLASSES
}
