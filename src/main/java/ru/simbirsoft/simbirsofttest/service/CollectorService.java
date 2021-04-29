package ru.simbirsoft.simbirsofttest.service;

import java.util.HashMap;
import java.util.Map;

public class CollectorService {

    private HashMap<String, Integer> result = new HashMap<String, Integer>();

    void collectData(String[] words) {
        for (String word : words) {
            if (word != null) {
                if (result.containsKey(word.toUpperCase())) {
                    result.put(word.toUpperCase(), result.get(word) != null ? result.get(word) + 1 : 1);
                } else {
                    result.put(word.toUpperCase(), 1);
                }
            }
        }
    }

    Map<String, Integer> getResult() {
        return result;
    }
}
