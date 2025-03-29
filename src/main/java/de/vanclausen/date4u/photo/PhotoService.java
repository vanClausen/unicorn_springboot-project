package de.vanclausen.date4u.photo;

import de.vanclausen.date4u.FileSystem;
import de.vanclausen.date4u.event.NewPhotoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.UncheckedIOException;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PhotoService {
    private final FileSystem fileSystem;
    private final Thumbnail thumbnail;
//    private final ApplicationEventPublisher publisher;

    @Autowired
    public PhotoService(FileSystem fileSystem, @Qualifier("QualityThumbnail") Thumbnail thumbnail/*, ApplicationEventPublisher publisher*/) {
        this.fileSystem = fileSystem;
        this.thumbnail = thumbnail;
//        this.publisher = publisher;
    }

    public Optional<byte[]> download(String name) {
        return download(name, FileSystem.FileSystemPath.ROOT);
    }

    public Optional<byte[]> download(String name, FileSystem.FileSystemPath fileSystemPath) {
        try {
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
