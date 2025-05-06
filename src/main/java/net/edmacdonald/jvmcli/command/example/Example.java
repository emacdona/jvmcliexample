package net.edmacdonald.jvmcli.command.example;

import net.edmacdonald.jvmcli.command.Main;
import net.edmacdonald.jvmcli.command.ParentCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.List;

@Component
@CommandLine.Command(name = "example", mixinStandardHelpOptions = true, description = "Some example commands.")
public class Example extends ParentCommand<Example.ExampleSubCommand> implements Main.MainSubCommand {

    @Autowired
    public Example(List<ExampleSubCommand> subCommands) {
        this.subCommands = subCommands;
    }

    public interface ExampleSubCommand {
    }
}
