package jaro.ev3dev.audio;

import jaro.ev3dev.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoundHelperTest {

    private static final Logger log = LoggerFactory.getLogger("Main");

    public static void main(final String[] args) throws Exception {

        final String soundFile = "nod_low_power.wav";
        SoundHelper.playBlocking(soundFile);
        log.info("Played blocking.");

        final Thread thread = Utils.startNonBlocking(() -> SoundHelper.playBlocking(soundFile));
        log.info("Played non-blocking.");
        thread.join();

        log.info("EOF");

    }

}
