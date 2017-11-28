package jaro.ev3dev.sensors;

import ev3dev.sensors.SensorMode;
import jaro.ev3dev.Validator;

/**
 * TODO: Per-Port synchronization only (currently synchronized over everything).
 */
abstract class SensorHelper<E extends Enum> {

    private E mode;
    private SensorMode sensorMode;
    private float[] sample;
    private int sampleSize;

    public E getMode() {
        return this.mode;
    }

    protected abstract SensorMode setSensorModeOnly(final E mode);

    public synchronized void setMode(final E mode) {
        this.mode = mode;
        this.sensorMode = this.setSensorModeOnly(mode);
        this.sampleSize = this.sensorMode.sampleSize();
        if (this.sample == null || this.sample.length < this.sampleSize) {
            this.sample = new float[this.sampleSize];
        }
    }

    protected float getSingleValueMeasurement(final E reqMode) {
        return this.getMeasurement(reqMode)[0];
    }

    protected float[] getMeasurement(final E reqMode) {
        Validator.assertState("in ambient mode", this.mode == reqMode, true);
        this.sensorMode.fetchSample(this.sample, 0);
        return this.sample;
    }
}
