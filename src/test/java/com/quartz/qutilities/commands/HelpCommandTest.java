package com.quartz.qutilities.commands;

import com.quartz.qutilities.unittests.QTestCase;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;

/**
 * Unit Test cases for {@link HelpCommand}
 *
 * @author lmcchbf
 * @since 16-Sep-2004
 */
public class HelpCommandTest extends QTestCase
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS
	
	static public final junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(HelpCommandTest.class);
	}	

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	private StringWriter    out             = null;
	private StringWriter    err             = null;
	private StringReader    in              = null;
	private CommandManager  commandManager  = null;

	private HelpCommand     helpCommand     = null;

	///////////////////////////////////////
	////    CONSTRUCTORS

	public HelpCommandTest()
	{
		super();
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	@Before
    public void setUp() throws Exception
	{
		out = new StringWriter();
		err = new StringWriter();
		in  = new StringReader("");

		commandManager = new DefaultCommandManager(out, err, in) {};

		this.helpCommand = new HelpCommand();
	}

    @Test
    public void test_printsHelpOfAllCommands() throws Exception
	{
		final MockCommand cmd1 = new MockCommand("cmd1", "the-description-for-cmd1");
		final MockCommand cmd2 = new MockCommand("cmd2", "the-description-for-cmd2");
		final MockCommand cmd3 = new MockCommand("cmd3", "the-description-for-cmd3");

		this.commandManager.addCommand(cmd1);
		this.commandManager.addCommand(cmd2);
		this.commandManager.addCommand(cmd3);

		cmd1.printHelp_counter.setExpected(1);
		cmd2.printHelp_counter.setExpected(1);
		cmd3.printHelp_counter.setExpected(1);

        this.helpCommand.execute(this.commandManager, new String[0]);


	}

	///////////////////////////////////////
	////    INNER CLASSES
}
