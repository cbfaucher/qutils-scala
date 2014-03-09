package com.quartz.qutilities.beans;

/**
 * A dummy Java Bean for unit testing
 *
 * @author Christian
 * @since Quartz...
 */
public class DummyBean
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	private int doodah = 0;

	///////////////////////////////////////
	////    CONSTRUCTORS

	public DummyBean()
	{
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	public int getDoodah()
	{
		return doodah;
	}

	public void setDoodah(int pDoodah)
	{
		doodah = pDoodah;
	}

	private void privateMethod()
	{
    	//  does nothing
	}

	///////////////////////////////////////
	////    INNER CLASSES
}
