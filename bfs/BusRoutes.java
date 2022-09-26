package bfs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
 *
 * For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 * You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.
 *
 * Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
 *
 *
 *
 * Example 1:
 *
 * Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 * Example 2:
 *
 * Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * Output: -1
 *
 */
public class BusRoutes {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i=0; i<routes.length; i++) {
            for (int j=0; j<routes[i].length; j++) {
                map.putIfAbsent(routes[i][j], new ArrayList<>());
                map.get(routes[i][j]).add(i);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        boolean[] visited = new boolean[routes.length];
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                int stop = queue.poll();
                if (stop == target) {
                    return count;
                }
                for (int bus : map.get(stop)) {
                    if (!visited[bus]) {
                        queue.addAll(Arrays.stream(routes[bus]).boxed().collect(Collectors.toList()));
                        visited[bus] = true;

                    }
                }
            }
            count++;
        }

        return -1;
    }

     public static void main(String[] args) {
        BusRoutes app = new BusRoutes();
        int[][] routes = {{1,2,7}, {3,6,7}};
        System.out.println(app.numBusesToDestination(routes, 1, 6));
    }
}
