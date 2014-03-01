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

/**
 * Unit Test cases for {@link RowFormat}
 *
 * @author lmcchbf
 * @since 22-Jun-2006
 */
public class SingleRowFormatTest extends QTestCase
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS
	
    static public final Test suite()
    {
        return new JUnit4TestAdapter(SingleRowFormatTest.class);
    }	

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public SingleRowFormatTest()
    {
        super();
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    @org.junit.Test
    public void test_format_noPresentation() throws Exception
    {
    	final SingleRowFormat rowFormat = new SingleRowFormat<Money>(new CellFormat[] {
            new LiteralCellFormat("It costs "),
            new DynamicCellFormat<Money>() {
                public String getCellContent(Money pObject)
                {
                    return pObject.currency;
                }
            },
            new DynamicCellFormat<Money>()
            {
                public String getCellContent(Money pObject)
                {
                    return String.valueOf(pObject.amount);
                }
            },
            new LiteralCellFormat(" for this item.")});

        Assert.assertEquals("It costs CAN$3.14 for this item.",
                     rowFormat.format(new Money("CAN$", 3.14f)));
    }

    @org.junit.Test
    public void test_formatTitle_noPresentation() throws Exception
    {
        final SingleRowFormat rowFormat = new SingleRowFormat<Money>(new CellFormat[] {
            new LiteralCellFormat("T1", "It costs "),
            new DynamicCellFormat<Money>("CURRENCY") {
                public String getCellContent(Money pObject)
                {
                    return pObject.currency;
                }
            },
            new DynamicCellFormat<Money>("PRICE")
            {
                public String getCellContent(Money pObject)
                {
                    return String.valueOf(pObject.amount);
                }
            },
            new LiteralCellFormat(" for this item.")});

        Assert.assertEquals("T1CURRENCYPRICE", rowFormat.formatTitle(false));
    }

    @org.junit.Test
    public void test_format_withPresentation() throws Exception
    {
    	final SingleRowFormat rowFormat = new SingleRowFormat<Money>(new CellFormat[] {
            new LiteralCellFormat(null, "It costs ", new CellPresentation(10, 100)),
            new DynamicCellFormat<Money>(null, null, new CellPresentation(5, 10)) {
                public String getCellContent(Money pObject)
                {
                    return pObject.currency;
                }
            },
            new DynamicCellFormat<Money>(null, null, new CellPresentation(10, 10))
            {
                public String getCellContent(Money pObject)
                {
                    return String.valueOf(pObject.amount);
                }
            },
            new LiteralCellFormat(null, " for this item.", new CellPresentation(0, 9))});

        final String expected = "It costs  CAN$ 3.14       for this";
        final String actual = rowFormat.format(new Money("CAN$", 3.14f));

        printExpectedAndActual(expected, actual);

        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void test_formatTitle_withPresentation() throws Exception
    {
        final SingleRowFormat rowFormat = new SingleRowFormat<Money>(new CellFormat[] {
            new LiteralCellFormat("T1", "It costs ", new CellPresentation(10, 100)),
            new DynamicCellFormat<Money>("CURRENCY", null, new CellPresentation(5, 10)) {
                public String getCellContent(Money pObject)
                {
                    return pObject.currency;
                }
            },
            new DynamicCellFormat<Money>("PRICE", null, new CellPresentation(10, 100))
            {
                public String getCellContent(Money pObject)
                {
                    return String.valueOf(pObject.amount);
                }
            },
            new LiteralCellFormat(null, " for this item.", new CellPresentation(0, 9))});

        final String expected = "T1        CURRENCYPRICE     ";
        final String actual = rowFormat.formatTitle(false);
        printExpectedAndActual(expected, actual);

        Assert.assertEquals(expected, actual);
    }

    private void printExpectedAndActual(final String pExpected, final String pActual)
    {
        System.out.println(getName() + ":\n" +
                           "Expected: '" + pExpected + "'\n" +
                           "Actual  : '" + pActual + "'");
    }

    ///////////////////////////////////////
    ////    INNER CLASSES

}
