package de.vanclausen.date4u.core.photo;

import de.vanclausen.date4u.core.FileSystem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.util.Base64;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest ( {"spring.shell.interactive.enabled=false"})
public class PhotoServiceTest {
    private static final byte[] MINIMAL_JPG = Base64
            .getDecoder()
            .decode(
            "/9j/4AAQSkZJRgABAQEASABIAAD"
                    + "/2wBDAP////////////////////////////////////////////////////////////"
                    + "//////////////////////////wgALCAABAAEBAREA/8QAFBABAAAAAAAAAAAAAAAAA"
                    + "AAAAP/aAAgBAQABPxA="
            );

//    @Mock FileSystem fileSystem;
//    @Spy AwtBicubicThumbnail thumbnail;
//    @InjectMocks PhotoService photoService;
    @MockitoBean FileSystem fileSystem;
    @MockitoSpyBean AwtBicubicThumbnail thumbnail;
    @Autowired PhotoService photoService;

    @Test
    void successful_photo_upload() {
        String imageName = photoService.upload(MINIMAL_JPG);
        assertThat(imageName).isNotEmpty();

        verify(fileSystem, times(2)).store(anyString(), any(byte[].class), any(FileSystem.FileSystemPath.class));

        verify(thumbnail, times(1)).thumbnail(any(byte[].class));
    }
}
