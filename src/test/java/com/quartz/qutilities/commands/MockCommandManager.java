package com.quartz.qutilities.commands;

import com.mockobjects.MockObject;

import java.io.Writer;
import java.io.Reader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class MockCommandManager extends MockObject implements CommandManager
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	///////////////////////////////////////
	////    CONSTRUCTORS

	public MockCommandManager()
	{
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	public void execute(String pCommand, String[] pArgs)
	        throws UnknownCommandException, InvalidCommandArgumentException, CommandExecutionException, CommandException
	{
		fail("No call expected");
	}

	public void addCommand(Command pCommand) throws DuplicateCommandException
	{
		fail("No call expected");
	}

	public Command getCommand(String pCommand)
	{
		fail("No call expected");
		return null;
	}

	public String[] getCommandNames()
	{
		fail("No call expected");
		return new String[0];
	}


	/**
	 * Return value for {@link #getOut()}.   Defaults to {@link System#out}
	 */
	public Writer getOut_returnValue = new OutputStreamWriter(System.out);

	public Writer getOut()
	{
		return getOut_returnValue;
	}


	/**
	 * Return value for {@link #getOut()}.  Defaults to {@link System#err}
	 */
	public Writer getErr_returnValue = new OutputStreamWriter(System.err);

	public Writer getErr()
	{
		return getErr_returnValue;
	}


	/**
	 * Return value for {@link #getIn()}
	 */
	public Reader getIn_returnValue = new InputStreamReader(System.in);

	public Reader getIn()
	{
		return getIn_returnValue;
	}

	///////////////////////////////////////
	////    INNER CLASSES
}
