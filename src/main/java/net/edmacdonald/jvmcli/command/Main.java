package net.edmacdonald.jvmcli.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.List;

@Component
@CommandLine.Command(name = "jvmcli", mixinStandardHelpOptions = true, description = "Sample JVM CLI program")
public class Main extends ParentCommand<Main.MainSubCommand> {

    @Autowired
    public Main(List<MainSubCommand> subCommands) {
        this.subCommands = subCommands;
    }

    public interface MainSubCommand {
    }
}
