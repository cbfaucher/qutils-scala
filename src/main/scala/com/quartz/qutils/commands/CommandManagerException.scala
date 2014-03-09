package com.quartz.qutils.commands

/**
 * Created with IntelliJ IDEA.
 * User: Christian
 * Date: 2/23/14
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
class CommandManagerException(msg: String = "", nested: Throwable = null) extends RuntimeException(msg, nested)

class CommandNotFoundException(commandLine: String) extends CommandManagerException(s"Command not found: $commandLine")
