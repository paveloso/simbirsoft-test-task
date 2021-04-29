package ru.simbirsoft.simbirsofttest.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileAppLogger implements AppLogger {

    private static FileAppLogger instance;

    private FileAppLogger() {}

    public static FileAppLogger getInstance() {
        if (instance == null) {
            instance = new FileAppLogger();
        }
        return instance;
    }

    public void log(String message) {
        File logFile = new File("app-parser.log");
        FileOutputStream outputStream = null;
        try {
            logFile.createNewFile();

            outputStream = new FileOutputStream(logFile, true);
            outputStream.write(message.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
