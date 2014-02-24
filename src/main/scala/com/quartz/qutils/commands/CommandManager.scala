package com.quartz.qutils.commands

import CommandKey.FromString

/**
 * Created with IntelliJ IDEA.
 * User: Christian
 * Date: 2/23/14
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
class CommandManager(commands: Seq[Command]) {

  private[this] val commandToCommand: Map[CommandKey, CommandExecutor] = commands.map {
      cmd => {
        (cmd.command, cmd)
      }
    }
    .map {
      case (words, cmd) => (words.toCommandKey, new CommandExecutor(this, cmd))
    }
    .toMap

  def supportedCommands: Seq[String] =
    commandToCommand
      .keySet
      .map( _.keyworkChain.mkString(" ") )
      .toSeq
      .sorted

  def execute(commandLine: String) {

    val key: CommandKey = commandLine.toCommandKey

    commandToCommand.get(key) match {
      case Some(cmd) => cmd.execute
      case None => throw new CommandNotFoundException(commandLine)
    }
  }
}

private class CommandExecutor(val commandManager: CommandManager, val command: Command) {
  def execute {
    command.action(commandManager, command)
  }
}


