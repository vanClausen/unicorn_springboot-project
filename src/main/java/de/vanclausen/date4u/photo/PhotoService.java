package de.vanclausen.date4u.photo;

import de.vanclausen.date4u.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UncheckedIOException;
import java.util.Optional;

@Service
public class PhotoService {
    private final FileSystem fileSystem;

    @Autowired
    public PhotoService(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    public Optional<byte[]> download(String name) {
        try {
            return Optional.of(fileSystem.load(name + ".jpg"));
        } catch (UncheckedIOException e) {
            return Optional.empty();
        }
    }
}
