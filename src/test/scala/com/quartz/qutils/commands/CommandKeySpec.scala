package com.quartz.qutils.commands

import org.scalatest.{FlatSpec, Matchers}

class CommandKeySpec extends FlatSpec with Matchers {

  "Two CommandKey" should "be equal" in {
    CommandKey(Seq("first", "second", "third")) should be (CommandKey( Seq("first", "second", "third") ))
  }
}
