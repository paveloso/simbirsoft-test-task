package ru.simbirsoft.simbirsofttest.printer;

import ru.simbirsoft.simbirsofttest.HtmlPageContentParser;
import ru.simbirsoft.simbirsofttest.log.FileAppLogger;
import ru.simbirsoft.simbirsofttest.log.LogRunner;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DbPrinter implements ResultPrinter, Runnable {

    private HashMap<String, Integer> result;

    public DbPrinter(HashMap<String, Integer> result) {
        this.result = result;
    }

    @Override
    public void print(HashMap<String, Integer> result) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/baseschema","root","root");

            String query = "insert into parse_results (word, count) values (?, ?)";

            for (Map.Entry<String, Integer> entry : result.entrySet()) {
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, entry.getKey());
                preparedStatement.setInt(2, entry.getValue());
                preparedStatement.execute();
            }

            conn.close();
        } catch(Exception e) {
            if (HtmlPageContentParser.ARG_VALUE == HtmlPageContentParser.ARG_LOG_DB || HtmlPageContentParser.ARG_VALUE == HtmlPageContentParser.ARG_LOG_NODB) {
                Thread logThread = new Thread(new LogRunner(FileAppLogger.getInstance(), new Date() + " IOException " + e.getMessage() + " " + e.getStackTrace()[0] + "\n"));
                logThread.start();
            } else {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        print(result);
    }
}
