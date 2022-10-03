package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 *
 * Return the size of the largest island in grid after applying this operation.
 *
 * An island is a 4-directionally connected group of 1s.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,0],[0,1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * Example 2:
 *
 * Input: grid = [[1,1],[1,0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 * Example 3:
 *
 * Input: grid = [[1,1],[1,1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 */
public class MakingALargeIsland {
    public int largestIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int max = 0, count = 0;
        for (int i=0; i<m; i++) {
            for (int  j=0; j<n; j++) {
                if (grid[i][j] == 0) {
                    grid[i][j] = 1;
                    max = Math.max(max, helper(grid, i, j, m, n));
                    grid[i][j] = 0;
                } else {
                    count++;
                }
            }
        }

        return max == 0 ? count : max;
    }

    private int helper(int[][] grid, int i, int j, int m, int n) {
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});
        visited[i][j] = true;
        int[][] moves = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        int area = 0;
        while (!queue.isEmpty()) {
            int[] entry = queue.poll();
            area++;
            for (int[] move : moves) {
                int newX = entry[0] + move[0];
                int newY = entry[1] + move[1];
                if (newX >=0 && newX < m && newY >= 0 && newY<n && grid[newX][newY] == 1 && !visited[newX][newY]) {
                    queue.offer(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }
        }

        return area;
    }

    public static void main(String[] args) {
        MakingALargeIsland app = new MakingALargeIsland();
        int[][] grid = {{1,1,1,1,0},{1,0,0,0,0},{0,0,0,1,1},{1,1,0,1,0},{0,0,0,0,1}};
        System.out.println(app.largestIsland(grid));
    }

}
