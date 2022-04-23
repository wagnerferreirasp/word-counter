package org.example.wordcounter.core.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Utility functions for maps
 */
public final class MapUtils {

    private MapUtils() {
        // Utility class
    }

    public static LinkedHashMap<String, Integer> sortedMapByValue(Map<String, Integer> map) {
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> result.put(entry.getKey(), entry.getValue()));

        return result;
    }

    public static Map<String, Integer> sumMaps(final Map<String, Integer> map1, final Map<String, Integer> map2) {
        Map<String, Integer> result = new HashMap<>(map1);
        map2.forEach((key,value) -> result.merge(key, value, Integer::sum));
        return result;
    }
}
