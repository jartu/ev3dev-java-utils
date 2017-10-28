package jaro.ev3dev.led;

public class LedBlinker extends Thread {
    private final LedPattern[] leftPatterns;
    private final LedPattern[] rightPatterns;
    private final long delayMillis;
    private final Long limitMillis;
    private final Integer limitIterations;
    private long startMillis;
    private boolean terminate;

    LedBlinker(final LedPattern[] leftPatterns, final LedPattern[] rightPatterns, final long delayMillis,
               final Long limitMillis, final Integer limitIterations) {
        this.leftPatterns = leftPatterns;
        this.rightPatterns = rightPatterns;
        this.delayMillis = delayMillis;
        this.limitMillis = limitMillis;
        this.limitIterations = limitIterations;
    }

    @Override
    public void run() {
        this.startMillis = System.currentTimeMillis();

        int iterations = 0;
        while (! this.terminate &&
                (limitIterations == null || iterations < limitIterations) &&
                (limitMillis == null || (System.currentTimeMillis() - startMillis) < limitMillis)) {

            if (this.leftPatterns != null && this.leftPatterns.length > 0) {
                LedHelper.setPattern(LedLight.LEFT, leftPatterns[iterations % this.leftPatterns.length]);
            }
            if (this.rightPatterns != null && this.rightPatterns.length > 0) {
                LedHelper.setPattern(LedLight.RIGHT, rightPatterns[iterations % this.rightPatterns.length]);
            }

            synchronized(this) {
                try {
                    this.wait(this.delayMillis);
                } catch (final InterruptedException e) {
                    e.printStackTrace();  // TODO: something else?
                }
            }

            iterations++;
        }
    }
}
