package com.quartz.qutilities.xml;

import org.w3c.dom.NodeList;

import java.util.Iterator;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class NodeListIterator implements Iterator
{
    final private NodeList nodeList;
    private int index = 0;

    public NodeListIterator(NodeList pNodeList)
    {
        nodeList = pNodeList;
    }

    public boolean hasNext()
    {
        return (index < nodeList.getLength());
    }

    public Object next()
    {
        return nodeList.item(index++);
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }
}