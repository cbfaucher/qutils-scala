/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.formatter;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.JUnit4TestAdapter;
import com.quartz.qutilities.unittests.QTestCase;
import com.quartz.qutilities.util.DefaultToString;
import org.junit.Assert;

/**
 * Unit Test cases for {@link BeanUtilsCellFormat}
 *
 * @author lmcchbf
 * @since 27-Jun-2006
 */
public class BeanUtilsCellFormatTest extends QTestCase
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS
	
    static public final Test suite()
    {
        return new JUnit4TestAdapter(BeanUtilsCellFormatTest.class);
    }	

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public BeanUtilsCellFormatTest()
    {
        super();
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    @org.junit.Test
    public void test_getCellContent() throws Exception
    {
    	final BeanUtilsCellFormat<Money> cellFormat = new BeanUtilsCellFormat<Money>("amount");

        Assert.assertEquals("3.14", cellFormat.getCellContent(new Money("CAN", 3.14f)));
    }

    @org.junit.Test
    public void test_getCellContent_nullvalue() throws Exception
    {
    	final BeanUtilsCellFormat<Money> cellFormat = new BeanUtilsCellFormat<Money>(null, "currency", new DefaultToString("Dawn!  It is null."), null);

        Assert.assertEquals("Dawn!  It is null.", cellFormat.getCellContent(new Money(null, 3.14f)));
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
