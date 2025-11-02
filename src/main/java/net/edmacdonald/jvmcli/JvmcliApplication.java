package net.edmacdonald.jvmcli;

import net.edmacdonald.jvmcli.command.Main;
import net.edmacdonald.jvmcli.impldetails.runtimehints.LiquibaseRuntimeHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ImportRuntimeHints(LiquibaseRuntimeHints.class)
public class JvmcliApplication {
    public static void main(String[] args) {
        System.exit(run(args));
    }

    /**
     * Starts the Spring context, runs the CLI, returns the exit code without exiting the JVM.
     */
    public static int run(String... args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(JvmcliApplication.class, args);
        try {
            return SpringApplication.exit(ctx);
        } finally {
            ctx.close();
        }
    }

    @Component
    public static class CliEntrypoint implements CommandLineRunner, ExitCodeGenerator {
        private final Main main;
        private volatile int exitCode = 0;

        @Autowired
        public CliEntrypoint(Main main) {
            this.main = main;
        }

        @Override
        public void run(String... args) {
            this.exitCode = main.getCommandLine().execute(args);
        }

        @Override
        public int getExitCode() {
            return exitCode;
        }
    }
}
