package net.edmacdonald.jvmcli.command

import net.edmacdonald.jvmcli.command.example.Example
import org.springframework.stereotype.Component
import picocli.CommandLine
import picocli.CommandLine.{Command, Parameters}

import java.util.concurrent.Callable
//import scala.annotation.meta.field
//import scala.beans.BeanProperty
import scala.compiletime.uninitialized

@Component
@Command(name = "find", mixinStandardHelpOptions = true, description = Array("Find files."))
class Find extends Example.ExampleSubCommand with Callable[Integer] {
  //@(Parameters @field)(index = "0", description = Array("Starting directory."))
  private var directory: String = "."

  @Parameters(index = "0", description = Array("Starting directory."))
  def setDirectory(d: String): Unit = {
    directory = d
  }

  override def call(): Integer = return 0
}
