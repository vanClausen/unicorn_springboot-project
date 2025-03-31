package de.vanclausen.date4u.core;

import org.junit.jupiter.api.Test;

import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class FileSystemTest {
    @Test
    void free_disk_space_has_to_be_positive() {
        FileSystem fileSystem = new FileSystem();

        long freeDiskSpace = fileSystem.getFreeDiskSpace();

        assertThat(freeDiskSpace).isGreaterThan(0);
    }

    @Test
    void store_and_load_successful() {
        FileSystem fileSystem = new FileSystem();
        fileSystem.store("test.txt", "Hello World".getBytes());
        byte[] data = fileSystem.load("test.txt");
        assertThat(data).isNotNull();
        assertThat(data).containsExactly("Hello World".getBytes());
    }

    @Test
    void load_unknown_files_throws_exception() {
        FileSystem fileSystem = new FileSystem();
        assertThatThrownBy(() ->
                fileSystem.load(UUID.randomUUID().toString()))
                .isInstanceOf(UncheckedIOException.class);
    }

    @Test
    void store_files_in_correct_directory() {
        FileSystem filesystem = new FileSystem();
        byte[] rootData = "Root Directory".getBytes();
        byte[] imagesData = "Images Directory".getBytes();
        byte[] thumbnailsData = "Thumbnails Directory".getBytes();

        filesystem.store("root.txt", rootData);
        byte[] rootDataLoaded = filesystem.load("root.txt", FileSystem.FileSystemPath.ROOT);
        assertThat(rootDataLoaded).isEqualTo(rootData);
        assertThatThrownBy(() ->
                filesystem.load("root.txt", FileSystem.FileSystemPath.IMAGES))
                .isInstanceOf(UncheckedIOException.class);
        assertThatThrownBy(() ->
                filesystem.load("root.txt", FileSystem.FileSystemPath.THUMBNAILS))
                .isInstanceOf(UncheckedIOException.class);
        filesystem.store("images.txt", imagesData, FileSystem.FileSystemPath.IMAGES);
        byte[] imagesDataLoaded = filesystem.load("images.txt", FileSystem.FileSystemPath.IMAGES);
        assertThat(imagesDataLoaded).isEqualTo(imagesData);
        assertThatThrownBy(() ->
                filesystem.load("images.txt", FileSystem.FileSystemPath.ROOT))
                .isInstanceOf(UncheckedIOException.class);
        assertThatThrownBy(() ->
                filesystem.load("images.txt", FileSystem.FileSystemPath.THUMBNAILS))
                .isInstanceOf(UncheckedIOException.class);
        filesystem.store("thumbnails.txt", thumbnailsData, FileSystem.FileSystemPath.THUMBNAILS);
        byte[] thumbnailsDataLoaded = filesystem.load("thumbnails.txt", FileSystem.FileSystemPath.THUMBNAILS);
        assertThat(thumbnailsDataLoaded).isEqualTo(thumbnailsData);
        assertThatThrownBy(() ->
                filesystem.load("thumbnails.txt", FileSystem.FileSystemPath.ROOT))
                .isInstanceOf(UncheckedIOException.class);
        assertThatThrownBy(() ->
                filesystem.load("thumbnails.txt", FileSystem.FileSystemPath.IMAGES))
                .isInstanceOf(UncheckedIOException.class);
    }

    @Test
    void load_arbitrary_file_throws_security_exception() {
        FileSystem fileSystem = new FileSystem();
        String arbitraryPath = "../../../../../../../../../../../Windows/notepad.exe";
        assertThatThrownBy( () -> fileSystem.load(arbitraryPath))
                .isInstanceOf(SecurityException.class)
                .hasMessageStartingWith("Access denied to ");
    }

    @Test
    void store_arbitrary_file_throws_security_exception() {
        FileSystem fileSystem = new FileSystem();
        String arbitraryPath = "../../../../../../../../../../../Windows/notepad.exe";
        assertThatThrownBy( () -> fileSystem.store(arbitraryPath, "Hello World".getBytes()))
                .isInstanceOf(SecurityException.class)
                .hasMessageStartingWith("Access denied to ");
    }
}