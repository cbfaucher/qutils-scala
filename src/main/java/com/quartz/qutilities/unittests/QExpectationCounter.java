package com.quartz.qutilities.unittests;

import com.mockobjects.ExpectationCounter;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class QExpectationCounter extends ExpectationCounter
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	///////////////////////////////////////
	////    CONSTRUCTORS

	public QExpectationCounter(String name)
	{
		super(name);

		this.setExpectNothing();
	}

	public QExpectationCounter(String name, int pNbExpectations)
	{
		super(name);

		this.setExpected(pNbExpectations);
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	///////////////////////////////////////
	////    INNER CLASSES
}
