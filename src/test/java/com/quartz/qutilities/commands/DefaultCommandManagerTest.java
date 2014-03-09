package com.quartz.qutilities.commands;

import com.quartz.qutilities.unittests.QTestCase;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;

/**
 * Unit Test cases for {@link DefaultCommandManager}
 *
 * @author lmcchbf
 * @since 15-Sep-2004
 */
public class DefaultCommandManagerTest extends QTestCase
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS
	
	static public final Test suite()
	{
		return new JUnit4TestAdapter(DefaultCommandManagerTest.class);
	}	

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	private CommandManager commandManager = null;

	///////////////////////////////////////
	////    CONSTRUCTORS

	public DefaultCommandManagerTest()
	{
		super();
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

    @Before
    public void setUp() throws Exception
	{
		this.commandManager = new DefaultCommandManager() {};
	}

    @org.junit.Test
    public void test_addGetCommand() throws Exception
	{
		final MockCommand command = new MockCommand("doodah");

		Assert.assertEquals(null, this.commandManager.getCommand(command.getCommandName()));

		//  add it now
		this.commandManager.addCommand(command);
		Assert.assertEquals(command, this.commandManager.getCommand(command.getCommandName()));

		//  add it twice
		try
		{
			this.commandManager.addCommand(new MockCommand("doodah"));
			fail("Exception expected.");
		}
		catch (DuplicateCommandException e)
		{
			//  ok
		}

		command.verify();
	}

    @org.junit.Test
    public void test_execute_success() throws Exception
    {
	    final MockCommand command = new MockCommand("doodah");
	    this.commandManager.addCommand(command);

	    final String[] expectedArgs = {"aaa", "1234", "3.1416"};

	    command.execute_counter.setExpected(1);
	    command.execute_arguments.setExpected(expectedArgs);

	    this.commandManager.execute("doodah", expectedArgs);

	    command.verify();
    }

    @org.junit.Test
    public void test_execute_cmdNotFound() throws Exception
	{
		final String[] expectedArgs = {"aaa", "1234", "3.1416"};

		try
		{
			this.commandManager.execute("doodah", expectedArgs);
			fail("Exception expected.");
		}
		catch (UnknownCommandException e)
		{
			//  ok
		}
	}

	///////////////////////////////////////
	////    INNER CLASSES
}
