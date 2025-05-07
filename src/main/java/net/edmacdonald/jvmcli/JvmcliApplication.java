package net.edmacdonald.jvmcli;

import net.edmacdonald.jvmcli.runtimehints.LiquibaseRuntimeHints;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
@ImportRuntimeHints(LiquibaseRuntimeHints.class)
public class JvmcliApplication {
    public static void main(String[] args) {
        SpringApplication.run(JvmcliApplication.class, args);
    }
}
