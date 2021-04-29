package ru.simbirsoft.simbirsofttest;

import ru.simbirsoft.simbirsofttest.printer.ConsolePrinter;
import ru.simbirsoft.simbirsofttest.printer.DbPrinter;
import ru.simbirsoft.simbirsofttest.service.ParserService;
import ru.simbirsoft.simbirsofttest.validation.ArgValidator;

import java.util.HashMap;

public class HtmlPageContentParser {

    public static int ARG_NOLOG_NODB_DEFAULT = 0;
    public static int ARG_LOG_NODB = 1;
    public static int ARG_NOLOG_DB = 2;
    public static int ARG_LOG_DB = 3;

    public static int ARG_VALUE = ARG_NOLOG_NODB_DEFAULT;

    public static void main(String[] args) {String error = new ArgValidator().validate(args);

        if (error.isEmpty()) {

            ARG_VALUE = Integer.parseInt(args[1]);

            HashMap<String, Integer> result = (HashMap<String, Integer>) ParserService.getInstance().parseAddress(args[0]);

            if (ARG_VALUE == ARG_LOG_DB || ARG_VALUE == ARG_NOLOG_DB) {
                Thread dbThread = new Thread(new DbPrinter(result));
                dbThread.start();
            } else {
                new ConsolePrinter().print(result);
            }

        }

        System.out.println(error);

    }

}
