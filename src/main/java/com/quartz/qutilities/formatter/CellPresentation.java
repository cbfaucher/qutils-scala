/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.formatter;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class CellPresentation
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    static public enum TruncationMode {truncate, abbreviate};

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private int cellMinimumLength = 0;
    private int cellMaximumLength = Integer.MAX_VALUE;
    private TruncationMode truncationMode = TruncationMode.truncate;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public CellPresentation()
    {
        this(0, Integer.MAX_VALUE, TruncationMode.truncate);
    }

    public CellPresentation(int pCellMinimumMaximumLength)
    {
        this(pCellMinimumMaximumLength, pCellMinimumMaximumLength, TruncationMode.truncate);
    }

    public CellPresentation(int pCellMinimumMaximumLength, TruncationMode pTruncationMode)
    {
        this(pCellMinimumMaximumLength, pCellMinimumMaximumLength, pTruncationMode);
    }

    public CellPresentation(int pCellMinimumLength, int pCellMaximumLength)
    {
        this(pCellMinimumLength, pCellMaximumLength, TruncationMode.truncate);
    }

    public CellPresentation(int pCellMinimumLength, int pCellMaximumLength, TruncationMode pTruncationMode)
    {
        cellMinimumLength = pCellMinimumLength;
        cellMaximumLength = pCellMaximumLength;
        truncationMode = pTruncationMode;
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public int getCellMinimumLength()
    {
        return cellMinimumLength;
    }

    public void setCellMinimumLength(int pCellMinimumLength)
    {
        cellMinimumLength = pCellMinimumLength;
    }

    public int getCellMaximumLength()
    {
        return cellMaximumLength;
    }

    public void setCellMaximumLength(int pCellMaximumLength)
    {
        cellMaximumLength = pCellMaximumLength;
    }

    public TruncationMode getTruncationMode()
    {
        return truncationMode;
    }

    public void setTruncationMode(TruncationMode pTruncationMode)
    {
        truncationMode = pTruncationMode;
    }

    public String toString()
    {
        return "Min=" + cellMinimumLength + "/Max=" + cellMaximumLength + "/Trunc.Mode=" + truncationMode;
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
