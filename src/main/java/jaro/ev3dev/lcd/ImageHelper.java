package jaro.ev3dev.lcd;

import ev3dev.actuators.LCD;
import ev3dev.utils.JarResource;
import lejos.hardware.lcd.GraphicsLCD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public final class ImageHelper {

    private static final Logger log = LoggerFactory.getLogger(ImageHelper.class);

    public static final boolean DEFAULT_DELETE_AFTERWARDS = true;
    public static final boolean DEFAULT_FORCE_EXPORT = false;

    private static final GraphicsLCD lcd = LCD.getInstance();  // TODO: check that it's thread-safe

    public static void clear() {
        lcd.clear();
    }

    public static void showEyes(final EyesImageType eyesType) throws IOException {
        showImage(eyesType.getFileName(), 0, 0, DEFAULT_DELETE_AFTERWARDS, DEFAULT_FORCE_EXPORT);
    }

    public static void showEyes(final EyesImageType eyesType,
                                final boolean deleteAfterwards, final boolean forceExport) throws IOException {
        showImage(eyesType.getFileName(), 0, 0, deleteAfterwards, forceExport);
    }

    public static void showImage(final String imageFileName,
                                 final int x, final int y) throws IOException {
        showImage(imageFileName, x, y, DEFAULT_DELETE_AFTERWARDS, DEFAULT_FORCE_EXPORT);
    }

    public static void showImage(final String imageFileName,
                                 final int x, final int y,
                                 final boolean deleteAfterwards, final boolean forceExport) throws IOException {

        // exporting the image from resources to a file (if it doesn't exist or if the export is forced)
        final File imageFile = new File(imageFileName);
        if (forceExport || ! imageFile.exists()) {
            JarResource.export(imageFileName);
            log.debug("Image exported to {}", imageFile);
        }

        // loading the Image
        final Image image = ImageIO.read(imageFile);
        log.debug("Loaded image content from {}", imageFile);

        // drawing the image
        lcd.drawImage(image, x, y, 0);  // third parameter seems to be ignored
        lcd.refresh();

        // deleting the image if requested
        if (deleteAfterwards) {
            if (! imageFile.delete()) {
                log.warn("Failed to delete the image file {}", imageFile);
            } else {
                log.debug("Deleted the image file {}", imageFile);
            }
        }
    }

    private ImageHelper() {
        // hiding default constructor
    }
}
