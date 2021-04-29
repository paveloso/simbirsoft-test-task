package ru.simbirsoft.simbirsofttest.log;

public class LogRunner implements Runnable {

    private AppLogger appLogger;
    private String message;

    public LogRunner(AppLogger appLogger, String message) {
        this.appLogger = appLogger;
        this.message = message;
    }

    public void run() {
        appLogger.log(message);
    }
}
