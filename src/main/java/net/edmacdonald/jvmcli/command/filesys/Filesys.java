package net.edmacdonald.jvmcli.command.filesys;

import net.edmacdonald.jvmcli.command.Main;
import net.edmacdonald.jvmcli.command.ParentCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.List;

@Component
@CommandLine.Command(name = "filesys", mixinStandardHelpOptions = true, description = "Commands related to the filesystem.")
public class Filesys extends ParentCommand<Filesys.FilesysSubCommand> implements Main.MainSubCommand {

    @Autowired
    public Filesys(List<FilesysSubCommand> subCommands) {
        this.subCommands = subCommands;
    }

    public interface FilesysSubCommand {
    }
}
