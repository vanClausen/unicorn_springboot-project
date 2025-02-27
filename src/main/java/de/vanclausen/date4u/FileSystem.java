package de.vanclausen.date4u;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileSystem {

    private final Path root = Path.of(System.getProperty("user.home")).resolve("date4u").resolve("fs");

    public FileSystem() {
        if (!Files.isDirectory(root)) {
            try {
                Files.createDirectories(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public long getFreeDiskSpace() {
        return root.toFile().getFreeSpace();
    }

    public byte[] load(String filename) {
        try {
            return Files.readAllBytes(root.resolve(filename));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    void store(String filename, byte[] data) {
        try {
            Files.write(root.resolve(filename), data);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
