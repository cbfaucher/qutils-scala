package com.quartz.qutilities.io;

import org.w3c.dom.*;

import java.io.IOException;
import java.io.Writer;

import com.quartz.qutilities.xml.NodeListIterator;

/**
 * This is a writer to write a DOM {@link org.w3c.dom.Document} to an underlying writer.
 * <p>
 * At this time, it does NOT support all types of nodes, but can be upgraded to support them very easily.  Node
 * types supported are:  Document, Element, Attr and Text nodes.
 *
 * @author Christian Faucher (fauchc02)
 */
public class XMLWriter
{
    /**
     * Document to write
     */
    final private Document          document;

    /**
     * A formatter to pretty-print the XML
     */
    final private FormatterWriter   formatter;

    /**
     * Constructor
     *
     * @param pDocument Document to write
     * @param pWriter Underlying writer to use
     * @param pCloseWriter True if underlying writer must be closed when this XMLWriter is closed, false otherwise.
     */
    public XMLWriter(Document pDocument, Writer pWriter, boolean pCloseWriter)
    {
        document = pDocument;
        formatter = new FormatterWriter(pWriter, pCloseWriter);
    }

    /**
     * Writes the document, using the {@link XMLWriterContext}
     * @param pContext Writing context
     * @throws IOException If an error occurs
     */
    public void write(XMLWriterContext pContext) throws IOException
    {
        writeXmlVersion(pContext);

        write(pContext, this.document);
    }

    /**
     * This method switches among supported node types to other <tt>write(...)</tt> methods, based on node type.
     * This is where you need to add a line to support other types of nodes.
     *
     * @param pContext Context
     * @param pNode Node to write
     * @throws IOException If an error occurs
     */
    void write(XMLWriterContext pContext, Node pNode) throws IOException
    {
        final int type = pNode.getNodeType();

        switch (type)
        {
            case Node.DOCUMENT_NODE:
                {
                    final Document document = (Document) pNode;
                    write(pContext, document);
                    break;
                }

            case Node.ELEMENT_NODE:
                this.write(pContext, (Element) pNode, true);
                break;

            case Node.ATTRIBUTE_NODE:
                this.write(pContext, (Attr) pNode);
                break;

            case Node.TEXT_NODE:
                this.write(pContext, (Text) pNode);
                break;

            case Node.CDATA_SECTION_NODE:
                this.write(pContext, (CDATASection) pNode);
                break;

            default:
                throw new IOException("Unsupported node type: " + pNode.getNodeType());
        }

    }

    /**
     * Writes the document, including the DOCTYPE, if any.
     *
     * @param pContext Context
     * @param pDocument Document to write
     * @throws IOException If an error occurs
     */
    private void write(XMLWriterContext pContext, Document pDocument)
            throws IOException
    {
        pContext.log("Found 'Document' node.");

        if (pDocument.getDoctype() != null)
        {
            this.write(pContext, pDocument.getDoctype());
        }

        this.write(pContext, document.getDocumentElement(), false);
    }

    private void write(XMLWriterContext pContext, DocumentType pDocumentType) throws IOException
    {
        pContext.log("Writing DOCTYPE...");

//        <!DOCTYPE
//            ex SYSTEM "ex.dtd" [ <!ENTITY foo "foo"> <!ENTITY bar
//            "bar"> <!ENTITY bar "bar2"> <!ENTITY % baz "baz">
//        ]> <ex/>

//        <!DOCTYPE connector PUBLIC '-//Sun Microsystems, Inc.//DTD Connector 1.0//EN' 'http://java.sun.com/dtd/connector_1_0.dtd'>

        this.formatter.write("<!DOCTYPE ");

        //  write root name
        this.formatter.write(pDocumentType.getName());
        this.formatter.write(" ");

        //  write PUBLIC or SYSTEM entry
        if (pDocumentType.getPublicId() != null)
        {
            this.formatter.write("PUBLIC ");
            this.formatter.write("'" + pDocumentType.getPublicId() + "' ");
            this.formatter.write("\"" + pDocumentType.getSystemId() + "\" ");
        }
        else
        {
            this.formatter.write("SYSTEM ");
            this.formatter.write("\"" + pDocumentType.getSystemId() + "\" ");
        }

        //  todo: support ENTITIES in DocType!
        if (pDocumentType.getEntities().getLength() > 0) throw new IOException("DTD's Entities not supported (yet).");

        //  todo: support NOTATIONS in DocType!
        if (pDocumentType.getNotations().getLength() > 0) throw new IOException("DTD's Notations not supported (yet).");

        this.formatter.write(">\n\n");
    }

