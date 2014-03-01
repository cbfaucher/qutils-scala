package com.quartz.qutilities.util;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
abstract class AbstractProperties implements IProperties
{
    private static final String MULTI_VALUE_SEPARATOR = "|";
    
    protected AbstractProperties()
    {
    }

    public void setProperty(String pName, String[] pValues)
    {
        final StringBuffer buffer = new StringBuffer(1000);
        for (int i = 0; pValues != null && i < pValues.length; i++)
        {
            final String value = pValues[i];
            if (i > 0) buffer.append(MULTI_VALUE_SEPARATOR);
            buffer.append(value);
        }

        setProperty(pName, buffer.toString());
    }

    public String[] getPropertyAsArray(String pName, String[] pDefaultValues)
    {
        final String property = getProperty(pName, null);
        if (property == null) return pDefaultValues;

        final StringTokenizer tokenizer = new StringTokenizer(property, MULTI_VALUE_SEPARATOR);
        final String[] values = new String[tokenizer.countTokens()];

        for (int i = 0; i < values.length; i++)
        {
            values[i] = tokenizer.nextToken();
        }

        return values;
    }

    public <R> Collection<R> getPropertyAsCollection(String pName, Converter<String, R> pConverter )
    {
        final String[] stringValues = getPropertyAsArray(pName, null);

        final Collection<R> values = new ArrayList<R>();

        if (stringValues == null) return values;

        for (int i = 0 ; i < stringValues.length; i++)
        {
            values.add(pConverter.convert(stringValues[i]));
        }

        return values;
    }

    public <T>void setProperty(String pPropName, Collection<T> pCollection, Converter<T, String> pConverter)
    {
        final String[] values = new String[pCollection.size()];

        int i = 0;
        for (Iterator iterator = pCollection.iterator(); iterator.hasNext();)
        {
            T item = (T) iterator.next();
            values[i] = pConverter.convert(item);
            i++;
        }

        setProperty(pPropName, values);
    }

    public Integer getPropertyAsInt(String pName, Integer pDefaultValue)
    {
        return Integer.parseInt(getProperty(pName, String.valueOf(pDefaultValue)).trim());
    }

    public void setProperty(String pName, Integer pValue)
    {
        final String v = pValue != null? pValue.toString() : null;
        setProperty(pName, v);
    }

    public Float getPropertyAsFloat(String pName, Float pDefaultValue)
    {
        return new Float(getProperty(pName, String.valueOf(pDefaultValue)).trim());
    }

    public Boolean getPropertyAsBoolean(String pName, Boolean pDefaultValue)
    {
        return Boolean.valueOf(getProperty(pName, String.valueOf(pDefaultValue)));
    }

    public LocalDate getPropertyAsYearMonthDay(String pName, LocalDate pDefaultValue)
    {
        return DateUtilities.toLocalDate(getProperty(pName, (pDefaultValue != null ? pDefaultValue.toString() : null)));
    }

    public void setProperty(String pName, boolean pSelected)
    {
        setProperty(pName, String.valueOf(pSelected));
    }

    public void setProperty(String pName, LocalDate pFromDate)
    {
        setProperty(pName, (pFromDate != null ? pFromDate.toString() : null));
    }
}
