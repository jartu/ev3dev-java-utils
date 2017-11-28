package jaro.ev3dev.sensors;

import jaro.ev3dev.Validator;

public class ColorRGB {

    private final int red;
    private final int green;
    private final int blue;

    public ColorRGB(final int red, final int green, final int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public ColorRGB(final float[] sample) {
        Validator.assertParameter("at least three values", sample.length >= 3, Validator.TRUE_PREDICATE);
        this.red = (int)sample[0];
        this.green = (int)sample[1];
        this.blue = (int)sample[2];
    }

    public int getRed() {
        return red;
    }
    public int getGreen() {
        return green;
    }
    public int getBlue() {
        return blue;
    }

    @Override
    public String toString() {
        return String.format("#%02X%02X%02X", this.red, this.green, this.blue);
    }
}
