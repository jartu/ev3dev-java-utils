package jaro.ev3dev.lcd;

public enum EyesImageType {
    ANGRY("Angry.png"),
    AWAKE("Awake.png"),
    BLACK_EYE("BlackEye.png"),
    BOTTOM_LEFT("BottomLeft.png"),
    BOTTOM_RIGHT("BottomRight.png"),
    CRAZY_1("Crazy1.png"),
    CRAZY_2("Crazy2.png"),
    DISAPPOINTED("Disappointed.png"),
    DIZZY("Dizzy.png"),
    DOWN("Down.png"),
    EVIL("Evil.png"),
    HURT("Hurt.png"),
    KNOCKED_OUT("KnockedOut.png"),
    LOVE("Love.png"),
    MIDDLE_LEFT("MiddleLeft.png"),
    MIDDLE_RIGHT("MiddleRight.png"),
    NEUTRAL("Neutral.png"),
    NUCLEAR("Nuclear.png"),
    PINCH_LEFT("PinchLeft.png"),
    PINCH_MIDDLE("PinchMiddle.png"),
    PINCH_RIGHT("PinchRight.png"),
    TEAR("Tear.png"),
    TIRED_LEFT("TiredLeft.png"),
    TIRED_MIDDLE("TiredMiddle.png"),
    TIRED_RIGHT("TiredRight.png"),
    TOXIC("Toxic.png"),
    UP("Up.png"),
    WINKING("Winking.png");

    private final String fileName;

    EyesImageType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }
}
