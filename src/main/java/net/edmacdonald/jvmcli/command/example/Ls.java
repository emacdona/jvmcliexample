package net.edmacdonald.jvmcli.command.example;

import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

@Component
@CommandLine.Command(name = "ls", mixinStandardHelpOptions = true, description = "List files.")
public class Ls implements Example.ExampleSubCommand, Callable<Integer> {
    @CommandLine.Parameters(index = "0", description = "Directory whose contents you wish to list.")
    private String directory;

    @Override
    public Integer call() throws Exception {
        Stream.of(Objects.requireNonNull(new File(directory).listFiles()))
                .forEach(System.out::println);
        return 0;
    }
}
