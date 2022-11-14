package bfs;

import java.util.*;

public class RaceCar {
    class Entry {
        int pos;
        int speed;
        public Entry (int pos, int speed) {
            this.pos = pos;
            this.speed = speed;
        }
    }
    public int racecar(int target) {
        Queue<Entry> queue = new LinkedList<>();
        queue.offer(new Entry(0, 1));
        int count = 0;
        Set<String> visited = new HashSet<>();
        visited.add("0 1");
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                Entry entry = queue.poll();
                if (entry.pos == target) {
                    return count;
                }
                int nextPosA = entry.pos + entry.speed;
                int nextSpeedA = entry.speed * 2;
                if (visited.add(nextPosA + " " + nextSpeedA) && nextPosA > 0 && nextPosA < target * 2) {
                    queue.offer(new Entry(nextPosA, nextSpeedA));
                }
                int nextSpeedR = entry.speed > 0 ? -1 : 1;
                if (visited.add(entry.pos + " " + nextSpeedR)) {
                    queue.offer(new Entry(entry.pos, nextSpeedR));
                }
            }
            count++;
        }

        return -1;
    }

    public static void main(String[] args) {
        RaceCar app = new RaceCar();
        System.out.println(app.racecar(6));
    }
}
