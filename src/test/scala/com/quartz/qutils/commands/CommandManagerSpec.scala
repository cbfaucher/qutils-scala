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
      new Command("load tickers", (mgr, cmd) => { println("load tickers") }, Some("Loads tickers informations") ),
      new Command("load\texchange", (mgr, cmd) => { println("load exchanges") }),
      //  will trim values
      new Command("  find  \t  signals  ", (mgr, cmd) => { println("find signals") })
    ))

    manager.supportedCommands should be( Seq("find signals", "load exchange", "load tickers"))
  }

  "Executing an existing command" should "execute the so-mentioned command (no args)" in {

    var marker: Option[String] = None

    val manager = new CommandManager(Seq(
      new Command("load tickers", (mgr, cmd) => sys.error("No call expected"), Some("Loads tickers informations") ),
      new Command("load\texchange", (mgr, cmd) => { marker = Some("Called!") }  ),
      //  will trim values
      new Command("  find  \t  signals  ", (mgr, cmd) => sys.error("No call expected") )
    ))

    manager.execute("load exchange")

    marker should be ( Some("Called!") )
  }

  "Executing a non existing command" should "throw an exception" in {
    val manager = new CommandManager(Seq(
      new Command("load tickers", (mgr, cmd) => { println("load tickers") }, Some("Loads tickers informations") ),
      new Command("load\texchange", (mgr, cmd) => { println("load exchanges") }),
      //  will trim values
      new Command("  find  \t  signals  ", (mgr, cmd) => { println("find signals") })
    ))

    intercept[CommandNotFoundException] {
      manager.execute("command not found")
    }
  }

  "Executing a command with arguments" should "find the correct command" in {
    fail()
  }
}
