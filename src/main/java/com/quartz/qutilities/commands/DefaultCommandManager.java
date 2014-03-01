package com.quartz.qutilities.commands;

import java.util.Map;
import java.util.HashMap;
import java.io.Writer;
import java.io.Reader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public abstract class DefaultCommandManager implements CommandManager
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	final private Map commands = new HashMap();

	final private Writer out;
	final private Writer err;
	final private Reader in;

	///////////////////////////////////////
	////    CONSTRUCTORS

	public DefaultCommandManager() throws IllegalArgumentException
	{
     	this(   new OutputStreamWriter(System.out),
	            new OutputStreamWriter(System.err),
	            new InputStreamReader(System.in));
	}

	public DefaultCommandManager(Writer pOut, Writer pErr, Reader pIn)
	{
		out = pOut;
		err = pErr;
		in = pIn;
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	public synchronized void addCommand(Command pCommand) throws DuplicateCommandException
	{
		final String commandName = pCommand.getCommandName();

		if (commands.containsKey(commandName))
        {
	        throw new DuplicateCommandException(pCommand);
        }

        this.commands.put(commandName, pCommand);
	}

    public void setCommands(Command ... pCommands) throws DuplicateCommandException
    {
        commands.clear();

        for (Command c : pCommands) addCommand(c);
    }

    public void execute(String pCommand, String[] pArgs)
	        throws UnknownCommandException, InvalidCommandArgumentException, CommandExecutionException, CommandException
	{
    	final Command command = getCommand(pCommand);
		if (command == null)
		{
			throw new UnknownCommandException(pCommand);
		}

		try
		{
			command.execute(this, pArgs);
		}
		catch (IOException e)
		{
			throw new CommandException(e);
		}
	}

	synchronized public Command getCommand(String pCommand)
	{
		return (Command) this.commands.get(pCommand);
	}

	synchronized public String[] getCommandNames()
	{
		return (String[]) this.commands.keySet().toArray(new String[0]);
	}

	public Writer getOut()
	{
		return this.out;
	}

	public Writer getErr()
	{
		return this.err;
	}

	public Reader getIn()
	{
		return this.in;
	}

	///////////////////////////////////////
	////    INNER CLASSES
}
