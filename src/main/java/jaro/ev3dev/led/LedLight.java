package jaro.ev3dev.led;

public enum LedLight {
    LEFT,
    RIGHT,
    BOTH;

    int getBit() {
        return (this.ordinal() + 1);   // LEFT == 1, RIGHT == 2, BOTH == LEFT | RIGHT == 3
    }
}
