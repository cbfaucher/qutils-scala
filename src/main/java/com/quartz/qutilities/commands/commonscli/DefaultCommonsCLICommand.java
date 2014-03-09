package com.quartz.qutilities.commands.commonscli;

import com.quartz.qutilities.commands.DefaultCommand;
import com.quartz.qutilities.commands.CommandManager;
import com.quartz.qutilities.commands.InvalidCommandArgumentException;
import com.quartz.qutilities.commands.CommandExecutionException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.Writer;
import java.io.PrintWriter;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public abstract class DefaultCommonsCLICommand extends DefaultCommand
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	public static final int HELP_LINE_WIDTH = 200;
	public static final int TAB_TO_OPTION_PADDING = 5;
	public static final int OPTION_TO_DESCRIPTION_PADDING = 5;

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	private Options options;
	private boolean failIfUnknownArguments = true;

	///////////////////////////////////////
	////    CONSTRUCTORS

	public DefaultCommonsCLICommand(String pCommandName, String pDescription)
	{
		this(pCommandName, pDescription, true);
	}

	/**
	 * Constructor.
	 *
	 * @param pCommandName Name of the command
	 * @param pCommandDescription Description of this command
	 * @param pFailIfUnknownArguments If true, parsing will fail if unused tokens
	 */
	protected DefaultCommonsCLICommand(String pCommandName, String pCommandDescription, boolean pFailIfUnknownArguments)
	{
		super(pCommandName, pCommandDescription);

		this.failIfUnknownArguments = pFailIfUnknownArguments;

		this.options = buildOptions();
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	final public Options getOptions()
	{
		return options;
	}

	final protected void setOptions(Options pOptions)
	{
		this.options = pOptions;
	}

	final public void execute(CommandManager pCommandManager, String[] pArguments) throws InvalidCommandArgumentException, CommandExecutionException, IOException
	{
		try
		{
			//  parse
			final CommandLine commandLine = CommandLineUtilities.parse(this.options, pArguments, true);

			if (failIfUnknownArguments == true)
			{
				if (commandLine.getArgList().size() > 0)
				{
					throw new InvalidCommandArgumentException("Unknown/Unsupported argument(s)", commandLine.getArgs());
				}
			}

			//  forward to 2nd execute(...) method
			this.execute(pCommandManager, commandLine);
		}
		catch (ParseException e)
		{
			this.printHelp(pCommandManager);
			throw new InvalidCommandArgumentException(e, pArguments);
		}
	}

	final public void printHelp(CommandManager pCommandManager) throws IOException
	{
        final Writer out = pCommandManager.getOut();

        final HelpFormatter helpFormatter = new HelpFormatter();
		helpFormatter.defaultSyntaxPrefix = "";
		final PrintWriter printWriter = new PrintWriter(out, true);
		helpFormatter.printHelp(printWriter,
		                        HELP_LINE_WIDTH,
		                        StringUtils.rightPad(this.commandName, COMMAND_NAME_PADDING) + this.commandDescription,
		                        "",
		                        this.options,
		                        TAB_TO_OPTION_PADDING,
		                        OPTION_TO_DESCRIPTION_PADDING,
		                        "",
		                        false);
		helpFormatter.printUsage(printWriter, HELP_LINE_WIDTH,
		                         StringUtils.leftPad("", TAB_TO_OPTION_PADDING) + "Usage: " + this.commandName, this.options);
	}

	/**
	 * Sub-classes can choose to override this method to build the {@link #options}, or to set it at a later
	 * time using {@link #setOptions(Options)}.
	 * <p>
	 * One way or the other, the options must be set prior to the first call to {@link #execute(com.quartz.qutilities.commands.CommandManager, java.lang.String[])}
	 *
	 * @return This implementation returns null.
	 */
	protected Options buildOptions()
	{
		return null;
	}

	abstract public void execute(CommandManager pCommandManager, CommandLine pCommandLine) throws InvalidCommandArgumentException, CommandExecutionException, IOException;

	///////////////////////////////////////
	////    INNER CLASSES
}
