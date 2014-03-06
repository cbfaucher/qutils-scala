package com.quartz.qutils

import java.sql.Timestamp
import org.joda.time.{DateTime, LocalDate}

/**
 * Bunch of converters
 */
object Converters {

  //  for native conversions scala --> Java
  type jlShort = java.lang.Short
  type jlInt = java.lang.Integer
  type jlLong = java.lang.Long

  type jlFloat = java.lang.Float
  type jlDouble = java.lang.Double

  implicit class FromLocalDate(val date: LocalDate) {
    def toTimestamp: Timestamp = Option(date).map(dt => new Timestamp(dt.toDateTimeAtStartOfDay.toDate.getTime)).getOrElse(null)
  }

  implicit class FromTimestamp(val timestamp: Timestamp) {
    def toLocalDate: LocalDate = Option(timestamp).map(ts => new LocalDate(ts.getTime)).getOrElse(null)
    def toDateTime: DateTime = Option(timestamp).map(ts => new DateTime(ts.getTime)).getOrElse(null)
  }
}
