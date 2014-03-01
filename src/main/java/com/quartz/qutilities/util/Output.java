/*
 * Copyright (c) 2006 9095-2458 Quebec Inc. All Rights Reserved.
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
public interface Output
{
    public void clear();

    public void write(String pText);
    public void writeln(String pText);
    void writeln();

    void selectAll();
    String getSelectedText();

    void setPosition(int pCharIndex);

}
