package net.edmacdonald.jvmcli.history

import net.edmacdonald.jvmcli.entity.{CommandArgument, CommandInvocation}
import net.edmacdonald.jvmcli.history.HistoryAdvice.getBean
import net.edmacdonald.jvmcli.repository.CommandInvocationRepository
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.{Aspect, Before, Pointcut}
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.context.{ApplicationContext, ApplicationContextAware}
import org.springframework.stereotype.Component

import java.math.BigInteger
import scala.util.chaining.*

@Aspect
@Component
class HistoryAdvice extends ApplicationContextAware {

  @Pointcut("execution(public int picocli.CommandLine.execute(String...))")
  private def commandExecution(): Unit = {}

  @Before("net.edmacdonald.jvmcli.history.HistoryAdvice.commandExecution()")
  def recordInvocation(joinPoint: JoinPoint): Unit = {
    val service: HistoryService = getBean(classOf[HistoryService])
    service.recordInvocation(
      joinPoint.getArgs()(0)
        .asInstanceOf[Array[String]]
    )
  }

  override def setApplicationContext(ctx: ApplicationContext): Unit = {
    HistoryAdvice.context = ctx
  }
}

object HistoryAdvice {
  @volatile private var context: ApplicationContext | Null = null

  def getBean[T](clazz: Class[T]): T = {
    context.getBean(clazz)
  }
}

@Component
class HistoryService @Autowired()(val commandInvocationRepository: CommandInvocationRepository,
                                  @Value("${spring.application.name}") val applicationName: String) {
  def recordInvocation(args: Array[String]): Unit = {
    commandInvocationRepository.save(
      new CommandInvocation().pipe { ci =>
        ci.command = applicationName
        args.foreach(arg =>
          ci.addArg(new CommandArgument().pipe { ca =>
            ca.arg = arg
            ca.argumentOrder = BigInteger.ZERO
            ca
          }))
        ci
      }
    )
  }
}