package jaro.ev3dev.led;

import ev3dev.utils.Brickman;
import jaro.ev3dev.lcd.EyesImageType;
import jaro.ev3dev.lcd.ImageHelper;
import lejos.utility.Delay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LedHelperTest {

    private static final Logger log = LoggerFactory.getLogger("Main");

    public static void main(final String[] args) throws Exception {

//        LedHelper.setPattern(LedLight.LEFT, LedPattern.STATIC_GREEN);
//        LedHelper.setPattern(LedLight.RIGHT, LedPattern.STATIC_RED);
//        log.info("SET");
//
//        Delay.msDelay(10_000L);

        final LedBlinker blinker = LedHelper.startBlinkerWithLimitIterations(
                new LedPattern[] {LedPattern.STATIC_RED, LedPattern.OFF},
                new LedPattern[] {LedPattern.STATIC_GREEN, LedPattern.OFF},
                200L,
                30 * 2);
        log.info("Blinker started");

        blinker.join();

        log.info("EOF");
    }

}
