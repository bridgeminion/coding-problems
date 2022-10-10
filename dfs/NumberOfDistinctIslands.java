package dfs;

import java.util.*;

/**
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.
 *
 * Return the number of distinct islands.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
 * Output: 1
 * Example 2:
 *
 *
 * Input: grid = [[1,1,0,1,1],[1,0,0,0,0],[0,0,0,0,1],[1,1,0,1,1]]
 * Output: 3
 *
 */
public class NumberOfDistinctIslands
{
    class Pair {
        String x;
        String y;
        int area;
        public Pair(String x, String y, int area) {
            this.x = x;
            this.y = y;
            this.area = area;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x.equals(pair.x) && y.equals(pair.y) && area == pair.area;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, area);
        }
    }

    int[][] moves = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public int numDistinctIslands(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Set<Pair> result = new HashSet<>();
        for (int i=0; i<m; i++)  {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    Pair pair = new Pair("", "", 0);
                    dfs(grid, i, j, m, n, i, j, pair);
                    result.add(pair);
                }
            }
        }

        return result.size();
    }

    private void dfs(int[][] grid, int i, int j, int m, int n, int x, int y, Pair pair) {
        pair.x += i - x;
        pair.y += j - y;
        pair.area += 1;
        grid[i][j] = 2;
        for (int[] move : moves) {
            int ii = i + move[0];
            int jj = j + move[1];
            if (ii>=0 && ii<m && jj>=0 && jj<n && grid[ii][jj] == 1) {
                dfs(grid, ii, jj, m, n, x, y, pair);
            }
        }
    }

    public static void main(String[] args) {
        NumberOfDistinctIslands app = new NumberOfDistinctIslands();
        int[][] grid = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        System.out.println(app.numDistinctIslands(grid));
    }

}
