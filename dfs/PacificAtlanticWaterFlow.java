package dfs;

import java.util.ArrayList;
import java.util.List;

public class PacificAtlanticWaterFlow {
    int[][] moves = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] pacific = new int[m][n];
        int[][] atlantic = new int[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (i == 0 || j == 0) {
                    floodFill(heights, i, j, pacific, m, n);
                }
           }
        }
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (i == m-1 || j == n-1) {
                    floodFill(heights, i, j, atlantic, m, n);
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (pacific[i][j] == 1 && atlantic[i][j] == 1) {
                    result.add(List.of(i, j));
                }
            }
        }

        return result;
    }

    private void floodFill(int[][] heights, int i, int j, int[][] ocean, int m, int n) {
        ocean[i][j] = 1;
        for (int[] move : moves) {
            int newX = i + move[0];
            int newY = j + move[1];
            if (newX>=0 && newX<m && newY>=0 && newY<n && heights[newX][newY]>=heights[i][j]&&ocean[newX][newY]==0) {
                floodFill(heights, newX, newY, ocean, m, n);
            }
        }
    }

    public static void main(String[] args) {
        PacificAtlanticWaterFlow app = new PacificAtlanticWaterFlow();
        int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        app.pacificAtlantic(heights);
    }

}
