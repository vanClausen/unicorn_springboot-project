package de.vanclausen.date4u.shell;

import de.vanclausen.date4u.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.unit.DataSize;

@ShellComponent
public class FsCommands {

    private final FileSystem fileSystem;

    @Autowired
    public FsCommands(FileSystem fs) {
        this.fileSystem = fs;
    }

    @ShellMethod("Display minimum required diskspace")
    public long minimumFreeDiskSpace() {
        return 1_000_000_000;
    }

    @ShellMethod("Display free disk space")
    public String freeDiskSpace() {
        return DataSize.ofBytes(fileSystem.getFreeDiskSpace()).toGigabytes() + " GB";
    }
}
