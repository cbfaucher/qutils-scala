package com.quartz.qutilities.unittests;

import com.mockobjects.Verifiable;
import com.mockobjects.util.Verifier;
import org.apache.commons.lang.ArrayUtils;
import org.junit.After;
import static org.junit.Assert.fail;

/**
 * Basic unit test for Quartz stuff
 *
 * @author Christian
 * @since Quartz...
 */
public class QTestCase implements Verifiable
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

    protected String name;

    ///////////////////////////////////////
	////    CONSTRUCTORS

    public QTestCase()
    {
        this("Test Name Unknown");
    }

    public QTestCase(String s)
	{
		super();
        name = s;
    }

	///////////////////////////////////////
	////    INSTANCE METHODS

    public String getName()
    {
        return name;
    }

    /**
	 *
	 * @param pBaseFilename Base filename, without path
	 * @return The full filename (including path) for a test data file located in unit tests's data directory
	 *
	 * @see #getUnitTestDataDirectory()
	 */
	protected String makeDataFilename(String pBaseFilename)
	{
		final String dataDir = getUnitTestDataDirectory();
		final StringBuffer propertyFilename = new StringBuffer(100);
		propertyFilename.append(dataDir);
		if (dataDir.endsWith("/") == false)
		{
			propertyFilename.append("/");
		}
		propertyFilename.append(pBaseFilename);

		return propertyFilename.toString();
	}

	/**
	 *
	 * @return The directory that contains test data files ($/QUtilities/unit_tests/data), defined by the system property <b>quartz.ut.data.dir</b>
	 */
	private String getUnitTestDataDirectory()
	{
		final String property = System.getProperty("quartz.ut.data.dir");
		if (property == null)
		{
			fail("Data directory not specified!  Use -Dquartz.ut.data.dir=<dir>");
		}
		return property;
	}

	protected void print(final Object pExpectedOptions, final Object pActualOptions)
	{
		System.out.println(this.getName() + ":\n" +
		                   "EXPECTED: " + pExpectedOptions + "\n" +
		                   "ACTUAL  : " + pActualOptions);
	}

	protected void assertEquals(Object[] pExpected, Object[] pActual)
	{
		print(ArrayUtils.toString(pExpected), ArrayUtils.toString(pActual));

		if (ArrayUtils.isEquals(pExpected, pActual) == true) return;

		fail("Array not equal.\nEXPECTED: " + ArrayUtils.toString(pExpected) + "\nACTUAL  : "  + ArrayUtils.toString(pActual));
	}

    @After
    final public void verify()
	{
		Verifier.verifyObject(this);
	}

	///////////////////////////////////////
	////    INNER CLASSES
}
