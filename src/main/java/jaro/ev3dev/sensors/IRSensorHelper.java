package jaro.ev3dev.sensors;

import ev3dev.sensors.SensorMode;
import ev3dev.sensors.ev3.EV3IRSensor;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class IRSensorHelper extends SensorHelper<IRSensorMode> {

    private static final Logger log = LoggerFactory.getLogger(IRSensorHelper.class);

    private static final IRSensorMode DEFAULT_MODE = IRSensorMode.DISTANCE;

    private static final Map<Port, IRSensorHelper> portHelperMap = new HashMap<>();

    private final EV3IRSensor irSensor;

    private IRSensorHelper(final Port port, final IRSensorMode mode) {
        this.irSensor = new EV3IRSensor(port);

        this.setMode(mode);
    }

    public static synchronized IRSensorHelper of(final Port port) {
        return of(port, DEFAULT_MODE);
    }

    public static synchronized IRSensorHelper of(final Port port, final IRSensorMode mode) {
        IRSensorHelper helper = portHelperMap.get(port);
        if (helper == null) {
            helper = new IRSensorHelper(port, mode);
            portHelperMap.put(port, helper);
        }
        return helper;
    }

    public synchronized float getDistanceValue() {
        return this.getSingleValueMeasurement(IRSensorMode.DISTANCE);
    }

    // TODO: add the SEEK mode measurement


    @Override
    protected SensorMode setSensorModeOnly(final IRSensorMode mode) {
        switch (mode) {
            case DISTANCE:
                return this.irSensor.getDistanceMode();
            case SEEK:
                return this.irSensor.getSeekMode();
            default:
                throw new IllegalArgumentException("Unrecognized mode: " + mode);
        }
    }

    public static void main(final String[] args) throws Exception {

        final IRSensorHelper ir = IRSensorHelper.of(SensorPort.S4);

        System.out.println("Mode: " + ir.getMode());
        System.out.println("Distance: " + ir.getDistanceValue());

//        for (int i = 0; i < 1000; i++) {
//
//            log.info("DISTANCE: {}", ir.getDistance());
//
//            Delay.msDelay(100L);
//        }

        log.info("END");
    }

}
