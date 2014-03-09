package com.quartz.qutilities.io;

import java.io.PrintWriter;
import java.io.Writer;

/**
 * A simple context for {@link XMLWriter#write(XMLWriterContext)}
 *
 * @author Christian Faucher (fauchc02)
 */
public class XMLWriterContext
{
    public static final float DEFAULT_XML_VERSION = 1.0f;

    private PrintWriter   logWriter;
    private boolean       verbose;
    private boolean       closeStreamOrWriter;
    private float         xmlVersion = DEFAULT_XML_VERSION;
    private String        encoding = null;

    /**
     * Creates an instance with verbose DISABLED, and forcing XML output to be closed as well.
     */
    public XMLWriterContext()
    {
        this(DEFAULT_XML_VERSION, null, true, null, false);
    }

    /**
     * Creates an instance with verbose DISABLED
     */
    public XMLWriterContext(boolean pCloseStreamOrWriter)
    {
        this(DEFAULT_XML_VERSION, null, pCloseStreamOrWriter, null, false);
    }

    /**
     * Creates an instance with specified verbose (on/off) and log writer.
     *
     * @param pCloseStreamOrWriter If true, xml output stream/writer will be closed after being used.
     * @param pLogWriter Writer for log entries
     * @param pVerbose true for verbose, false othwerise (mute).
     */
    public XMLWriterContext(float pXmlVersion, String pEncoding, boolean pCloseStreamOrWriter, Writer pLogWriter, boolean pVerbose)
    {
        this.xmlVersion = pXmlVersion;
        this.encoding = pEncoding;
        this.closeStreamOrWriter = pCloseStreamOrWriter;
        logWriter = (pLogWriter != null
                        ? (pLogWriter instanceof PrintWriter ? (PrintWriter) pLogWriter : new PrintWriter(pLogWriter, true))
                        : null);
        verbose = pVerbose;
    }

    public String getEncoding()
    {
        return encoding;
    }

    public void setEncoding(String pEncoding)
    {
        encoding = pEncoding;
    }

    public float getXmlVersion()
    {
        return xmlVersion;
    }

    public void setXmlVersion(float pXmlVersion)
    {
        xmlVersion = pXmlVersion;
    }

    public PrintWriter getLogWriter()
    {
        return logWriter;
    }

    public void setLogWriter(PrintWriter pLogWriter)
    {
        logWriter = pLogWriter;
    }

    public boolean isVerbose()
    {
        return verbose;
    }

    public void setVerbose(boolean pVerbose)
    {
        verbose = pVerbose;
    }

    public void setCloseStreamOrWriter(boolean pCloseStreamOrWriter)
    {
        closeStreamOrWriter = pCloseStreamOrWriter;
    }

    public boolean isCloseStreamOrWriter()
    {
        return closeStreamOrWriter;
    }

    void log(String pMsg)
    {
        if (this.verbose && this.logWriter != null) logWriter.write("DEBUG: " + pMsg + "\n");
    }
}
