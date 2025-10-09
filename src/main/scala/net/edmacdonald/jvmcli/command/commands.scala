package net.edmacdonald.jvmcli.command

import net.edmacdonald.jvmcli.command.filesys.Filesys
import net.edmacdonald.jvmcli.command.filesys.Filesys.FilesysSubCommand
import net.edmacdonald.jvmcli.command.history.History
import net.edmacdonald.jvmcli.command.history.History.HistorySubCommand
import net.edmacdonald.jvmcli.repository.CommandInvocationRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import picocli.CommandLine
import picocli.CommandLine.{Command, Parameters}

import java.nio.file.{Files, Paths}
import java.util.concurrent.Callable
import scala.compiletime.uninitialized
import scala.jdk.CollectionConverters.*
import scala.jdk.StreamConverters.*

@Component
@Command(name = "find", mixinStandardHelpOptions = true, description = Array("Find files."))
class Find extends FilesysSubCommand with Callable[Integer] {
  private var directory: String = "."

  @Parameters(index = "0", description = Array("Starting directory."))
  def setDirectory(d: String): Unit = {
    directory = d
  }

  override def call(): Integer = {
    try {
      Files.walk(Paths.get(directory)).forEach(path =>
        println(path)
      )
      return 0
    } finally {

    }
  }
}

@Component
@Command(name = "show", mixinStandardHelpOptions = true, description = Array("Show history."))
class ShowHistory
(
  commandInvocationRepository: CommandInvocationRepository
) extends HistorySubCommand with Callable[Integer] {
  @Transactional(readOnly = true)
  override def call(): Integer = {
    commandInvocationRepository.findAll().asScala.zipWithIndex.foreach{case (ci, idx) =>
      println(f"${idx + 1}%5d ${ci.command} ${ci.args.asScala.map(ci => ci.arg).mkString(" ")}")
    }
    return 0
  }
}
