package net.edmacdonald.jvmcli.history

import org.aspectj.lang.annotation.{Around, Aspect, Before, Pointcut}
import org.springframework.stereotype.Component

@Aspect
@Component
class History {

  @Pointcut("execution(public int picocli.CommandLine.execute(String[])) && within(net.edmacdonald.jvmcli..*)")
  private def commandExecution(): Unit = {}

  @Before("net.edmacdonald.jvmcli.history.History.commandExecution()")
  def log(): Unit = {
    println("EFM: Before command execution")
  }
}