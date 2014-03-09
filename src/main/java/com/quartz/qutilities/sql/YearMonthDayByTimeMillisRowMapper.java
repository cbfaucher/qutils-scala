/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.sql;

import org.joda.time.YearMonthDay;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Finds existing dates, either for all exchanges or a given one, with optional limit in number of results.
 *
 * @author Christian
 * @since Quartz...
 */
public class YearMonthDayByTimeMillisRowMapper implements ParameterizedRowMapper<YearMonthDay>
{
    public YearMonthDayByTimeMillisRowMapper()
    {
    }

    public YearMonthDay mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new YearMonthDay(rs.getDate(1).getTime());
    }
}
