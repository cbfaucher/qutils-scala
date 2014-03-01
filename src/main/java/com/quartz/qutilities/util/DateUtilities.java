/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.util;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.TimeOfDay;
import org.joda.time.YearMonthDay;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
final public class DateUtilities
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    private static final Pattern TIME_PATTERN = Pattern.compile("([0-9]{2}):([0-9]{2}):([0-9]{2}).*");
    private static final Pattern DATE_TIME_PATTERN = Pattern.compile("([0-9]{4})-([0-9]{1,2})-([0-9]{1,2})[ ]+([0-9]{1,2}):([0-9]{1,2}):([0-9]{1,2})");

    ///////////////////////////////////////
    ////    STATIC METHODS

    private DateUtilities()
    {
    }

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    static public YearMonthDay toYearMonthDay(String pFormattedDate)
    {
        if(pFormattedDate == null) return null;
        
        final StringTokenizer tokenizer = new StringTokenizer(pFormattedDate, "/-");

        if (tokenizer.countTokens() != 3) throw new IllegalArgumentException("Unsupported date format: " + pFormattedDate + ".  Must be yyyy/mm/dd");

        final int year = new Integer(tokenizer.nextToken());
        final int monthOfYear = new Integer(tokenizer.nextToken());
        final int dayOfMonth = new Integer(tokenizer.nextToken());
        return new YearMonthDay(year, monthOfYear, dayOfMonth);        
    }

    static public LocalDate toLocalDate(String pFormattedDate)
    {
        if(pFormattedDate == null) return null;

        final StringTokenizer tokenizer = new StringTokenizer(pFormattedDate, "/-");

        if (tokenizer.countTokens() != 3) throw new IllegalArgumentException("Unsupported date format: " + pFormattedDate + ".  Must be yyyy/mm/dd");

        final int year = new Integer(tokenizer.nextToken());
        final int monthOfYear = new Integer(tokenizer.nextToken());
        final int dayOfMonth = new Integer(tokenizer.nextToken());
        return new LocalDate(year, monthOfYear, dayOfMonth);        
    }

    static public TimeOfDay toTimeOfDay(String pTime)
    {
        //  00:00:04.047
        final Matcher matcher = TIME_PATTERN.matcher(pTime);
        if (!matcher.matches()) throw new IllegalArgumentException("Invalid time format: " + pTime);

        final int hour = new Integer(matcher.group(1));
        final int minute = new Integer(matcher.group(2));
        final int second = new Integer(matcher.group(3));

        return new TimeOfDay(hour, minute, second);
    }

    public static Date toJavaSqlDate(YearMonthDay pYearMonthDay)
    {
        return new Date(pYearMonthDay.toDateMidnight().toDate().getTime());
    }

    public static Date toJavaSqlDate(LocalDate pYearMonthDay)
    {
        return new Date(pYearMonthDay.toDateMidnight().toDate().getTime());
    }

    public static Timestamp toJavaSqlTimestamp(DateTime pDateTime)
    {
        return (pDateTime != null ? new Timestamp(pDateTime.getMillis()) : null);   
    }

    public static YearMonthDay toYearMonthDay(Date pDate)
    {
        if (pDate == null) return null;
        return new YearMonthDay(pDate.getTime());
    }

    public static LocalDate toLocalDate(java.util.Date pDate)
    {
        if (pDate == null) return null;
        return new LocalDate(pDate.getTime());
    }

    public static DateTime toDateTime(Timestamp pTimestamp)
    {
        if (pTimestamp == null) return null;
        return new DateTime(pTimestamp.getTime());
    }

    public static DateTime toDateTime(String pToken, boolean pAcceptsNull)
    {
        if (StringUtils.isBlank(pToken))
        {
            if (pAcceptsNull) return null;
            else throw new IllegalArgumentException("Null token.");
        }

        //2005-07-04 09:15:50

        final Matcher matcher = DATE_TIME_PATTERN.matcher(pToken);
        if (!matcher.matches()) throw new IllegalArgumentException("Token does not match DateTime pattern: " + pToken);

        return new DateTime(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(3)),
                Integer.parseInt(matcher.group(4)),
                Integer.parseInt(matcher.group(5)),
                Integer.parseInt(matcher.group(6)),
                0);
    }
}
