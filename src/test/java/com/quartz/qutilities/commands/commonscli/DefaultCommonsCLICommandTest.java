package com.quartz.qutilities.commands.commonscli;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.JUnit4TestAdapter;
import com.quartz.qutilities.unittests.QTestCase;
import com.quartz.qutilities.commands.CommandManager;
import com.quartz.qutilities.commands.InvalidCommandArgumentException;
import com.quartz.qutilities.commands.CommandExecutionException;
import com.quartz.qutilities.commands.MockCommandManager;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import java.io.IOException;

/**
 * Unit Test cases for {@link DefaultCommonsCLICommand}
 *
 * @author lmcchbf
 * @since 16-Sep-2004
 */
public class DefaultCommonsCLICommandTest extends QTestCase
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS
	
	static public final Test suite()
	{
		return new JUnit4TestAdapter(DefaultCommonsCLICommandTest.class);
	}	

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	private MockCommandManager mockCommandManager = null;
    private MyCommand command = null;

	///////////////////////////////////////
	////    CONSTRUCTORS

	public DefaultCommonsCLICommandTest()
	{
		super();
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

    @Before
    public void setUp() throws Exception
	{
		this.mockCommandManager = new MockCommandManager();

		//  this.command created in test.
	}

    @org.junit.Test
    public void test_execute_mandatoryOptionsAreThere() throws Exception
	{
		final Options theOptions = new Options();
		final Option o1 = OptionBuilder.hasArgs(0).isRequired().create("o1");
		final Option o2 = OptionBuilder.hasArgs(2).isRequired().create("o2");
		theOptions.addOption(o1);
		theOptions.addOption(o2);

     	this.command = new MyCommand("cmd", this.getName(), theOptions);

		this.command.execute(this.mockCommandManager, new String[] {"-o1", "-o2", "Christian", "Faucher"});

		Assert.assertEquals(1, this.command.nbCallsToExecute);
		Assert.assertEquals(0, command.lastReceivedCommandLine.getArgList().size());
		Assert.assertEquals("Christian Faucher", CommandLineUtilities.getOptionValuesAsString(command.lastReceivedCommandLine, o2));
	}

    @org.junit.Test
    public void test_execute_missingMandatoryOption() throws Exception
	{
		final Options theOptions = new Options();
		final Option o1 = OptionBuilder.hasArgs(0).isRequired().create("o1");
		final Option o2 = OptionBuilder.hasArgs(2).isRequired().create("o2");
		theOptions.addOption(o1);
		theOptions.addOption(o2);

     	this.command = new MyCommand("cmd", this.getName(), theOptions);

		//  o1 not specified!
		try
		{
			this.command.execute(this.mockCommandManager, new String[] { /*"-o1",*/ "-o2", "Christian", "Faucher"});
			fail("Exception expected.");
		}
		catch (InvalidCommandArgumentException e)
		{
			//  ok
			assertTrue(e.getCause() instanceof ParseException);
		}

		Assert.assertEquals(0, this.command.nbCallsToExecute);
	}

    @org.junit.Test
    public void test_execute_optionalOption_specified() throws Exception
	{
		final Options theOptions = new Options();
		final Option o1 = OptionBuilder.hasArgs(0).isRequired(false).create("o1");  //  now optional
		final Option o2 = OptionBuilder.hasArgs(2).isRequired().create("o2");
		theOptions.addOption(o1);
		theOptions.addOption(o2);

     	this.command = new MyCommand("cmd", this.getName(), theOptions);

		this.command.execute(this.mockCommandManager, new String[] { "-o1", "-o2", "Christian", "Faucher"});

		Assert.assertEquals(1, this.command.nbCallsToExecute);
		Assert.assertEquals(true, this.command.lastReceivedCommandLine.hasOption("o1"));
	}

    @org.junit.Test
    public void test_execute_optionalOption_notSpecified() throws Exception
	{
		final Options theOptions = new Options();
		final Option o1 = OptionBuilder.hasArgs(0).isRequired(false).create("o1");  //  now optional
		final Option o2 = OptionBuilder.hasArgs(2).isRequired().create("o2");
		theOptions.addOption(o1);
		theOptions.addOption(o2);

     	this.command = new MyCommand("cmd", this.getName(), theOptions);

		//  o1 not specified!
		this.command.execute(this.mockCommandManager, new String[] { /*"-o1",*/ "-o2", "Christian", "Faucher"});

		Assert.assertEquals(1, this.command.nbCallsToExecute);
		Assert.assertEquals(false, this.command.lastReceivedCommandLine.hasOption("o1"));
	}

    @org.junit.Test
    public void test_execute_invalidOption() throws Exception
	{
		final Options theOptions = new Options();
		final Option o1 = OptionBuilder.hasArgs(0).isRequired(false).create("o1");  //  now optional
		final Option o2 = OptionBuilder.hasArgs(2).isRequired().create("o2");
		theOptions.addOption(o1);
		theOptions.addOption(o2);

     	this.command = new MyCommand("cmd", this.getName(), theOptions);

		try
		{
			this.command.execute(this.mockCommandManager, new String[] { "-o1", "-o2", "Christian", "Faucher", "-invalidoption"});
			fail("Exception expected.");
		}
		catch (InvalidCommandArgumentException e)
		{
			assertTrue(e.getCause() instanceof ParseException);
		}

		Assert.assertEquals(0, this.command.nbCallsToExecute);
	}

    @org.junit.Test
    public void test_execute_optionsWithExtraArguments_noneRequired() throws Exception
	{
		final Options theOptions = new Options();
		final Option o1 = OptionBuilder.hasArgs(0).isRequired(false).create("o1");  //  now optional
		final Option o2 = OptionBuilder.hasArgs(2).isRequired().create("o2");
		theOptions.addOption(o1);
		theOptions.addOption(o2);

     	this.command = new MyCommand("cmd", this.getName(), theOptions);

		try
		{
			//  -o1 has invalid arguments
			this.command.execute(this.mockCommandManager, new String[] { "-o1", "useless", "argument", "-o2", "Christian", "Faucher"});
			fail("Exception expected.");
		}
		catch (InvalidCommandArgumentException e)
		{
			Assert.assertEquals(new String[] {"useless", "argument"}, e.getFaultyArguments());
		}

		Assert.assertEquals(0, this.command.nbCallsToExecute);
	}

    @org.junit.Test
    public void test_execute_optionsWithExtraArguments_tooMany() throws Exception
	{
		final Options theOptions = new Options();
		final Option o1 = OptionBuilder.hasArgs(0).isRequired(false).create("o1");  //  now optional
		final Option o2 = OptionBuilder.hasArgs(2).isRequired().create("o2");
		theOptions.addOption(o1);
		theOptions.addOption(o2);

     	this.command = new MyCommand("cmd", this.getName(), theOptions);

		try
		{
			//  -o1 has invalid arguments
			this.command.execute(this.mockCommandManager, new String[] { "-o1", "-o2", "Christian", "Faucher", "OneTooMany"});
			fail("Exception expected.");
		}
		catch (InvalidCommandArgumentException e)
		{
			Assert.assertEquals(new String[] {"OneTooMany"}, e.getFaultyArguments());
		}

		Assert.assertEquals(0, this.command.nbCallsToExecute);
	}

    @org.junit.Test
    public void test_parsing_keepsUnusedArgs() throws Exception
	{
		final Options theOptions = new Options();
		final Option o1 = OptionBuilder.hasArgs(0).isRequired(false).create("o1");  //  now optional
		final Option o2 = OptionBuilder.hasArgs(2).isRequired().create("o2");
		theOptions.addOption(o1);
		theOptions.addOption(o2);

     	this.command = new MyCommand("cmd", this.getName(), false, theOptions);

		//  -o1 has invalid arguments
		this.command.execute(this.mockCommandManager, new String[] { "-o1", "useless", "argument", "-o2", "Christian", "Faucher", "OneTooMany"});

        Assert.assertEquals(1, this.command.nbCallsToExecute);
		Assert.assertEquals(new String[] {"useless", "argument", "OneTooMany"}, this.command.lastReceivedCommandLine.getArgs());
	}

	///////////////////////////////////////
	////    INNER CLASSES

	public class MyCommand extends DefaultCommonsCLICommand
	{
		//  those are results from calls to execute(...)
		public int          nbCallsToExecute    = 0;
		public CommandLine  lastReceivedCommandLine = null;

		public MyCommand(String pCommandName, String pCommandDescription, Options pOptions)
		{
			super(pCommandName, pCommandDescription);

			this.setOptions(pOptions);
		}

		public MyCommand(String pCommandName, String pCommandDescription, boolean pFailIfUnknownArguments, Options pOptions)
		{
			super(pCommandName, pCommandDescription, pFailIfUnknownArguments);

			this.setOptions(pOptions);
		}

		public void execute(CommandManager pCommandManager, CommandLine pCommandLine) throws InvalidCommandArgumentException, CommandExecutionException, IOException
		{
			++nbCallsToExecute;
			this.lastReceivedCommandLine = pCommandLine;
		}
	}
}
