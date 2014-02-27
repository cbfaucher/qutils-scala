package com.quartz.qutils.commands

import CommandKey.FromString

/**
 * Created with IntelliJ IDEA.
 * User: Christian
 * Date: 2/23/14
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
class CommandManager(commands: Command*) {

  def apply(cmdLine: String) {
    execute(cmdLine)
  }

  private[this] val commandToCommand: Map[CommandKey, CommandExecutor] = commands.map {
      cmd => (cmd.command, cmd)
    }
    .map {
      case (words, cmd) => (words.toCommandKey, new CommandExecutor(this, cmd))
    }
    .toMap

  /**
   * @return Supported commands, sorted by alphabetical order
   */
  def supportedCommands: Seq[String] =
    commandToCommand
      .keySet
      .map( _.keyworkChain.mkString(" ") )
      .toSeq
      .sorted

  def execute(commandLine: String) {

    val elements = commandLine.toCommandKey.keyworkChain

    findCommand(CommandKey(elements)) match {
      case Some( (key, cmd) ) => {
        //  split args part...
        val arguments = elements.drop(key.keyworkChain.length)

        cmd.execute(arguments)
      }
      case None => throw new CommandNotFoundException(commandLine)
    }
  }

  private[commands] def getCommand(cmdLine: String): Option[Command] = findCommand(cmdLine.toCommandKey) match {
    case Some( (key, executor) ) => Some(executor.command)
    case None => None
  }

  private[this] def findCommand(key: CommandKey): Option[(CommandKey, CommandExecutor)] = commandToCommand.get(key) match {
    case Some(cmd) => Some( (key, cmd) )
    case None => if (key.keyworkChain.length > 1) findCommand(key.shift) else None
  }
}

private class CommandExecutor(val commandManager: CommandManager, val command: Command) {
  def execute(arguments: Seq[String]) {
    command(CommandContext(commandManager, command, arguments))
  }
}


