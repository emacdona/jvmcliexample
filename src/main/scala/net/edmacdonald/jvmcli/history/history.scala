package net.edmacdonald.jvmcli.history

import net.edmacdonald.jvmcli.history.History.getBean
import net.edmacdonald.jvmcli.repository.CommandInvocationRepository
import org.aspectj.lang.annotation.{Aspect, Before, Pointcut}
import org.springframework.context.{ApplicationContext, ApplicationContextAware}
import org.springframework.stereotype.Component

@Aspect
@Component
class History extends ApplicationContextAware {

  @Pointcut("execution(public * picocli.CommandLine.execute(..))")
  private def commandExecution(): Unit = {}

  @Before("net.edmacdonald.jvmcli.history.History.commandExecution()")
  def log(): Unit = {
    var commandInvocationRepository: CommandInvocationRepository =
      getBean(classOf[CommandInvocationRepository])

    println(s"EFM: Before command execution: ${commandInvocationRepository}")
  }

  override def setApplicationContext(ctx: ApplicationContext): Unit = {
    History.context = ctx
  }
}

object History {
  @volatile private var context: ApplicationContext = _

  def getBean[T](clazz: Class[T]): T = {
    context.getBean(clazz)
  }
}
