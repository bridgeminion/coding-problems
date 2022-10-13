package binarysearch;

import java.util.Map;
import java.util.TreeMap;

public class MyCalendar {
    TreeMap<Integer, Integer> map = new TreeMap<>();
    public MyCalendar() {
    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> entry = map.lowerEntry(end);
        if (entry == null || entry.getValue() <= start) {
            map.put(start, end);

            return true;
        }
        return false;
    }
}
