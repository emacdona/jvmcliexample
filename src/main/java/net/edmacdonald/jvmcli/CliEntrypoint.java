package net.edmacdonald.jvmcli;

import net.edmacdonald.jvmcli.command.Main;
import net.edmacdonald.jvmcli.entity.CommandArgument;
import net.edmacdonald.jvmcli.entity.CommandInvocation;
import net.edmacdonald.jvmcli.repository.CommandInvocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class CliEntrypoint implements CommandLineRunner {
    private final Main main;
    private final CommandInvocationRepository commandInvocationRepository;

    @Autowired
    public CliEntrypoint(Main main, CommandInvocationRepository commandInvocationRepository) {
        this.main = main;
        this.commandInvocationRepository = commandInvocationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long order = 0;
        CommandInvocation commandInvocation = new CommandInvocation();
        commandInvocation.setCommand("jvmcli");
        commandInvocation.setArgs(
                Arrays.asList(args).stream()
                        .map(arg -> {
                            CommandArgument commandArgument = new CommandArgument();
                            commandArgument.setArg(arg);
                            commandArgument.setArgumentOrder(BigInteger.valueOf(order));

                            return commandArgument;
                        }).collect(Collectors.toUnmodifiableList())
        );
        commandInvocationRepository.save(commandInvocation);
        System.exit(main.getCommandLine().execute(args));
    }
}
