/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.formatter;

import com.quartz.qutilities.util.ToString;
import com.quartz.qutilities.util.DefaultToString;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public abstract class CellFormat
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private String              title;
    private CellPresentation    cellPresentation = new CellPresentation();

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    protected CellFormat()
    {
        this("");
    }

    protected CellFormat(String pTitle)
    {

    }

    protected CellFormat(String pTitle, CellPresentation pCellPresentation)
    {
        title = (pTitle != null ? pTitle : "");
        cellPresentation = (pCellPresentation != null ? pCellPresentation : new CellPresentation());
    }


    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String pTitle)
    {
        title = pTitle;
    }

    public CellPresentation getCellPresentation()
    {
        return cellPresentation;
    }

    public void setCellPresentation(CellPresentation pCellPresentation)
    {
        cellPresentation = pCellPresentation;
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
