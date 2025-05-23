package net.edmacdonald.jvmcli;

import net.edmacdonald.jvmcli.command.Main;
import net.edmacdonald.jvmcli.impldetails.runtimehints.LiquibaseRuntimeHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ImportRuntimeHints(LiquibaseRuntimeHints.class)
public class JvmcliApplication {
    public static void main(String[] args) {
        SpringApplication.run(JvmcliApplication.class, args);
    }


    @Component
    public static class CliEntrypoint implements CommandLineRunner {
        private final Main main;

        @Autowired
        public CliEntrypoint(Main main) {
            this.main = main;
        }

        @Override
        public void run(String... args) throws Exception {
            System.exit(main.getCommandLine().execute(args));
        }
    }
}
