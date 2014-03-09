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
public class LiteralCellFormat extends CellFormat
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private String literalContent = null;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public LiteralCellFormat()
    {
        this(null);
    }

    public LiteralCellFormat(String pLiteralContent)
    {
        this(null, pLiteralContent);
    }

    public LiteralCellFormat(String pTitle, String pLiteralContent)
    {
        this(pTitle, pLiteralContent, null);
    }

    public LiteralCellFormat(String pTitle, String pLiteralContent, CellPresentation pCellPresentation)
    {
        super(pTitle, pCellPresentation);
        literalContent = pLiteralContent;
    }



    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public String getLiteralContent()
    {
        return literalContent;
    }

    public void setLiteralContent(String pLiteralContent)
    {
        literalContent = pLiteralContent;
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
