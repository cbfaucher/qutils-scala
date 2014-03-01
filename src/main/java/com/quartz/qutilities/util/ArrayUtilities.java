package com.quartz.qutilities.util;

import java.lang.reflect.Array;

/**
 * This class complements the Jakarta Commons-lang's {@link org.apache.commons.lang.ArrayUtils} class.
 * <p>
 * So if you dont find wnat you need here, be curious and go see in {@link org.apache.commons.lang.ArrayUtils} first!
 *
 * @author Christian
 * @since Quartz...
 */
final public class ArrayUtilities
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	static public Object[] shiftLeft(Object[] pOriginalArray, int pNbElements, Class pArrayType)
		throws IllegalArgumentException
	{
		if (pNbElements < 0) throw new IllegalArgumentException("Number of spaces to shift cannot be negative");
		if (pArrayType == null) throw new IllegalArgumentException("Type of returned array not specified.");

		if (pOriginalArray == null) return null;

		final int newLength = pOriginalArray.length - pNbElements;

		final Object[] newArray = (Object[]) Array.newInstance(
		        pArrayType,
		        (newLength >= 0 ? newLength : 0));

		for (int i = 0; i < newLength; i++)
		{
			newArray[i] = pOriginalArray[pNbElements + i];
		}

		return newArray;
	}

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	///////////////////////////////////////
	////    CONSTRUCTORS

	private ArrayUtilities()
	{
	}

	///////////////////////////////////////
	////    INSTANCE METHODS

	///////////////////////////////////////
	////    INNER CLASSES
}
