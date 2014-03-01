package com.quartz.qutilities.commands.commonscli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.Parser;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
final public class CommandLineUtilities
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	static public String getOptionValue(CommandLine pCommandLine, Option pOption)
	{
		return pCommandLine.getOptionValue(pOption.getOpt());
	}

	static public String getOptionValuesAsString(CommandLine pCommandLine, Option pOption)
	{
        final String opt = pOption.getOpt();
		final String[] values = pCommandLine.getOptionValues(opt);
		if (values == null || values.length == 0) return null;

		final StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < values.length; i++)
		{
			String value = values[i];
			if (i > 0)
			{
				buffer.append(" ");
			}
			buffer.append(value);
		}
		return buffer.toString();
	}

	public static Integer getOptionValuesAsInteger(CommandLine pCommandLine, Option pOption)
	{
		final String optionValue = pCommandLine.getOptionValue(pOption.getOpt());
		return (optionValue != null ? new Integer(optionValue.trim()) : null);
	}

	public static boolean hasOption(CommandLine pCommandLine, Option pOption)
	{
		return pCommandLine.hasOption(pOption.getOpt());
	}

	static public CommandLine parse(Options pOptions, String[] pCmdArgs, boolean pFailOnNonOption) throws ParseException
	{
        final Parser parser = ParserFactory.create();

		return  parser.parse(pOptions, pCmdArgs, !pFailOnNonOption);
	}

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	///////////////////////////////////////
	////    CONSTRUCTORS

	///////////////////////////////////////
	////    INSTANCE METHODS

	///////////////////////////////////////
	////    INNER CLASSES
}
