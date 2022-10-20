package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);
        for (int i=1; i<intervals.length; i++) {
            int[] last = merged.get(merged.size() - 1);
            int[] interval = intervals[i];
            if (interval[0] > last[1]) {
                merged.add(interval);
            } else {
                last[1] = Math.max(last[1], interval[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
