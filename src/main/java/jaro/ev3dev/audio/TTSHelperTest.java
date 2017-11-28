package jaro.ev3dev.audio;

import jaro.ev3dev.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TTSHelperTest {

    private static final Logger log = LoggerFactory.getLogger("Main");

    public static void main(final String[] args) throws Exception {

        TTSHelper.sayBlocking("First sentence. What a happy day! I feel like today is going to be great!");
        log.info("Blocking part done");

        Utils.startNonBlocking(() -> TTSHelper.sayBlocking("Second  sentence. What a happy day! I feel like today is going to be great!"));
        log.info("Non-blocking part sent");

    }

}
