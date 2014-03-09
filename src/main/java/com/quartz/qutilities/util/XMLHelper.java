/* Generated by Together */

package com.quartz.qutilities.util;

import com.quartz.qutilities.logging.ILog;
import com.quartz.qutilities.logging.LogManager;
import com.quartz.qutilities.io.XMLWriter;
import com.quartz.qutilities.io.XMLWriterContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;

/**
 * An helper class for XML common tasks, such as reading a DOM Document out of an XML.
 */
final public class XMLHelper
{
    static private final ILog LOG = LogManager.getLogger(XMLHelper.class);

    /**
     * Creates an empty XML Document.
     * @return A empty XML document
     * @throws ParserConfigurationException If creation fails because of parser configuratino.
     */
    public static Document newDocument() throws ParserConfigurationException
    {
         return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    }

    /**
     * Opens a DOM Document from given URL.
     *
     * @param pXmlURL URL to the XML document to open
     * @param pEntityResolver If specified, validation is ACTIVATED, using this entity resolver to resolve DTD/XSD
     *
     * @return A DOM Document opened from source
     *
     * @throws javax.xml.parsers.ParserConfigurationException If parser configuration is wrong (sic)
     * @throws org.xml.sax.SAXException If parsing fails
     * @throws java.io.IOException If URL cannot be read successfully.
     */
    public static Document openDocument(URL pXmlURL, EntityResolver pEntityResolver) throws ParserConfigurationException, SAXException, IOException
    {
        return openDocument(new InputSource(pXmlURL.openStream()), pEntityResolver);
    }

    /**
     * Opens a DOM Document from the given source.
     *
     * @param pInputSource XML source to read
     * @param pEntityResolver If specified, validation is ACTIVATED, using this entity resolver to resolve DTD/XSD
     *
     * @param pEntityResolver
     * @return A DOM Document opened from source
     *
     * @throws ParserConfigurationException If parser configuration is wrong (sic)
     * @throws SAXException If parsing fails
     * @throws IOException If URL cannot be read successfully.
     */
    public static Document openDocument(final InputSource pInputSource, final EntityResolver pEntityResolver)
            throws IOException, SAXException, ParserConfigurationException
    {
        InputStream stream = null;

        try
        {
            DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
            dfactory.setNamespaceAware(true);

            final DocumentBuilder documentBuilder = dfactory.newDocumentBuilder();
            if (pEntityResolver != null)
            {
                dfactory.setValidating(true);
                documentBuilder.setEntityResolver(pEntityResolver);
            }
            else
            {
                dfactory.setValidating(false);
            }

            documentBuilder.setErrorHandler(new DefaultErrorHandler());

            return documentBuilder.parse(pInputSource);
        }
        finally
        {
            if (stream != null)
            {
                stream.close();
            }
        }
    }

    public static void write(Document pDocument, OutputStream pOutputStream, final XMLWriterContext pXmlWriterContext) throws IOException
    {
        final OutputStreamWriter osWriter = new OutputStreamWriter(pOutputStream);
        write(pDocument, osWriter, pXmlWriterContext);
    }

    public static void write(Document pDocument, final Writer pOsWriter, final XMLWriterContext pXmlWriterContext)
            throws IOException
    {
        final XMLWriter writer = new XMLWriter(pDocument, pOsWriter, pXmlWriterContext.isCloseStreamOrWriter());
        try
        {
            writer.write(pXmlWriterContext);
            pOsWriter.flush();
        }
        finally
        {
            writer.close();
        }
    }

    /**
     * Gets the attribute value.
     * @param pElement XML Element containing the attribute
     * @param pAttributeName Name of the attribute
     * @param pDefaultValue  Default value (may be null)
     * @return The attribute's value, or the default value is not such attribute is defined
     */
    static public String getAttribute(Element pElement, String pAttributeName, String pDefaultValue)
    {
        final String value = pElement.getAttribute(pAttributeName);
        return (value != null ? value : pDefaultValue);
    }

    /**
     * Gets the attribute value as a {@link int}.
     * @param pElement XML Element containing the attribute
     * @param pAttributeName Name of the attribute
     * @param pDefaultValue  Default value
     * @return The attribute's value, or the default value is not such attribute is defined
     */
    static public int getAttributeAsInt(Element pElement, String pAttributeName, int pDefaultValue)
    {
        final String value = XMLHelper.getAttribute(pElement, pAttributeName, String.valueOf(pDefaultValue));
        return Integer.parseInt(value.trim());
    }

    /**
     * NOT IMPLEMENTED CORRECTLY.  DO NOT USE WITHOUT FIXING IT!
     */
    static public String toString(NamedNodeMap pMap)
    {
        final int length = pMap.getLength();
        for (int i = 0; i < length; i++)
        {
            final Node node = pMap.item(i);
            System.out.println("#" + i + " - Node Type: " + node.getNodeType());
        }

        return "TODO";
    }

    /**
     * Replaces spaces, tabs, CR and other invalid characters in an XML element name by
     * the underscore character, to make a valid XML element name.
     *
     * @param pValue Wanted name
     * @return Valid XML Element name, from <tt>pValue</tt>
     */
    public static String makeValidElementName(String pValue)
    {
        String work = pValue;

        work = work.replace(' ', '_');
        work = work.replace('\t', '_');
        work = work.replace('\n', '_');
        work = work.replace('\r', '_');

        return work;
    }

    /**
     * A local {@link org.xml.sax.ErrorHandler} for XML parsing done in this class.
     *
     * @author fauchc02
     */
    static private class DefaultErrorHandler implements ErrorHandler
    {
        DefaultErrorHandler()
        {
        }

        public void warning(SAXParseException exception) throws SAXException
        {
            LOG.warn(toString(exception));
        }

        public void error(SAXParseException exception) throws SAXException
        {
            LOG.error(toString(exception));
            throw exception;
        }

        public void fatalError(SAXParseException exception) throws SAXException
        {
            LOG.fatal(toString(exception));
            throw exception;
        }

        private String toString(SAXParseException exception)
        {
            final StringBuffer buffer  = new StringBuffer(1000);
            buffer.append(exception.getMessage());
            buffer.append("\n\tLine    =").append(exception.getLineNumber());
            buffer.append("\n\tColumn  =").append(exception.getColumnNumber());
            buffer.append("\n\tPublicID=").append(exception.getPublicId());
            buffer.append("\n\tSystemID=").append(exception.getSystemId());
            return buffer.toString();
        }
    }
}
