package dfs;

import java.util.HashMap;
import java.util.Map;

public class LongestIncreasingPathInAMatrix {
    int[][] moves = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] memo = new int[m][n];
        int max = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                max = Math.max(max, dfs(matrix, memo, i, j, m, n));
            }
        }

        return max;
    }

    private int dfs(int[][] matrix, int[][] memo, int i, int j, int m, int n) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int max = 0;
        for (int[] move : moves) {
            int newX = i + move[0];
            int newY = j + move[1];
            if (newX>=0 && newX<m && newY>=0 && newY<n && matrix[i][j]<matrix[newX][newY]) {
                max = Math.max(max, dfs(matrix, memo, newX, newY, m, n));
            }
        }

        memo[i][j] = max + 1;

        return max+1;
    }

    public static void main(String[] args) {
        LongestIncreasingPathInAMatrix app = new LongestIncreasingPathInAMatrix();
        int[][] matrix = {{9,9,4},{6,6,8},{2,1,1}};
        System.out.println(app.longestIncreasingPath(matrix));
    }
}
