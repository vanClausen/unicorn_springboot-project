package de.vanclausen.date4u.interfaces.shell;

import de.vanclausen.date4u.core.FileSystem;
import de.vanclausen.date4u.core.configuration.Date4uProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

import java.nio.file.Path;

@ShellComponent
public class FsCommands {

    private final FileSystem fileSystem;
    private final Environment env;

//    @Value("${date4u.filesystem.minimum-free-disk-space:1_000_000}")
//    private long minFreeDiskSpace;
    private final Date4uProperties date4uProperties;

    @Autowired
    public FsCommands(FileSystem fs, Environment env, Date4uProperties date4uProperties) {
        this.fileSystem = fs;
        this.env = env;
        this.date4uProperties = date4uProperties;
    }

    @ShellMethod("Display minimum required diskspace")
    public long minimumFreeDiskSpace() {
        return date4uProperties.getFilesystem().getMinimumFreeDiskSpace();
    }

    @ShellMethod("Display free disk space")
    public String freeDiskSpace() {
        return DataSize.ofBytes(fileSystem.getFreeDiskSpace()).toGigabytes() + " GB";
    }

    @ShellMethod("Print user home directory")
    public String userHome() {
        return env.getProperty("user.home");
    }

    @ShellMethod("Display if a path exists")
    public boolean exists (Path path) {
        System.out.println(path);
        return true;
    }
}

@Component
class StringToPathConverter implements Converter<String, Path> {

    @Override
    public Path convert(String source) {
        return Path.of(source);
    }
}
