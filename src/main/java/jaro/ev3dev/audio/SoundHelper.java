package jaro.ev3dev.audio;

import ev3dev.actuators.Sound;
import ev3dev.utils.JarResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class SoundHelper {

    private static final Logger log = LoggerFactory.getLogger(SoundHelper.class);

    public static final int MIN_VOLUME = 0;
    public static final int MAX_VOLUME = 100;
    public static final int DEFAULT_VOLUME = 100;

    public static final boolean DEFAULT_DELETE_AFTERWARDS = true;
    public static final boolean DEFAULT_FORCE_EXPORT = false;

    private static final Sound SOUND = Sound.getInstance();
    static {
        SOUND.setVolume(DEFAULT_VOLUME);
    }

    public static void playBlocking(final String soundFileName) {
        playBlocking(soundFileName, DEFAULT_DELETE_AFTERWARDS, DEFAULT_FORCE_EXPORT);
    }

    public static void playBlocking(final String soundFileName, final boolean deleteAfterwards, final boolean forceExport) {

        // exporting the sound file to file system
        File soundFile = new File(soundFileName);
        if (forceExport || ! soundFile.exists()) {
            try {
                JarResource.export(soundFileName);
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
            log.debug("Sound file {} exported", soundFile);
        }

        // playing the file
        SOUND.playSample(soundFile);
        log.debug("Played {}", soundFile);

        // optionally removing the file afterwards
        if (deleteAfterwards) {
            if (! soundFile.delete()) {
                log.warn("Failed to delete the ound file {}", soundFile);
            } else {
                log.debug("Sound file {} deleted", soundFile);
            }
        }
    }
}
