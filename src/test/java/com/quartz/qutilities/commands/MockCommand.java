package com.quartz.qutilities.commands;

import com.mockobjects.MockObject;
import com.quartz.qutilities.unittests.QExpectationCounter;
import com.quartz.qutilities.unittests.QExpectationValue;

import java.io.IOException;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class MockCommand extends MockObject implements Command
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	public String commandName = null;
	public String description = null;

	///////////////////////////////////////
	////    CONSTRUCTORS

	public MockCommand()
	{
	}

	public MockCommand(String pCommandName)
	{
		commandName = pCommandName;
	}

	public MockCommand(String pCommandName, String pDescription)
	{
		commandName = pCommandName;
		description = pDescription;
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	/**
	 * NOTE: No check can be done on this method.
	 *
	 * @return command name
	 */
	public String getCommandName()
	{
		return this.commandName;
	}


	/**
	 * Counter for {@link #execute(CommandManager, String[])}
	 */
	final public QExpectationCounter execute_counter = new QExpectationCounter("execute(CommandManager, String[])");

	/**
	 * Expectation value for {@link #execute(CommandManager, String[])}'s arguments
	 */
	public QExpectationValue execute_arguments = new QExpectationValue("execute-expected-arguments");

	public void execute(CommandManager pCommandManager, String[] pArguments) throws InvalidCommandArgumentException, CommandExecutionException
	{
        this.execute_counter.inc();

		this.execute_arguments.setActual(pArguments);
	}


	/**
	 * Counter for {@link #printHelp(CommandManager)}
	 */
	final public QExpectationCounter printHelp_counter = new QExpectationCounter("printHelp(CommandManager)");

	public void printHelp(CommandManager pCommandManager) throws IOException
	{
		printHelp_counter.inc();
	}

	///////////////////////////////////////
	////    INNER CLASSES
}
