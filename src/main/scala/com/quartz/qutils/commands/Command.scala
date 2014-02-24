package com.quartz.qutils.commands

/**
 * Created with IntelliJ IDEA.
 * User: Christian
 * Date: 2/23/14
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */

class Command(val command: String,
              val action: (CommandManager, Command) => Unit,
              val description: Option[String] = None)