    private void write(XMLWriterContext pContext, Element pElement, boolean pIndent) throws IOException
    {
        pContext.log("Writing 'Element' node <" + pElement.getNodeName() + "> Indent=" + pIndent);

        if (pElement == null) return;

        if (isEmpty(pElement.getNodeName()))
            throw new IllegalStateException("Element's name cannot be empty.");

        if (this.formatter.isHeadOfLine() == false)
        {
            this.formatter.write("\n");
        }

        if (pIndent)
        {
            this.formatter.indent();
        }

        formatter.write("<");
        formatter.write(pElement.getNodeName());

        if (pElement.hasAttributes())
        {
            //formatter.write(" ");
            this.write(pContext, pElement.getAttributes());
        }

        //
        // Write empty nodes as "<EMPTY />" to make sure version 3
        // and 4 web browsers can read empty tag output as HTML.
        // XML allows "<EMPTY/>" too, of course.
        //
        if (!pElement.hasChildNodes() )
        {
            formatter.write (" />\n");
        }
        else
        {
            // Patched by Mathieu Carbou 09/11/2004
            
            //formatter.write (">\n");	// ">" 
            formatter.write (">");

            writeChildren(pContext, pElement);

            //if (this.formatter.isHeadOfLine() == false) this.formatter.write("\n");
            
            formatter.write ("</");	// "</"
            formatter.write (pElement.getNodeName());
            formatter.write (">\n");	// ">"
        }

        this.formatter.unindent();
    }

    private void writeChildren(XMLWriterContext pContext, Node pNode) throws IOException
    {
        if (pNode.hasChildNodes() == false) return;

        final NodeListIterator childrenIt = new NodeListIterator(pNode.getChildNodes());
        while (childrenIt.hasNext())
        {
            Node childNode = (Node) childrenIt.next();
            write(pContext, childNode);
        }
    }

    private void write(XMLWriterContext pContext, NamedNodeMap pNamedNodeMap) throws IOException
    {
        final int length = pNamedNodeMap.getLength();

        for (int i = 0; i < length; i++)
        {
            // PATCHED BY MATHIEU CARBOU
            //if (i > 0) formatter.write(" ");
            if (i >= 0) formatter.write(" ");
            this.write(pContext, pNamedNodeMap.item(i));
        }
    }

    private void write(XMLWriterContext pContext, Attr pAttributeNode) throws IOException
    {
        final String name = pAttributeNode.getName();
        final String value = pAttributeNode.getValue();

        final String nodeName = pAttributeNode.getOwnerElement() != null
                ? pAttributeNode.getOwnerElement().getNodeName()
                : "???";
        pContext.log("Writing Attr '" + name + "=" + value + "' for Element <" + nodeName + ">");

        formatter.write(name);
        formatter.write("=\"");

        if ( !isEmpty(value) )
        {
            formatter.write(value);
        }

        formatter.write("\"");
    }

    private void write(XMLWriterContext pContext, Text pAttributeNode) throws IOException
    {
        pContext.log("Writing TEXT value...");
        final String textValue = pAttributeNode.getNodeValue();
        if (textValue != null)
        {
            //if (formatter.isHeadOfLine() == false) formatter.write("\n");
            formatter.indent();
            formatter.write(textValue);
            formatter.unindent();
            //formatter.write("\n");
        }
    }

    private void write(XMLWriterContext pContext, CDATASection pAttributeNode) throws IOException
    {
        pContext.log("Writing CATA value...");
        final String textValue = pAttributeNode.getNodeValue();
        if (textValue != null)
        {
            if (formatter.isHeadOfLine() == false) formatter.write("\n");
            formatter.indent();
            formatter.write("<![CDATA[\n");
            formatter.write(textValue);
            if (formatter.isHeadOfLine() == false) formatter.write("\n");            
            formatter.write("]]>\n");
            formatter.unindent();
            //formatter.write("\n");
        }
    }

    private void writeXmlVersion(XMLWriterContext pContext)
            throws IOException
    {
        formatter.write("<?xml version=\""+ pContext.getXmlVersion() + "\"");

        if (pContext.getEncoding() != null)
        {
            formatter.write(" encoding=\"" + pContext.getEncoding() + "\"");
        }

        formatter.write("?>\n\n");
    }

    private boolean isEmpty(String pStr)
    {
        if (pStr == null) return true;
        if (pStr.trim().length() == 0) return true;

        return false;
    }

    /**
     * Closes this XMLWriter.  Must be called to cleanup things
     *
     * @throws IOException If an error occurs.
     */
    public void close() throws IOException
    {
        formatter.close();
    }
}
