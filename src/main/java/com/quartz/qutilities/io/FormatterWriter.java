/*
 * Copyright (c) 2005 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.io;

import java.io.Writer;
import java.io.IOException;

/**
 * A {@link Writer} that supports indentations of line, where, when a CR is found, an arbitrary level of indentation
 * is written, prior to resuming writing the passed-in buffer.
 *
 * @author Christian
 * @since Quartz...
 */
public class FormatterWriter extends Writer
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    static public final String DEFAULT_INDENT = "\t";

    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    final private Writer    underlyingWriter;
    final private boolean   closeUnderlyingWriter;
    final private String    indentation;

    private int     indent   = 0;
    private boolean headOfLine = true;
    private boolean isClosed = false;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public FormatterWriter(Writer pUnderlyingWriter)
    {
        this(pUnderlyingWriter, true);
    }

    public FormatterWriter(Writer pUnderlyingWriter, boolean pCloseUnderlyingWriter)
    {
        this(pUnderlyingWriter, pCloseUnderlyingWriter, DEFAULT_INDENT);
    }

    public FormatterWriter(Writer pUnderlyingWriter, boolean pCloseUnderlyingWriter, String pIndentation)
    {
        underlyingWriter = pUnderlyingWriter;
        closeUnderlyingWriter = pCloseUnderlyingWriter;
        indentation = pIndentation;
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    /**
     * Augment by 1 the level of indentation.
     * <p>
     * This does NOT write the indentation though.
     */
    public void indent()
    {
        indent++;
    }

    /**
     * Removes 1 level of indentation.
     */
    public void unindent()
    {
        if (indent > 0) indent--;
    }

    /**
     * Writes the caracter buffer, and indents when a new line if found in buffer.
     *
     * @param cbuf char buffer
     * @param off Start index.
     * @param len Length
     * @throws IOException
     */
    public void write(char cbuf[], int off, int len) throws IOException
    {
        checkIfClosed();

        for (int i = off; i < (off+len); i++)
        {
            if (headOfLine == true) writeIndent();

            char c = cbuf[i];

            this.underlyingWriter.write(c);

            if ('\n' == c) headOfLine = true;
        }
    }

    public void flush() throws IOException
    {
        checkIfClosed();

        underlyingWriter.flush();
    }

    public void close() throws IOException
    {
        if (isClosed) return;

        try
        {
            if (this.closeUnderlyingWriter == true)
            {
                this.underlyingWriter.close();
            }
        }
        finally
        {
            isClosed = true;
        }
    }

    public boolean isHeadOfLine()
    {
        return headOfLine;
    }        

    private void writeIndent() throws IOException
    {
        for (int i = 0; i < indent; i++) underlyingWriter.write(this.indentation);

        headOfLine = false;
    }

    private void checkIfClosed()
            throws IOException
    {
        if (isClosed) throw new IOException("Closed.");
    }


    ///////////////////////////////////////
    ////    INNER CLASSES
}
