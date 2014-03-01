package com.quartz.qutilities.unittests;

import com.mockobjects.ExpectationValue;

/**
 * A {@link ExpectationValue} that fails if not setup properly (not silent like MockObjects')
 *
 * @author Christian
 * @since Quartz...
 */
public class QExpectationValue extends ExpectationValue
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	///////////////////////////////////////
	////    CONSTRUCTORS

	public QExpectationValue(String name)
	{
		super(name);

		this.setExpectNothing();
	}


	///////////////////////////////////////
	////    INSTANCE METHODS

	///////////////////////////////////////
	////    INNER CLASSES
}
