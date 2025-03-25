package de.vanclausen.date4u.shell;

import de.vanclausen.date4u.FileSystem;
import de.vanclausen.date4u.configuration.Date4uProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.unit.DataSize;

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
}
