package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n grid where each cell can have one of three values:
 *
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * Example 2:
 *
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 * Example 3:
 *
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 */
public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int fresh = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[] {i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        if (fresh == 0) {
            return 0;
        }
        int[][] moves = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0;i<size; i++) {
                int[] entry = queue.poll();
                for (int[] move : moves) {
                    int newX = entry[0] + move[0];
                    int newY = entry[1] + move[1];
                    if (newX>=0 && newX<m && newY>=0 && newY<n && grid[newX][newY] == 1) {
                        grid[newX][newY] = 2;
                        queue.offer(new int[] {newX, newY});
                        fresh--;
                    }
                }
            }
            steps++;
            if (fresh == 0) {
                return steps;
            }
        }

        return -1;
    }

}
