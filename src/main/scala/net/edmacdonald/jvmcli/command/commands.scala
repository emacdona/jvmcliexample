package net.edmacdonald.jvmcli.command

import net.edmacdonald.jvmcli.command.example.Filesys
import org.springframework.stereotype.Component
import picocli.CommandLine
import picocli.CommandLine.{Command, Parameters}

import java.util.concurrent.Callable
import scala.compiletime.uninitialized

@Component
@Command(name = "find", mixinStandardHelpOptions = true, description = Array("Find files."))
class Find extends Filesys.ExampleSubCommand with Callable[Integer] {
  private var directory: String = "."

  @Parameters(index = "0", description = Array("Starting directory."))
  def setDirectory(d: String): Unit = {
    directory = d
  }

  override def call(): Integer = return 0
}
