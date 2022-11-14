package bfs;

import java.util.*;

/**
 * Your car starts at position 0 and speed +1 on an infinite number line. Your car can go into negative positions.
 * Your car drives automatically according to a sequence of instructions 'A' (accelerate) and 'R' (reverse):
 *
 * When you get an instruction 'A', your car does the following:
 * position += speed
 * speed *= 2
 * When you get an instruction 'R', your car does the following:
 * If your speed is positive then speed = -1
 * otherwise speed = 1
 * Your position stays the same.
 * For example, after commands "AAR", your car goes to positions 0 --> 1 --> 3 --> 3, and your speed goes to 1 --> 2 --> 4 --> -1.
 *
 * Given a target position target, return the length of the shortest sequence of instructions to get there.
 *
 *
 *
 * Example 1:
 *
 * Input: target = 3
 * Output: 2
 * Explanation:
 * The shortest instruction sequence is "AA".
 * Your position goes from 0 --> 1 --> 3.
 * Example 2:
 *
 * Input: target = 6
 * Output: 5
 * Explanation:
 * The shortest instruction sequence is "AAARA".
 * Your position goes from 0 --> 1 --> 3 --> 7 --> 7 --> 6.
 *
 */
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
