package net.edmacdonald.jvmcli.history

import org.aspectj.lang.annotation.{Aspect, Before, Pointcut}

@Aspect
class History {

  @Pointcut("execution(public * picocli.CommandLine.execute(..))")
  private def commandExecution(): Unit = {}

  @Before("net.edmacdonald.jvmcli.history.History.commandExecution()")
  def log(): Unit = {
    println("EFM: Before command execution")
  }
}