/*
 * Copyright (c) 2006 Your Corporation. All Rights Reserved.
 */
package com.quartz.qutilities.digester;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.FromXmlRuleSet;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.net.URL;

/**
 * An helper class that loads a {@link org.apache.commons.digester.Digester} from
 * an XML rule set.
 * <p>
 * Yes, I know, there are the {@link org.apache.commons.digester.xmlrules.DigesterLoader} and
 * {@link org.apache.commons.digester.xmlrules.FromXmlRuleSet} classes.
 * <p>
 * However I ran into problems with Digester 1.6 and 1.7 in Tomcat, having the following error:
 * <tt><pre>
 * 2006-05-19 14:01:13,670 [http-8000-Processor25] ERROR Digester - Digester.getParser:
 * org.xml.sax.SAXNotRecognizedException: Feature: http://apache.org/xml/features/validation/dynamic
 *	at org.apache.crimson.parser.XMLReaderImpl.setFeature(XMLReaderImpl.java:219)
 *	at org.apache.crimson.jaxp.SAXParserImpl.setFeatures(SAXParserImpl.java:150)
 *  at org.apache.crimson.jaxp.SAXParserImpl.<init>(SAXParserImpl.java:133)
 *	at org.apache.crimson.jaxp.SAXParserFactoryImpl.newSAXParserImpl(SAXParserFactoryImpl.java:113)
 *	at org.apache.crimson.jaxp.SAXParserFactoryImpl.setFeature(SAXParserFactoryImpl.java:141)
 *	at org.apache.commons.digester.parser.XercesParser.configureXerces(XercesParser.java:185)
 *	at org.apache.commons.digester.parser.XercesParser.newSAXParser(XercesParser.java:138)
 *	at org.apache.commons.digester.ParserFeatureSetterFactory.newSAXParser(ParserFeatureSetterFactory.java:71)
 *	at org.apache.commons.digester.Digester.getParser(Digester.java:692)
 *	at org.apache.commons.digester.Digester.getXMLReader(Digester.java:899)
 *	at org.apache.commons.digester.Digester.parse(Digester.java:1666)
 * </pre></tt>
 * I created this class to force my own {@link javax.xml.parsers.SAXParser} in the {@link org.apache.commons.digester.Digester}
 * instance;  a thing is not done but the two classes aforementionned....
 *
 * @author fauchc02
 * @since 2006-05-19 14:14:06
 */
final public class DigesterHelper
{
    static public Digester loadFromXmlRuleSet(URL pDigesterRuleUrl)
            throws IllegalArgumentException, SAXException, ParserConfigurationException
    {
        if (pDigesterRuleUrl == null) throw new IllegalArgumentException("Digester Rules' URL is null.");
        
        final Digester digester = new Digester(SAXParserFactory.newInstance().newSAXParser());

        final FromXmlRuleSet ruleSet = new FromXmlRuleSet(pDigesterRuleUrl);
        ruleSet.addRuleInstances(digester);

        return digester;
    }
}
