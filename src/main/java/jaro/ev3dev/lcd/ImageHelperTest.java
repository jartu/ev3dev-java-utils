package jaro.ev3dev.lcd;

import ev3dev.utils.Brickman;
import lejos.utility.Delay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageHelperTest {

    private static final Logger log = LoggerFactory.getLogger("Main");

    public static void main(final String[] args) throws Exception {

        // disabling the Brickman
        Brickman.disable();
        log.info("Brickman disabled");

        // clearing the screen
        ImageHelper.clear();
        log.info("Screen cleared");
        Delay.msDelay(5_000L);

        // showing the image
        for (int i = 0; i < 10; i++) {
            ImageHelper.showEyes(EyesImageType.CRAZY_1, i == 9, false);
            ImageHelper.showEyes(EyesImageType.CRAZY_2, false, false);
        }

//        Delay.msDelay(10_000L);

        log.debug("EOF");

    }

}
