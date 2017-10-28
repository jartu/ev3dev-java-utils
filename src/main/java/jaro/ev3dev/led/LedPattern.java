package jaro.ev3dev.led;

public enum LedPattern {
    OFF(0),
    STATIC_GREEN(1),
    STATIC_RED(2),
    STATIC_YELLOW(3),
    BLINK_NORMAL_GREEN(4),
    BLINK_NORMAL_RED(5),
    BLINK_NORMAL_YELLOW(6),
    BLINK_FAST_GREEN(7),
    BLINK_FAST_RED(8),
    BLINK_FAST_YELLOW(9);

    private final int pattern;

    LedPattern(final int pattern) {
        this.pattern = pattern;
    }

    public int getPattern() {
        return pattern;
    }
}
