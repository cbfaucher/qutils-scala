package com.quartz.qutils.commands

import org.scalatest.{FlatSpec, Matchers}

/**
 * Created with IntelliJ IDEA.
 * User: Christian
 * Date: 2/23/14
 * Time: 6:26 PM
 * To change this template use File | Settings | File Templates.
 */
class CommandKeySpec extends FlatSpec with Matchers {

  "Two CommandKey" should "be equal" in {
    CommandKey(Seq("first", "second", "third")) should be (CommandKey( Seq("first", "second", "third") ))
  }
}
