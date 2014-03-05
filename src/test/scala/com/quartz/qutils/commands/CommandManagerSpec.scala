package com.quartz.qutils.commands

import org.scalatest.{FlatSpec, Matchers}

class CommandManagerSpec extends FlatSpec with Matchers {

  class Fixture {
    val loadTickersCmd = Command("load tickers", "Load tickers information", ctx => {println("load tickers")})
    val loadExchangeCmd = Command("load\texchange", null, ctx => { println("load exchanges") })
    val findSignalsCmd = Command("  find  \t  signals  ", null, ctx => { println("find signals") })

    val defaultManager = new CommandManager( loadTickersCmd, Command.helpCommand, loadExchangeCmd, findSignalsCmd )
  }

  it should "return the expected commands" in new Fixture {

    defaultManager.supportedCommands should be( Seq("find signals", "help", "load exchange", "load tickers"))
  }

  "Executing an existing single word command" should "execute the so-mentioned command (no args)" in new Fixture {
    var marker: Option[String] = None

    val manager = new CommandManager(
      loadTickersCmd,
      Command("help", "", ctx => { marker = Some("'help' called!") } ),
      findSignalsCmd
    )

    manager("help")

    marker should be (Some("'help' called!"))
  }

  "Executing an existing single word command" should "execute the so-mentioned command (with args)" in new Fixture {
    var marker: Option[String] = None

    val manager = new CommandManager(
      loadTickersCmd,
      Command("help", "", ctx => { marker = Some(s"""'help' called with arguments ${ctx.args.mkString(" ")}!""") } ),
      findSignalsCmd
    )

    manager("help arg1 arg2")

    marker should be (Some("'help' called with arguments arg1 arg2!"))
  }

  "Executing an existing multi-words command" should "execute the so-mentioned command (no args)" in new Fixture {

    var marker: Option[String] = None

    val manager = new CommandManager(
      loadTickersCmd,
      findSignalsCmd,
      Command("load\texchange", "", ctx => { marker = Some("CALLED!") }))

    manager("load exchange")

    marker should be ( Some("CALLED!") )
  }

  "Executing a non existing command" should "throw an exception" in new Fixture {

    intercept[CommandNotFoundException] {
      defaultManager("command not found")
    }
  }

  "Executing a command with arguments" should "find the correct command" in new Fixture {
    var called = false
    var exchangeName: Option[String] = None

    val manager = new CommandManager(
      loadTickersCmd, findSignalsCmd,
      Command("load\texchange", "", ctx => { called = true; exchangeName = ctx.args.headOption } )
    )

    manager("load exchange NYSE")

    called should be (true)
    exchangeName should be (Some("NYSE"))
  }
}
