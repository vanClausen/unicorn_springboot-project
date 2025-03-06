package de.vanclausen.date4u.shell;

import de.vanclausen.date4u.FileSystem;
import de.vanclausen.date4u.photo.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@ShellComponent
public class PhotoCommands {
    private final PhotoService photoService;

    @Autowired
    public PhotoCommands(PhotoService photoService) {
        this.photoService = photoService;
    }

    @ShellMethod("Show photo")
    public String showPhoto(
            String name,
            @ShellOption(
                    defaultValue="root",
                    help="Filesystem directory (default: root, options: root, images, thumbnails)"
            ) String directory
    ) {
        FileSystem.FileSystemPath fileSystemPath = Arrays.stream(FileSystem.FileSystemPath.values())
                .filter(fsDir -> fsDir.name().equalsIgnoreCase(directory))
                .findAny()
                .orElse(null);
        if (fileSystemPath == null) {
            return "Invalid directory";
        }
        return photoService.download(name, fileSystemPath)
                .map(bytes -> {
                    try {
                        BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
                        return String.format("title: %s, width: %d, height: %d", name, image.getWidth(), image.getHeight());
                    } catch (IOException e) {
                        return "Failed to open photo";
                    }
                })
                .orElse("Photo not found");
    }

    @ShellMethod("Upload photo")
    public String uploadPhoto(String filename) throws IOException {
        byte[] imageBytes = Files.readAllBytes(Path.of(filename));
        return "Uploaded " + photoService.upload(imageBytes);
    }
}
