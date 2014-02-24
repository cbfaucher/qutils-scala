package com.quartz.qutils.commands

import org.scalatest.{FlatSpec, Matchers}

/**
 * Created with IntelliJ IDEA.
 * User: Christian
 * Date: 2/23/14
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */
class CommandManagerSpec extends FlatSpec with Matchers {

  it should "return the expected commands" in {
    val manager = new CommandManager(Seq(
      new Command("load tickers", (ctx) => { println("load tickers") }, Some("Loads tickers informations") ),
      new Command("load\texchange", (ctx) => { println("load exchanges") }),
      //  will trim values
      new Command("  find  \t  signals  ", (ctx) => { println("find signals") })
    ))

    manager.supportedCommands should be( Seq("find signals", "load exchange", "load tickers"))
  }

  "Executing an existing command" should "execute the so-mentioned command (no args)" in {

    var marker: Option[String] = None

    val manager = new CommandManager(Seq(
      new Command("load tickers", (ctx) => sys.error("No call expected"), Some("Loads tickers informations") ),
      new Command("load\texchange", (ctx) => { marker = Some("Called!") }  ),
      //  will trim values
      new Command("  find  \t  signals  ", (ctx) => sys.error("No call expected") )
    ))

    manager.execute("load exchange")

    marker should be ( Some("Called!") )
  }

  "Executing a non existing command" should "throw an exception" in {
    val manager = new CommandManager(Seq(
      new Command("load tickers", (ctx) => { println("load tickers") }, Some("Loads tickers informations") ),
      new Command("load\texchange", (ctx) => { println("load exchanges") }),
      //  will trim values
      new Command("  find  \t  signals  ", (ctx) => { println("find signals") })
    ))

    intercept[CommandNotFoundException] {
      manager.execute("command not found")
    }
  }

  "Executing a command with arguments" should "find the correct command" in {
    var called = false
    var exchangeName: Option[String] = None

    val manager = new CommandManager(Seq(
      new Command("load tickers", (ctx) => { println("load tickers") }, Some("Loads tickers informations") ),
      new Command("load\texchange", (ctx) => { called = true; exchangeName = ctx.args.headOption }),
      //  will trim values
      new Command("  find  \t  signals  ", (ctx) => { println("find signals") })
    ))

    manager.execute("load exchange NYSE")

    called should be (true)
    exchangeName should be (Some("NYSE"))
  }
}
