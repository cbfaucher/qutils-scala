package com.quartz.qutils.commands

/**
 * Context of execution for a command
 */
case class CommandContext(manager: CommandManager, command: Command, args: Seq[String]) {

  def apply(idx: Int): Option[String] = if (idx < args.length) Some(args(idx)) else None
}
