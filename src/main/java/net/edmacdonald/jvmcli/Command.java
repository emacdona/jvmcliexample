package net.edmacdonald.jvmcli;

import net.edmacdonald.jvmcli.command.Main;
import net.edmacdonald.jvmcli.command.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Command implements CommandLineRunner {
    private final Main main;
    private final Test test;

    @Autowired
    public Command(Main main, Test test) {
        this.main = main;
        this.test = test;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("It worked? %s".formatted(test.getString()));
        System.exit(main.getCommandLine().execute(args));
    }
}
