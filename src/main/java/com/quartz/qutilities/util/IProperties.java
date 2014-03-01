package com.quartz.qutilities.util;

import org.joda.time.LocalDate;
import org.joda.time.YearMonthDay;

import java.util.Collection;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public interface IProperties
{
    Object setProperty(String pName, String pValue);

    void setProperty(String pName, String[] pValues);

    String[] getPropertyAsArray(String pName, String[] pDefaultValues);

    <R> Collection<R> getPropertyAsCollection(String pName, Converter<String, R> pConverter );

    <T>void setProperty(String pPropName, Collection<T> pCollection, Converter<T, String> pConverter);

    String getProperty(String pName, String pDefaultValue);

    Integer getPropertyAsInt(String pName, Integer pDefaultValue);

    Float getPropertyAsFloat(String pName, Float pDefaultValue);

    Boolean getPropertyAsBoolean(String pName, Boolean pDefaultValue);

    void setProperty(String pName, boolean pSelected);

    LocalDate getPropertyAsYearMonthDay(String pName, LocalDate pDefaultValue);

    void setProperty(String pName, LocalDate pFromDate);

    void setProperty(String pName, Integer pValue);
}
