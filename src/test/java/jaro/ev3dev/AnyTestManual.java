package jaro.ev3dev;

import java.util.Random;

public class AnyTestManual {

    public static void main(final String[] args) throws Exception {

        final Thread thread = new Thread(() -> {
            final boolean throwExc = new Random(System.currentTimeMillis()).nextBoolean();
            if (throwExc) {
                throw new IllegalStateException("TEST");
            }
        });

        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.format("Exception from %s: %s%n", t, e);
        });

        thread.start();
        System.out.println("Started");

        thread.join();

        System.out.println("EOF");
    }
}
