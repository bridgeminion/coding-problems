package binarysearch;

import java.util.Map;
import java.util.TreeMap;

/**
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 * Example 2:
 *
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 */
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
