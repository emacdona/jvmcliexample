package net.edmacdonald.jvmcli.command.filesys;

import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Formatter;
import java.util.concurrent.Callable;

@Component
@CommandLine.Command(name = "ls", mixinStandardHelpOptions = true, description = "List files.")
public class Ls implements Filesys.FilesysSubCommand, Callable<Integer> {
    @CommandLine.Parameters(index = "0", description = "Directory whose contents you wish to list.")
    private String directory;

    @CommandLine.Option(names = "-l")
    private boolean longOutput = false;

    @Override
    public Integer call() throws Exception {
        for (File f : new File(directory).listFiles()) {
            if (!longOutput) {
                System.out.println(f);
            } else {
                var attributes = Files.readAttributes(f.toPath(), PosixFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
                var permissions = attributes.permissions();
                var permissionsString = new Formatter().format(
                        "%s%s%s%s%s%s%s%s%s",
                        permissions.contains(PosixFilePermission.OWNER_READ) ? "r" : "-",
                        permissions.contains(PosixFilePermission.OWNER_WRITE) ? "w" : "-",
                        permissions.contains(PosixFilePermission.OWNER_EXECUTE) ? "x" : "-",
                        permissions.contains(PosixFilePermission.GROUP_READ) ? "r" : "-",
                        permissions.contains(PosixFilePermission.GROUP_WRITE) ? "w" : "-",
                        permissions.contains(PosixFilePermission.GROUP_EXECUTE) ? "x" : "-",
                        permissions.contains(PosixFilePermission.OTHERS_READ) ? "r" : "-",
                        permissions.contains(PosixFilePermission.OTHERS_WRITE) ? "w" : "-",
                        permissions.contains(PosixFilePermission.OTHERS_EXECUTE) ? "x" : "-"
                );

                System.out.printf(
                        "%-12.9s %-13.10s %-13.10s %s%n",
                        permissionsString,
                        attributes.owner().getName(),
                        attributes.group().getName(),
                        f
                );
            }
        }
        return 0;
    }
}
