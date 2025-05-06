package net.edmacdonald.jvmcli;

import net.edmacdonald.jvmcli.command.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Command implements CommandLineRunner {
    private final Main main;

    @Autowired
    public Command(Main main) {
        this.main = main;
    }

    @Override
    public void run(String... args) throws Exception {
        System.exit(main.getCommandLine().execute(args));
    }
}
