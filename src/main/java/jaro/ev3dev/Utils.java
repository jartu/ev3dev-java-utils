package jaro.ev3dev;

public final class Utils {

    public static Thread startNonBlocking(final Runnable block, final Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        final Thread thread = new Thread(() -> block.run());
        if (uncaughtExceptionHandler != null) {
            thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
        thread.start();
        return thread;
    }

    public static Thread startNonBlocking(final Runnable block, final MutableObject<Throwable> uncaughtExceptionHolder) {
        return startNonBlocking(block, (t, e) -> uncaughtExceptionHolder.setValue(e));
    }

    public static Thread startNonBlocking(final Runnable block) {
        return startNonBlocking(block, (Thread.UncaughtExceptionHandler)null);
    }

    private Utils() {
        // hiding default constructor
    }
}
