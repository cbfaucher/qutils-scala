package com.quartz.qutils.commands

/**
 * Created with IntelliJ IDEA.
 * User: Christian
 * Date: 2/23/14
 * Time: 6:12 PM
 * To change this template use File | Settings | File Templates.
 */
case class CommandKey(keyworkChain: Seq[String]) {

  def shift: CommandKey =
    if (keyworkChain.size > 0) CommandKey(keyworkChain.dropRight(1))
    else throw new RuntimeException("Empty key - cannot shift.")

  override def toString: String = keyworkChain.mkString(" ")
}

object CommandKey {
  implicit class FromString(str: String) {
    def toCommandKey = CommandKey {
      str.split(Array(' ', '\t'))
        .map(_.trim)
        .filter(_.nonEmpty)
        .toSeq
    }
  }
}

