package net.edmacdonald.jvmcli.command;

import picocli.CommandLine;

import java.util.List;

public abstract class ParentCommand<T> {
    protected List<T> subCommands;

    public final List<T> getSubCommands() {
        return subCommands;
    }

    public final CommandLine getCommandLine() {
        CommandLine commandLine = new CommandLine(this);

        for (T subCommand : getSubCommands()) {
            if (subCommand instanceof ParentCommand<?>) {
                commandLine.addSubcommand(((ParentCommand<?>) subCommand).getCommandLine());
            } else {
                commandLine.addSubcommand(subCommand);
            }
        }

        return commandLine;
    }
}
