package net.edmacdonald.jvmcli;

import net.edmacdonald.jvmcli.command.Main;
import net.edmacdonald.jvmcli.entity.CommandArgument;
import net.edmacdonald.jvmcli.entity.CommandInvocation;
import net.edmacdonald.jvmcli.impldetails.runtimehints.LiquibaseRuntimeHints;
import net.edmacdonald.jvmcli.repository.CommandInvocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@SpringBootApplication
@EnableAspectJAutoProxy
@ImportRuntimeHints(LiquibaseRuntimeHints.class)
public class JvmcliApplication {
    public static void main(String[] args) {
        SpringApplication.run(JvmcliApplication.class, args);
    }


    @Component
    public static class CliEntrypoint implements CommandLineRunner {
        private final Main main;
        private final CommandInvocationRepository commandInvocationRepository;
        private final String applicationName;

        @Autowired
        public CliEntrypoint(Main main, CommandInvocationRepository commandInvocationRepository, @Value("${spring.application.name}") String applicationName) {
            this.main = main;
            this.commandInvocationRepository = commandInvocationRepository;
            this.applicationName = applicationName;
        }

        @Override
        public void run(String... args) throws Exception {
            long order = 10;
            CommandInvocation commandInvocation = new CommandInvocation();
            commandInvocation.setCommand(applicationName);
            for (String arg : args) {
                CommandArgument commandArgument = new CommandArgument();
                commandArgument.setArg(arg);
                commandArgument.setArgumentOrder(BigInteger.valueOf(order));
                commandInvocation.addArg(commandArgument);
            }
            commandInvocationRepository.save(commandInvocation);
            System.exit(main.getCommandLine().execute(args));
        }
    }
}
