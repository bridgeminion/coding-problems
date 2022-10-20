package binarysearch;

import java.util.Map;
import java.util.TreeMap;

public class MeetingRoomsTwo {
    public int minMeetingRooms(int[][] intervals) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] interval : intervals) {
            map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
            map.put(interval[1], map.getOrDefault(interval[1], 0) - 1);
        }
        int count = 0, max = 0;
        for (int value : map.values()) {
            count += value;
            max = Math.max(max, count);
        }

        return max;
    }
}
