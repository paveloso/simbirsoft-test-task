package ru.simbirsoft.simbirsofttest.validation;

import java.text.ParseException;

public class ArgValidator {
    public String validate(String[] args) {
        if (args.length == 0) {
            return "no arguments were passed";
        }
        if (args.length > 2) {
            return "too many arguments were passed";
        }
        if (args.length == 1 && !args[0].startsWith("http")) {
            return "wrong url was passed. dont forget http/https";
        }
        if (args.length == 2) {
            int arg = 0;
            try {
                arg = Integer.parseInt(args[1]);
            } catch (NumberFormatException ex) {
                return "second argument must be a digit";
            }
            if (arg > 3) {
                return "wrong second argument. check the readme";
            }
            if (!args[0].startsWith("http")) {
                return "wrong url was passed. dont forget http/https";
            }
        }
        return "";
    }
}
