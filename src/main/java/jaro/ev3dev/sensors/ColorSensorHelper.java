package jaro.ev3dev.sensors;

import ev3dev.sensors.SensorMode;
import ev3dev.sensors.ev3.EV3ColorSensor;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ColorSensorHelper extends SensorHelper<ColorSensorMode> {

    private static final Logger log = LoggerFactory.getLogger(ColorSensorHelper.class);

    private static final ColorSensorMode DEFAULT_MODE = ColorSensorMode.AMBIENT;

    private static final Map<Port, ColorSensorHelper> portHelperMap = new HashMap<>();

    private final EV3ColorSensor colorSensor;
    private float[] sample;
    private ColorSensorMode mode;
    private SensorMode sensorMode;

    private ColorSensorHelper(final Port port, final ColorSensorMode mode) {
        this.colorSensor = new EV3ColorSensor(port);

        this.setMode(mode);
    }

    public static synchronized ColorSensorHelper of(final Port port) {
        return of(port, DEFAULT_MODE);
    }

    public static synchronized ColorSensorHelper of(final Port port, final ColorSensorMode mode) {
        ColorSensorHelper helper = portHelperMap.get(port);
        if (helper == null) {
            helper = new ColorSensorHelper(port, mode);
            portHelperMap.put(port, helper);
        }
        return helper;
    }

    /**
     * TODO: Return an int value?
     */
    public synchronized float getAmbientValue() {
        return this.getSingleValueMeasurement(ColorSensorMode.AMBIENT);
    }

    /**
     * TODO: Return an int value?
     */
    public synchronized float getColorIDValue() {
        return this.getSingleValueMeasurement(ColorSensorMode.COLOR_ID);
    }

    /**
     * TODO: Return an int value?
     */
    public synchronized float getRedValue() {
        return this.getSingleValueMeasurement(ColorSensorMode.RED);
    }

    public synchronized ColorRGB getRGBValue() {
        return new ColorRGB(this.getMeasurement(ColorSensorMode.RGB));
    }

    @Override
    protected SensorMode setSensorModeOnly(final ColorSensorMode mode) {
        switch (mode) {
            case AMBIENT:
                return this.colorSensor.getAmbientMode();
            case COLOR_ID:
                return this.colorSensor.getColorIDMode();
            case RED:
                return this.colorSensor.getRedMode();
            case RGB:
                return this.colorSensor.getRGBMode();
            default:
                throw new IllegalArgumentException("Unrecognized mode: " + mode);
        }
    }

    public static void main(final String[] args) throws Exception {

        final ColorSensorHelper color = ColorSensorHelper.of(SensorPort.S4);

        for (int i = 0; i < 1000; i++) {

            log.info("AMBIENT: {}", color.getAmbientValue());

            Delay.msDelay(100L);
        }

        log.info("END");
    }

}
