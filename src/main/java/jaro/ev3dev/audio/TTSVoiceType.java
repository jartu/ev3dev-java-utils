package jaro.ev3dev.audio;

public enum TTSVoiceType {
    CZECH("czech"),
    GERMAN("german"),
    ENGLISH("english"),
    FRENCH("french"),
    LATIN("latin");

    private final String voiceName;

    TTSVoiceType(final String voiceName) {
        this.voiceName = voiceName;
    }

    public String getVoiceName() {
        return voiceName;
    }
}
