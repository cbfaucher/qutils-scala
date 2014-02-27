package com.quartz.qutils.commands

/**
 * Created with IntelliJ IDEA.
 * User: Christian
 * Date: 2/23/14
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */

trait Command extends (CommandContext => Unit) {
  def command: String
  def description: Option[String]
}

object Command {

  def apply(cmd: String, descriptionMsg: String, f: (CommandContext) => Unit): Command = new Command() {
    def command: String = cmd
    def description: Option[String] = Option(descriptionMsg).map(_.trim).filter(_.nonEmpty)
    def apply(ctx: CommandContext) { f(ctx) }
  }

  val helpCommand = Command("help", "help [command]: Prints help on command", ctx => {
    if (ctx.args.isEmpty)
      println(ctx.manager.supportedCommands.mkString("\n"))
    else
      ctx.args.foreach {
        cmd => {
          ctx.manager.getCommand(cmd) match {
            case Some(command) => println(s"${command.command}: ${command.description.getOrElse("<none>")}")
            case None => println(s"ERROR: No command found for '$cmd'")
          }
        }
      }
  })

  val findCommand = Command("find", "find [tag]: Finds command having specified tag in it.", ctx => {
    if (ctx.args.length != 1)
      println("ERROR: only one argument is expected.")
    else {
      val tag = ctx.args(0)
      println {
        ctx.manager
          .supportedCommands
          .filter(_.contains(tag))
          .map(ctx.manager.getCommand)
//          .filter(_.isDefined)
          .flatten
          .map(cmd => { s"""${cmd.command}: ${cmd.description}""" } )
          .mkString("\n")
      }
    }
  })
}

object CommandApp extends App {

  val manager = new CommandManager(Command.helpCommand, Command.findCommand)
  manager.execute("help")
  manager.execute("help help")
  manager.execute("help find")
  manager.execute("find")
  manager.execute("find find")
  manager.execute("not found")
}
