package jaro.ev3dev.buttons;

import ev3dev.sensors.Button;
import ev3dev.utils.Brickman;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ButtonHelperTest {

    private static final Logger log = LoggerFactory.getLogger("Main");

    public static void main(final String[] args) throws Exception {

        Brickman.disable();
        log.info("Brickman disabled");

        for (int i = 0; i < 10; i++) {
            log.info("Entering wait ...");
            ButtonsHelper.waitForLaunch();
            log.info("Wait ended");
        }

//        log.info("Entering wait ...");
//        Button.ENTER.waitForPress();
//        log.info("Wait ended");

        log.info("EOF");
    }

}
