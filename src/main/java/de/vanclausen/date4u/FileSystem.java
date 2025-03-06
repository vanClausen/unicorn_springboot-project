package de.vanclausen.date4u;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class FileSystem {

    public enum FileSystemPath {
        ROOT,
        IMAGES,
        THUMBNAILS;
    }

    private final Path root = Path.of(System.getProperty("user.home")).resolve("date4u").resolve("fs");
    private final Path images = root.resolve("images");
    private final Path thumbnails = root.resolve("thumbnails");
    private final Map<FileSystemPath, Supplier<Path>> pathSupplier = new HashMap<>();

    public FileSystem() {
        initializeFileSystem();
        pathSupplier.put(FileSystemPath.ROOT, () -> root);
        pathSupplier.put(FileSystemPath.IMAGES, () -> images);
        pathSupplier.put(FileSystemPath.THUMBNAILS, () -> thumbnails);
    }

    private void initializeFileSystem() {
        initializeDirectories(root);
        initializeDirectories(images);
        initializeDirectories(thumbnails);
    }

    private void initializeDirectories(Path path) {
        if (!Files.isDirectory(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public long getFreeDiskSpace() {
        return root.toFile().getFreeSpace();
    }

    public byte[] load(String filename) {
        return load(filename, FileSystemPath.ROOT);
    }

    public byte[] load(String filename, FileSystemPath fileSystemPath) {
        Path path = pathSupplier.getOrDefault(fileSystemPath, () -> root).get();
        try {
            return Files.readAllBytes(path.resolve(filename));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void store(String filename, byte[] data) {
        store(filename, data, FileSystemPath.ROOT);
    }

    public void store(String filename, byte[] data, FileSystemPath fileSystemPath) {
        Path path = pathSupplier.getOrDefault(fileSystemPath, () -> root).get();
        try {
            Files.write(path.resolve(filename), data);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
