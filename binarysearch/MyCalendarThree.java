package binarysearch;

import java.util.Map;
import java.util.TreeMap;

public class MyCalendarThree {
    TreeMap<Integer, Integer> map = new TreeMap<>();
    public MyCalendarThree() {
    }

    public int book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int count = 0, max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            count += entry.getValue();
            max = Math.max(max, count);
        }

        return max;
    }

    public static void main(String[] args) {
        MyCalendarThree app = new MyCalendarThree();
        System.out.println(app.book(10, 20));
        System.out.println(app.book(50, 60));
        System.out.println(app.book(10, 40));
        System.out.println(app.book(5, 15));
        System.out.println(app.book(5, 10));
        System.out.println(app.book(25, 55));
    }
}
