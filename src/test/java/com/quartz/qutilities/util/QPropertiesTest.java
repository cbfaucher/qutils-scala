package com.quartz.qutilities.util;

import com.quartz.qutilities.unittests.QTestCase;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import org.junit.Assert;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URL;

/**
 * Unit Test cases for {@link QProperties}
 *
 * @author lmcchbf                                               
 * @since 10-Aug-2004
 */
public class QPropertiesTest extends QTestCase
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS
	
	static public final Test suite()
	{
		return new JUnit4TestAdapter(QPropertiesTest.class);
	}	

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	///////////////////////////////////////
	////    CONSTRUCTORS

	public QPropertiesTest()
	{
		super();
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

    @org.junit.Test
    public void test_load_filename() throws Exception
	{
//		final String propFilename = "C:\\Documents and Settings\\Christian\\My Documents\\9095-2458\\Development\\qutilities\\src\\test\\java\\some.properties";
		final String propFilename = "./src/test/java/some.properties";

		final QProperties actual = QProperties.load(propFilename);

		final QProperties expected = new QProperties();
		expected.setProperty("p1", "v1");
		expected.setProperty("p2", "v2");
		expected.setProperty("p3", "v3");

		Assert.assertEquals(expected, actual);
	}

	@org.junit.Test(expected = NullPointerException.class)
    public void test_load_filename_null() throws Exception
	{
			QProperties.load((String) null);
			fail("Exception expected.");
	}

    @org.junit.Test(expected = NullPointerException.class)
    public void test_load_file_null() throws Exception
	{
			QProperties.load((File) null);
			fail("Exception expected.");
	}

    @org.junit.Test(expected = NullPointerException.class)
    public void test_load_URL_null() throws Exception
	{
			QProperties.load((URL) null);
			fail("Exception expected.");
	}

    @org.junit.Test(expected = NullPointerException.class)
    public void test_load_inputStream_null() throws Exception
	{
			QProperties.loadFromStream(null);
			fail("Exception expected.");
	}



	///////////////////////////////////////
	////    INNER CLASSES
}
