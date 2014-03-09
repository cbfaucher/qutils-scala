package com.quartz.qutils

import com.quartz.qutilities.util.QUserProperties
import org.joda.time.LocalDate
import scala.reflect.ClassTag

/**
 * Created with IntelliJ IDEA.
 * User: Christian
 * Date: 2/28/14
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
object QUserPropertiesHelper {

  implicit class FromUserProperties(userProps: QUserProperties) {
    private[this] val converters: Map[Class[_], Converter[_]] = Map(
      classOf[LocalDate] -> new LocalDateConverter
    )


    def using[T](userProp: String, defaultValue: => T)(f: (T) => T)(implicit manifest: ClassTag[T]): T = {

      val converter: Converter[T] = converters.getOrElse(
        manifest.runtimeClass, throw new RuntimeException(s"No converter found for ${manifest.runtimeClass.getName}"))
        .asInstanceOf[Converter[T]]

      val theDefaultValue = converter.get(userProp, defaultValue)(userProps)
      val v = f(theDefaultValue)
      converter.set(userProp, v)(userProps)
      v
    }
  }
}

private trait Converter[T] {
  def toT(str: String): T
  def fromT(value: T): String = value.toString
  def get(userPropName: String, defaultValue: => T)(implicit userProperties: QUserProperties): T
  def set(userPropName: String, value: T)(implicit userProperties: QUserProperties)
}

private class LocalDateConverter extends Converter[LocalDate] {

  def get(userPropName: String, defaultValue: => LocalDate)(implicit userProperties: QUserProperties): LocalDate =
    userProperties.getPropertyAsYearMonthDay(userPropName, defaultValue)

  def set(userPropName: String, value: LocalDate)(implicit userProperties: QUserProperties) {
    userProperties.setProperty(userPropName, value)
  }

  def toT(str: String): LocalDate = LocalDate.parse(str)
}
