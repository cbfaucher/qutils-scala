package com.quartz.qutils.commands

import CommandKey.FromString

/**
 * The 'Command Manager' knowing all available commands.
 * <p>
 * [[com.quartz.qutils.commands.Command]]s are specified at construction time.
 * <p>
 * You can execute a command as follow: <code>manager("keyword1 keyword2 arg1 arg2")</code>
 */
class CommandManager(commands: Command*) {

  def apply(commandLine: String) {
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


