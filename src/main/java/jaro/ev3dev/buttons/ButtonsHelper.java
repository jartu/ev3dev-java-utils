package jaro.ev3dev.buttons;

import ev3dev.sensors.Button;
import jaro.ev3dev.led.LedBlinker;
import jaro.ev3dev.led.LedHelper;
import jaro.ev3dev.led.LedPattern;

public final class ButtonsHelper {

    // somehow the system doesn't notice the KeyPress while blinking
    @Deprecated
    public static void waitForLaunch() {
        final LedBlinker blinker = LedHelper.startBlinkerWithLimitIterations(
                new LedPattern[] {LedPattern.STATIC_YELLOW, LedPattern.OFF},
                new LedPattern[] {LedPattern.OFF, LedPattern.STATIC_YELLOW},
                500L, 200);
        try {
            // blocking wait for the press
            Button.ENTER.waitForPress();
        } finally {
            blinker.terminate();
            LedHelper.setAllOff();
        }
    }

    private ButtonsHelper() {
        // hiding default constructor
    }

}
