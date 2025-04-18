package de.vanclausen.date4u.core.photo;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service("QualityThumbnail")
public class AwtBicubicThumbnail implements Thumbnail {

    private static BufferedImage create(BufferedImage source, int width, int height) {
        double thumbRatio = (double) width / (double) height;
        double imgRatio = (double) source.getWidth() / (double) source.getHeight();
        if (thumbRatio < imgRatio) {
            height = (int) (width / imgRatio);
        } else {
            width = (int) (height * imgRatio);
        }
        BufferedImage thumb = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = thumb.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.drawImage(source, 0, 0, width, height, null);
        g2d.dispose();
        return thumb;
    }

    @Override
    public Future<byte[]> thumbnail(byte[] imageBytes) {
        try (InputStream is = new ByteArrayInputStream(imageBytes);
             ByteArrayOutputStream baos = new ByteArrayOutputStream() ) {
            ImageIO.write( create(ImageIO.read(is), 200, 200), "jpg", baos);
            return CompletableFuture.completedFuture(baos.toByteArray());
        } catch(IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}


