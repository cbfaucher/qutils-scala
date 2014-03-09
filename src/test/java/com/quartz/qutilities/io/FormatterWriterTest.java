/*
 * Copyright (c) 2005 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.io;

import com.quartz.qutilities.unittests.QTestCase;
import junit.framework.TestSuite;
import junit.framework.JUnit4TestAdapter;
import org.junit.Assert;

import java.io.StringWriter;

/**
 * Unit Test cases for {@link FormatterWriter}
 *
 * @author lmcchbf
 * @since 2-Jan-2005
 */
public class FormatterWriterTest extends QTestCase
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS
	
    static public final junit.framework.Test suite()
    {
        return new JUnit4TestAdapter(FormatterWriterTest.class);
    }	

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public FormatterWriterTest()
    {
        super();
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    @org.junit.Test
    public void test_indentAndUnindent() throws Exception
    {
        final StringWriter buffer = new StringWriter();
    	final FormatterWriter formatter = new FormatterWriter(buffer, true, "+++");

        final String expected = "The quick\n+++brown fox\n++++++jumps\n+++\n+++over the lazy\n+++dog.\n\n";

        formatter.write("The quick\n");
        formatter.indent();
        formatter.write("brown ");
        formatter.indent();
        formatter.write("fox\n");
        formatter.write("jumps\n");
        formatter.unindent();
        formatter.write("\n");        
        formatter.write("over the lazy\ndog.");
        formatter.unindent();
        formatter.write("\n\n");

        formatter.flush();

        try
        {
            Assert.assertEquals(expected, buffer.toString());
        }
        finally
        {
            formatter.close();
        }
    }

    @org.junit.Test
    public void test_write_indentAtStart() throws Exception
    {
        final StringWriter buffer = new StringWriter();
    	final FormatterWriter formatter = new FormatterWriter(buffer, true, "\t");

        formatter.indent();
        formatter.indent();

        final String expected = "\t\tHello,\n\t\tWorld!";

        formatter.write("Hello,\nWorld!");

        Assert.assertEquals(expected, buffer.toString());
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
