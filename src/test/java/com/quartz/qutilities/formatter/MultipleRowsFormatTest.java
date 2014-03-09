/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.formatter;

import com.quartz.qutilities.unittests.QTestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.JUnit4TestAdapter;
import org.junit.Assert;

import java.util.List;
import java.util.Vector;

/**
 * Unit Test cases for {@link MultipleRowsFormat}
 *
 * @author lmcchbf
 * @since 27-Jun-2006
 */
public class MultipleRowsFormatTest extends QTestCase
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS
	
    static public final Test suite()
    {
        return new JUnit4TestAdapter(MultipleRowsFormatTest.class);
    }	

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public MultipleRowsFormatTest()
    {
        super();
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    @org.junit.Test
    public void test_format() throws Exception
    {
        final List<Money> v = new Vector<Money>();

        v.add(new Money("US", 1.23f));
        v.add(new Money("CAN", 4.56f));
        v.add(new Money("PESOS", 10000f));

        final MultipleRowsFormat<List<Money>, Money> format = new MultipleRowsFormat<List<Money>, Money>();
        format.add(new LiteralCellFormat("It costs "));
        format.add(new BeanUtilsCellFormat<Money>("currency"));
        format.add(new BeanUtilsCellFormat<Money>("amount"));

        final String expected =
                "It costs US1.23\nIt costs CAN4.56\nIt costs PESOS10000.0";

        Assert.assertEquals(expected, format.format(v));
    }


    ///////////////////////////////////////
    ////    INNER CLASSES
}
