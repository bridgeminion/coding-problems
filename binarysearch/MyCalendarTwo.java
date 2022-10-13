package binarysearch;

import com.sun.security.jgss.GSSUtil;

import java.util.Map;
import java.util.TreeMap;

public class MyCalendarTwo {
    TreeMap<Integer, Integer> map = new TreeMap<>();
    public MyCalendarTwo() {
    }

    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            count += entry.getValue();
            if (count > 2) {
                map.put(start, map.get(start) - 1);
                map.put(end, map.get(end) + 1);
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        MyCalendarTwo app = new MyCalendarTwo();
        System.out.println(app.book(24, 40));
        System.out.println(app.book(43, 50));
        System.out.println(app.book(27, 43));
        System.out.println(app.book(5, 21));
        System.out.println(app.book(30, 40));
        System.out.println(app.book(14, 29));
        System.out.println(app.book(3, 19));
        System.out.println(app.book(3, 14));
        System.out.println(app.book(25, 39));
        System.out.println(app.book(6, 19));
    }
}
