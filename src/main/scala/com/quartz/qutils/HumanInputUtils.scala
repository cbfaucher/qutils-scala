package com.quartz.qutils

import org.joda.time.LocalDate
import com.quartz.qutilities.io.IOUtilities._
import org.joda.time.format.DateTimeFormat

/**
 * Bunch of methods for human inputs...
 */
object HumanInputUtils {

  private val dateFormatter = DateTimeFormat.forPattern("yyyy/MM/dd")

  def askDate(question: String, defaultValue: LocalDate): LocalDate =
    try {
      val v = askQuestion(question, defaultValue.toString(dateFormatter))
      LocalDate.parse(v, dateFormatter)
    } catch {
      case e:IllegalArgumentException => askDate(question, defaultValue)
    }

}
