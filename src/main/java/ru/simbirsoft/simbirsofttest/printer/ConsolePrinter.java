package ru.simbirsoft.simbirsofttest.printer;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ConsolePrinter implements ResultPrinter {

    @Override
    public void print(HashMap<String, Integer> result) {
        result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);
    }
}
