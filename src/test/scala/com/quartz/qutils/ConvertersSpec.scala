package com.quartz.qutils

import org.scalatest.{FlatSpec, Matchers}
import org.joda.time.{DateTime, LocalDate}
import java.sql.Timestamp
import java.util.Calendar

class ConvertersSpec extends FlatSpec with Matchers {

  "FromLocalDate" should "convert toTimestamp" in {
    import Converters.FromLocalDate

    val calendar = Calendar.getInstance()
    calendar.set(2014, 10 - 1/*zero based, of course!*/, 31, 0, 0, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    new LocalDate(2014, 10, 31).toTimestamp should be (new Timestamp(calendar.getTimeInMillis))

    val localDate: LocalDate = null
    localDate.toTimestamp should be (null)
  }

  "FromTimestamp" should "convert to LocalDate and DateTime" in {
    import Converters.FromTimestamp

    val calendar = Calendar.getInstance()
    calendar.set(1975, 6/*zero based, of course!*/, 27, 23, 5, 32)
    calendar.set(Calendar.MILLISECOND, 0)
    val timestamp = new Timestamp(calendar.getTimeInMillis)

    timestamp.toLocalDate should be (new LocalDate(1975, 7, 27))
    timestamp.toDateTime should be (new DateTime(1975, 7, 27, 23, 5, 32))

    val nullTimestamp: Timestamp = null
    nullTimestamp.toLocalDate should be (null)
    nullTimestamp.toDateTime should be (null)
  }
}
