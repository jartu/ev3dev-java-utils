package jaro.ev3dev.audio;

import ev3dev.robotics.tts.Espeak;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.BiConsumer;

public final class TTSHelper {

    private static final Logger log = LoggerFactory.getLogger(TTSHelper.class);

    public static final int MIN_VOLUME = 0;
    public static final int MAX_VOLUME = 200;
    public static final int DEFAULT_VOLUME = 100;

    public static final int MIN_PITCH = 0;
    public static final int MAX_PITCH = 99;
    public static final int DEFAULT_PITCH = 50;

    public static final int MIN_SPEED = 80;
    public static final int MAX_SPEED = 450;
    public static final int DEFAULT_SPEED = 175;

    private static final Espeak TTS = new Espeak();
    static {
        TTS.setVoice(Espeak.VOICE_ENGLISH);
        TTS.setVolume(DEFAULT_VOLUME);
        TTS.setPitch(DEFAULT_PITCH);
        TTS.setSpeedReading(DEFAULT_SPEED);
    };

    public static void setVolume(final int volume) {
        set((tts, value) -> tts.setVolume(value), "volume", volume, MIN_VOLUME, MAX_VOLUME);
    }
    public static void setPitch(final int pitch) {
        set((tts, value) -> tts.setPitch(value), "pitch", pitch, MIN_PITCH, MAX_PITCH);
    }
    public static void setSpeed(final int speed) {
        set((tts, value) -> tts.setSpeedReading(speed), "speed", speed, MIN_SPEED, MAX_SPEED);
    }
    public static void setVoice(final TTSVoiceType voiceType) {
        TTS.setVoice(voiceType.getVoiceName());
    }

    public static synchronized void sayBlocking(final String message) {
        TTS.setMessage(message);
        TTS.say();
    }

    public static void sayNonBlocking(final String message) {
        new Thread(() -> {
            sayBlocking(message);
        }).start();
    }

    private static void set(final BiConsumer<Espeak, Integer> setter, final String valueName, final int value,
                            final int minValue, final int maxValue) {
        if (value < minValue || value > maxValue) {
            throw new IllegalArgumentException(String.format("Invalid %s value (%d), use value from interval [%d, %d]",
                    valueName, value, minValue, maxValue));
        }
        setter.accept(TTS, value);
    }

    private TTSHelper() {
        // hiding default constructor
    }
}
