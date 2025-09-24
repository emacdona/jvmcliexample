package net.edmacdonald.jvmcli.command

import net.edmacdonald.jvmcli.command.example.Example
import org.springframework.stereotype.Component
import picocli.CommandLine

import java.util.concurrent.Callable

@Component
@CommandLine.Command(name = "find", mixinStandardHelpOptions = true, description = Array("Find files."))
class Find extends Example.ExampleSubCommand, Callable[Integer] {
  override def call(): Integer = 0
}
