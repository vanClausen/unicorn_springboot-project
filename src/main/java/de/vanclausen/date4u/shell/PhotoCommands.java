package de.vanclausen.date4u.shell;

import de.vanclausen.date4u.photo.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@ShellComponent
public class PhotoCommands {
    private final PhotoService photoService;

    @Autowired
    public PhotoCommands(PhotoService photoService) {
        this.photoService = photoService;
    }

    @ShellMethod("Show photo")
    public String showPhoto(String name) {
        return photoService.download(name)
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
}
