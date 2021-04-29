package ru.simbirsoft.simbirsofttest.service;

import ru.simbirsoft.simbirsofttest.HtmlPageContentParser;
import ru.simbirsoft.simbirsofttest.log.FileAppLogger;
import ru.simbirsoft.simbirsofttest.log.LogRunner;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

public class ParserService {

    private static ParserService instance;

    private CollectorService collectorService = new CollectorService();

    private char[] separators = {' ', ',', '.', '!', '?','"', ';', ':', '[', ']', '(', ')', '\n', '\r', '\t'};
//    private char[] separators = {' ', ',', '.'};

    private String regexPattern = " |\\,|\\.|\\!|\\?|\\\"|\\;|\\:|\\[|\\]|\\(|\\)|\\<|\\>|\\/|\n|\r|\t";

    private ParserService() {}

    public static ParserService getInstance() {
        if (instance == null) {
            instance = new ParserService();
        }
        return instance;
    }

    public Map<String, Integer> parseAddress(String url) {
        try {
            URL targetUrl = new URL(url);

            HttpURLConnection connection = null;
            if (url.startsWith("https")) {
                connection = (HttpsURLConnection) targetUrl.openConnection();
            } else {
                connection = (HttpURLConnection) targetUrl.openConnection();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            String[] words;

            while ((line = reader.readLine()) != null) {
                words = line.split(regexPattern);
                collectorService.collectData(words);
            }

        } catch (MalformedURLException e) {
            if (HtmlPageContentParser.ARG_VALUE == HtmlPageContentParser.ARG_LOG_DB || HtmlPageContentParser.ARG_VALUE == HtmlPageContentParser.ARG_LOG_NODB) {
                Thread logThread = new Thread(new LogRunner(FileAppLogger.getInstance(), new Date() + " MalformedULException " + e.getMessage() + " " + e.getStackTrace()[0] + "\n"));
                logThread.start();
            } else {
                e.printStackTrace();
            }
        } catch (IOException e) {
            if (HtmlPageContentParser.ARG_VALUE == HtmlPageContentParser.ARG_LOG_DB || HtmlPageContentParser.ARG_VALUE == HtmlPageContentParser.ARG_LOG_NODB) {
                Thread logThread = new Thread(new LogRunner(FileAppLogger.getInstance(), new Date() + " IOException " + e.getMessage() + " " + e.getStackTrace()[0] + "\n"));
                logThread.start();
            } else {
                e.printStackTrace();
            }
        }

        return collectorService.getResult();

    }
}
