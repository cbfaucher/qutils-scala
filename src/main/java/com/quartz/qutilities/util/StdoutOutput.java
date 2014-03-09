/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.util;



/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class StdoutOutput implements Output
{
    public StdoutOutput()
    {
    }

    public void clear()
    {
    }

    public void write(String pText)
    {
        System.out.print(pText);
    }

    public void writeln(String pText)
    {
        System.out.println(pText);
    }

    public void writeln()
    {
        System.out.println();
    }

    public void selectAll()
    {
    }

    public String getSelectedText()
    {
        return null;
    }

    public void setPosition(int pCharIndex)
    {
    }
}
