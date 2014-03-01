package com.quartz.qutilities.util;

import com.quartz.qutilities.unittests.QTestCase;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import junit.framework.TestSuite;
import junit.framework.JUnit4TestAdapter;

/**
 * Unit Test cases for {@link ArrayUtilities}
 *
 * @author lmcchbf
 * @since 15-Sep-2004
 */
public class ArrayUtilitiesTest extends QTestCase
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS
	
	static public final junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(ArrayUtilitiesTest.class);
	}	

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	///////////////////////////////////////
	////    CONSTRUCTORS

	public ArrayUtilitiesTest()
	{
		super();
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

    @Test
    public void test_shiftLeft_newArrayHasContent() throws Exception
	{
		final String[] original = { "aaa", "bbb", "ccc", "ddd", "eee"};

		final String[] expected = {"ccc", "ddd", "eee"};
		final String[] actual   = (String[]) ArrayUtilities.shiftLeft(original, 2, String.class);

		ArrayUtils.isEquals(expected, actual);
	}

    @Test
    public void test_shiftLeft_newArray_isEmpty() throws Exception
	{
		final Integer[] original = { new Integer(123), new Integer(456), new Integer(789)};

		final Integer[] expected = {};
		final Integer[] actual   = (Integer[]) ArrayUtilities.shiftLeft(original, 3, Integer.class);

		ArrayUtils.isEquals(expected, actual);
	}

    @Test
    public void test_shiftLeft_moreShitsThanElements() throws Exception
	{
		final Float[] original = { new Float(1.23), new Float(4.56), new Float(7.89)};

		final Float[] expected = {};
		final Float[] actual   = (Float[]) ArrayUtilities.shiftLeft(original, 10, Float.class);

		ArrayUtils.isEquals(expected, actual);
	}

    @Test
    public void test_shiftLeft_shiftZeroElements() throws Exception
	{
		final Object[] original = { Boolean.TRUE, new Float(4.56), new Long(1234567890123456789l)};

		final Object[] expected = original;
		final Object[] actual   = ArrayUtilities.shiftLeft(original, 0, Object.class);

		ArrayUtils.isEquals(expected, actual);
	}

    @Test
    public void test_shiftLeft_orignalArrayIsEmpty() throws Exception
	{
		final Object[] original = {};

		final Object[] expected = original;
		final Object[] actual   = ArrayUtilities.shiftLeft(original, 3, Object.class);

		ArrayUtils.isEquals(expected, actual);
	}

    @Test
    public void test_shiftLeft_originalArrayIsNull() throws Exception
	{
		final Object[] original = null;

		final Object[] expected = original;
		final Object[] actual   = ArrayUtilities.shiftLeft(original, 8, Object.class);

		ArrayUtils.isEquals(expected, actual);
	}

    @Test
    public void test_shiftLeft_nbToShiftIsNegative() throws Exception
	{
		final Object[] original = { Boolean.TRUE, new Float(4.56), new Long(1234567890123456789l)};

		try
		{
			ArrayUtilities.shiftLeft(original, -3, Object.class);
			fail("Exception expected.");
		}
		catch (IllegalArgumentException e)
		{
			Assert.assertEquals("Number of spaces to shift cannot be negative", e.getMessage());
		}
	}

    @Test
    public void test_shiftLeft_classArgIsNull() throws Exception
	{
		final Object[] original = { Boolean.TRUE, new Float(4.56), new Long(1234567890123456789l)};

		try
		{
			ArrayUtilities.shiftLeft(original, 3, null);
			fail("Exception expected.");
		}
		catch (IllegalArgumentException e)
		{
			Assert.assertEquals("Type of returned array not specified.", e.getMessage());
		}
	}

	///////////////////////////////////////
	////    INNER CLASSES
}
