package jaro.ev3dev.led;

import ev3dev.actuators.ev3.EV3Led;
import lejos.hardware.LED;
import lejos.utility.Delay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public final class LedHelper {

    private static final Logger log = LoggerFactory.getLogger(LedHelper.class);

    private static final ThreadLocal<LED> LED_LEFT = new ThreadLocal<LED>() {
        @Override
        protected LED initialValue() {
            return new EV3Led(EV3Led.LEFT);
        }
    };
    private static final ThreadLocal<LED> LED_RIGHT = new ThreadLocal<LED>() {
        @Override
        protected LED initialValue() {
            return new EV3Led(EV3Led.RIGHT);
        }
    };

    public static void setAllOff() {
        setPattern(LedLight.BOTH, LedPattern.OFF);
    }

    public static void setPattern(final LedLight led, final LedPattern pattern) {
        if ((LedLight.LEFT.getBit() & led.getBit()) > 0) {
            LED_LEFT.get().setPattern(pattern.getPattern());
        }
        if ((LedLight.RIGHT.getBit() & led.getBit()) > 0) {
            LED_RIGHT.get().setPattern(pattern.getPattern());
        }
    }

    public static LedBlinker startBlinkerWithLimitIterations(final LedPattern[] leftPatterns, final LedPattern[] rightPatterns,
                                                             final long delayMillis, final int iterations) {
        final LedBlinker blinker = new LedBlinker(leftPatterns, rightPatterns, delayMillis, null, iterations);
        blinker.start();
        return blinker;
    }

    private LedHelper() {
        // hiding default constructor
    }
}
