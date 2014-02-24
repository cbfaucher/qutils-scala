package com.quartz.qutils.commands

/**
 * Created with IntelliJ IDEA.
 * User: Christian
 * Date: 2/23/14
 * Time: 9:07 PM
 * To change this template use File | Settings | File Templates.
 */
case class CommandContext(manager: CommandManager, command: Command, args: Seq[String])
