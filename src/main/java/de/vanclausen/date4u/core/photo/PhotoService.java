package de.vanclausen.date4u.core.photo;

import de.vanclausen.date4u.core.FileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.UncheckedIOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class PhotoService {
    private final FileSystem fileSystem;
    private final Thumbnail thumbnail;
//    private final ApplicationEventPublisher publisher;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public PhotoService(FileSystem fileSystem, @Qualifier("QualityThumbnail") Thumbnail thumbnail/*, ApplicationEventPublisher publisher*/) {
        this.fileSystem = fileSystem;
        this.thumbnail = thumbnail;
//        this.publisher = publisher;
    }

    @Cacheable(cacheNames="date4u.filesystem.load",
            key="#name",
            unless="#result == null")
    public Optional<byte[]> download(String name, FileSystem.FileSystemPath fileSystemPath) {
        try {
            log.info("Load image {}", name);
            return Optional.of(fileSystem.load(name + ".jpg", fileSystemPath));
        } catch (UncheckedIOException e) {
            return Optional.empty();
        }
    }

    public String upload(byte[] imageBytes) {
        String imageName = UUID.randomUUID().toString();

//        NewPhotoEvent newPhotoEvent = new NewPhotoEvent(imageName, OffsetDateTime.now());
//        publisher.publishEvent(newPhotoEvent);

        // store original image
        fileSystem.store(imageName + ".jpg", imageBytes, FileSystem.FileSystemPath.IMAGES);

        // store thumbnail
        byte[] thumbnailBytes = thumbnail.thumbnail(imageBytes);
        fileSystem.store(imageName + "-thumb.jpg", thumbnailBytes, FileSystem.FileSystemPath.THUMBNAILS);

        return imageName;
    }
}
