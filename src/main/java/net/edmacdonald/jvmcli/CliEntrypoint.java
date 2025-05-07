package net.edmacdonald.jvmcli;

import net.edmacdonald.jvmcli.command.Main;
import net.edmacdonald.jvmcli.command.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class CliEntrypoint implements CommandLineRunner {
    private final Main main;
    private final Test test;

    @Autowired
    public CliEntrypoint(Main main, Test test) {
        this.main = main;
        this.test = test;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Invocation:\n%s".formatted(
                Arrays.stream(args).collect(Collectors.joining("\n\t", "\t", "\n")))
        );
        System.exit(main.getCommandLine().execute(args));
    }
}
