package com.quartz.qutilities.commands.commonscli;

import org.apache.commons.cli.Parser;
import org.apache.commons.cli.GnuParser;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
final public class ParserFactory
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	static public Parser create()
	{
        return new GnuParser();
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
