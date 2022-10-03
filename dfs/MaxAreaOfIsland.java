package dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ou are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * The area of an island is the number of cells with a value 1 in the island.
 *
 * Return the maximum area of an island in grid. If there is no island, return 0.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 */
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int max = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, floodfill(grid, i, j, m, n));
                }
            }
        }

        return max;
    }

    private int floodfill(int[][] grid, int i, int j, int m, int n) {
        if (i<0 || i>=m || j<0 || j>=n || grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 2;
        int sum = 1;
        int[][] moves = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        for (int[] move : moves) {
            sum += floodfill(grid, i+move[0], j+move[1], m, n);
        }

        return sum;
    }
}
