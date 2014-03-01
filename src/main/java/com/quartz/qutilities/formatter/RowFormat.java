/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.formatter;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Formats a row according to the {@link CellFormat}s composing it.
 *
 * @see DynamicCellFormat
 * @see LiteralCellFormat
 * @see CellPresentation
 *
 * @author Christian
 * @since Quartz...
 */
public abstract class RowFormat<FORMAT_TYPE, CELL_TYPE>
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    final protected List<CellFormat> cellFormats = new ArrayList<CellFormat>();

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public RowFormat()
    {
    }

    public RowFormat(CellFormat[] pFormats)
    {
        if (pFormats != null)
        {
            for (CellFormat c : pFormats) cellFormats.add(c);
        }
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    public void add(CellFormat pCellFormat)
    {
        if (pCellFormat == null) throw new IllegalArgumentException("Null cell format");
        cellFormats.add(pCellFormat);
    }

    public String formatTitle(boolean pUnderlined) throws IllegalArgumentException
    {
        return formatTitle(pUnderlined, "");
    }

    public String formatTitle(boolean pUnderlined, String pExtraTitle) throws IllegalArgumentException
        {
        final StringBuffer buffer = new StringBuffer(1024);

        for (CellFormat c : cellFormats) buffer.append(padOrTrunk(c, c.getTitle()));

        if (pExtraTitle != null) buffer.append(pExtraTitle);

        if (pUnderlined)
        {
            final int length = buffer.length();
            buffer.append("\n").append(StringUtils.repeat("-", length));
        }

        return buffer.toString();
    }

    abstract public String format(FORMAT_TYPE pObjectToFormat) throws FormatException, IllegalArgumentException;

    protected String formatRow(CELL_TYPE o) throws FormatException
    {
        final StringBuffer buffer = new StringBuffer(1024);

        for (CellFormat c : this.cellFormats)
        {
            buffer.append(getCellContent(c, o));
        }

        return buffer.toString();
    }

    protected String getCellContent(CellFormat c, CELL_TYPE pObject) throws FormatException
    {
        String cellContent;

        if (c instanceof LiteralCellFormat)
        {
            cellContent = ((LiteralCellFormat) c).getLiteralContent();
        }
        else if (c instanceof DynamicCellFormat)
        {
            cellContent = ((DynamicCellFormat) c).getCellContent(pObject);
        }
        else
        {
            throw new IllegalArgumentException("Unsupported type of CellFormat: " + c.getClass().getName());
        }

        return padOrTrunk(c, cellContent);
    }

    private String padOrTrunk(CellFormat c, String pContent)
    {
        String theContent = (pContent != null ? pContent : "");

        final CellPresentation cellPresentation = c.getCellPresentation();
        if (cellPresentation == null) return pContent;

        if (theContent.length() < cellPresentation.getCellMinimumLength())
        {
            theContent = StringUtils.rightPad(theContent, cellPresentation.getCellMinimumLength(), ' ');
        }

        if (theContent.length() > cellPresentation.getCellMaximumLength())
        {
            theContent =
                    cellPresentation.getTruncationMode() == CellPresentation.TruncationMode.truncate
                    ? theContent.substring(0, cellPresentation.getCellMaximumLength())
                    : StringUtils.abbreviate(theContent, cellPresentation.getCellMaximumLength());
        }

        return theContent;
    }


    ///////////////////////////////////////
    ////    INNER CLASSES
}
