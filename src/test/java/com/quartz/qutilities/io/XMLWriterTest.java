/*
 * Copyright (c) 2005 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.io;

import com.quartz.qutilities.unittests.QTestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.JUnit4TestAdapter;
import org.junit.Assert;
import org.junit.Before;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringWriter;

/**
 * Unit Test cases for {@link XMLWriter}
 *
 * @author lmcchbf
 * @since 2-Jan-2005
 */
public class XMLWriterTest extends QTestCase
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS
	
    static public final Test suite()
    {
        return new JUnit4TestAdapter(XMLWriterTest.class);
    }	

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    private Document    document = null;
    private StringWriter buffer = null;
    private XMLWriter    xmlWriter = null;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    public XMLWriterTest()
    {
        super();
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    @Before
    public void setUp() throws Exception
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        document = dbf.newDocumentBuilder().newDocument();


        buffer = new StringWriter();
        xmlWriter = new XMLWriter(document, buffer, true);
    }

    @org.junit.Test
    public void test_emptyDocument() throws Exception
    {
        Element root = document.createElement("doodah");
        document.appendChild(root);

        this.xmlWriter.write(new XMLWriterContext(true));
        Assert.assertEquals("<?xml version=\"1.0\"?>\n" +
                "\n" +
                "<doodah />\n", this.buffer.toString());
    }

    @org.junit.Test
    public void test_document_withChildElements() throws Exception
    {
        Element root = document.createElement("root");
        document.appendChild(root);

        root.appendChild(document.createElement("a1"));
        final Element a2 = document.createElement("a2");
        root.appendChild(a2);
        final Element a3 = document.createElement("a3");
        root.appendChild(a3);

        //  child of a2
        a2.appendChild(document.createElement("a2a"));
        a2.appendChild(document.createElement("a2b"));
        a2.appendChild(document.createElement("a2c"));

        //  child of a3
        a3.appendChild(document.createElement("a3a"));
        a3.appendChild(document.createElement("a3b"));

        final String expected =
                "<root>\n" +
                        "\t<a1 />\n" +
                        "\t<a2>\n" +
                        "\t\t<a2a />\n" +
                        "\t\t<a2b />\n" +
                        "\t\t<a2c />\n" +
                        "\t</a2>\n" +
                        "\t<a3>\n" +
                        "\t\t<a3a />\n" +
                        "\t\t<a3b />\n" +
                        "\t</a3>\n" +
                        "</root>\n";

        xmlWriter.write(new XMLWriterContext(), this.document);

        Assert.assertEquals(expected, buffer.toString());
    }

    @org.junit.Test
    public void test_writeDocument_withChildNodesAndAttributes() throws Exception
    {
        Element root = document.createElement("root");
        document.appendChild(root);

        final Element a1 = document.createElement("a1");
        root.appendChild(a1);
        a1.setAttribute("attr1", "value1");
        a1.setAttribute("attr2", "value2");
        final Element a2 = document.createElement("a2");
        root.appendChild(a2);
        final Element a3 = document.createElement("a3");
        root.appendChild(a3);

        //  child of a2
        a2.setAttribute("attr3", "value3");

        a2.appendChild(document.createElement("a2a"));
        a2.appendChild(document.createElement("a2b"));
        a2.appendChild(document.createElement("a2c"));

        //  child of a3
        final Element a3a = document.createElement("a3a");
        a3.appendChild(a3a);
        a3.appendChild(document.createElement("a3b"));

        //  child of a3a
        final Element b1 = document.createElement("b1");
        b1.setAttribute("b11", "vb11");
        b1.setAttribute("b12", "vb12");
        a3a.appendChild(b1);
        a3a.appendChild(document.createElement("b2"));
        final Element b3 = document.createElement("b3");
        b3.setAttribute("b31", "vb31");
        a3a.appendChild(b3);

        final String expected =
                "<root>\n" +
                        "\t<a1 attr1=\"value1\" attr2=\"value2\" />\n" +
                        "\t<a2 attr3=\"value3\">\n" +
                        "\t\t<a2a />\n" +
                        "\t\t<a2b />\n" +
                        "\t\t<a2c />\n" +
                        "\t</a2>\n" +
                        "\t<a3>\n" +
                        "\t\t<a3a>\n" +
                        "\t\t\t<b1 b11=\"vb11\" b12=\"vb12\" />\n" +
                        "\t\t\t<b2 />\n" +
                        "\t\t\t<b3 b31=\"vb31\" />\n" +
                        "\t\t</a3a>\n" +
                        "\t\t<a3b />\n" +
                        "\t</a3>\n" +
                        "</root>\n";

        xmlWriter.write(new XMLWriterContext(),this.document);

        Assert.assertEquals(expected, buffer.toString());
    }

    @org.junit.Test
    public void test_writeTextNodes() throws Exception
    {
    	final Element root = this.document.createElement("root");
        this.document.appendChild(root);

        root.setAttribute("a1", "va1");
        root.setAttribute("a2", "va2");

        root.appendChild(this.document.createTextNode("Hello,"));
        root.appendChild(this.document.createTextNode(" World"));
        root.appendChild(this.document.createTextNode("!!!"));

        final String expected =
                "<root a1=\"va1\" a2=\"va2\">Hello, World!!!</root>\n";

        xmlWriter.write(new XMLWriterContext(), document);

        Assert.assertEquals(expected, buffer.toString());
    }

    ///////////////////////////////////////
    ////    INNER CLASSES
}
