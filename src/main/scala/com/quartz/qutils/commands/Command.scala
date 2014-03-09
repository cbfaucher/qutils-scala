package com.quartz.qutils.commands

/**
 * A 'command' is a single- or multi-keyword linked to a behaviour, along with a description
 */
trait Command extends (CommandContext => Unit) {
  def command: String
  def description: Option[String]

  override def toString(): String = s"""$command: ${description.getOrElse("<none>")}"""
}

/**
 * Companion object, for creating a command:
 * <pre>
 *    val cmd = Command("do this", "This is my description", ctx => { .... } )
 * </pre>
 */
object Command {

  def apply(cmd: String, descriptionMsg: String, f: (CommandContext) => Unit): Command = new Command() {
    def command: String = cmd
    def description: Option[String] = Option(descriptionMsg).map(_.trim).filter(_.nonEmpty)
    def apply(ctx: CommandContext) { f(ctx) }
  }

  /**
   * A default 'help' command that either prints all available commands, with description, or
   * the help for the specified command.
   */
  val helpCommand = Command("help", "[command]: Prints help on command", ctx => {
    if (ctx.args.isEmpty)
      println(ctx.manager.supportedCommands.mkString("\n"))
    else
      ctx.args.foreach {
        cmd => {
          ctx.manager.getCommand(cmd) match {
            case Some(command) => println(command.toString())
            case None => println(s"ERROR: No command found for '$cmd'")
          }
        }
      }
  })

  /**
   * Finds existing commands having the given 'tag' in their keywords.
   */
  val findCommand = Command("find", "[tag]: Finds command having specified tag in it.", ctx => {
    if (ctx.args.length != 1)
      println("ERROR: only one argument is expected.")
    else {
      val tag = ctx.args(0)
      println {
        ctx.manager
          .supportedCommands
          .filter(_.contains(tag))
          .map(ctx.manager.getCommand)
          .flatten                                                    //  remove the option
          .map( _.toString() ) //  formats help line 'cmd: description'
          .mkString("\n")
      }
    }
  })

  /**
   * Calls <code>sys.exit(0)</code>, and thoerically, never returns...
   */
  val exitCommand = Command("exit", "Calls sys.exit(0)", ctx => { sys.exit(0) })
}

/**
 * Utility main to test invokation, 'cause it hard to unit tests that.
 */
object CommandApp extends App {

  val manager = new CommandManager(Command.helpCommand, Command.findCommand)
  manager("help")
  manager("help help")
  manager("help find")
  manager("find")
  manager("find find")
  manager ("not found")
}
