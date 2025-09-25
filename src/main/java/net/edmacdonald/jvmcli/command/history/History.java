package net.edmacdonald.jvmcli.command.history;

import net.edmacdonald.jvmcli.command.Main;
import net.edmacdonald.jvmcli.command.ParentCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.List;

@Component
@CommandLine.Command(name = "history", mixinStandardHelpOptions = true, description = "Commands related to command history.")
public class History extends ParentCommand<History.HistorySubCommand> implements Main.MainSubCommand {

    @Autowired
    public History(List<History.HistorySubCommand> subCommands) {
        this.subCommands = subCommands;
    }

    public interface HistorySubCommand {
    }
}
