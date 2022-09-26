package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] != 0) {
            return -1;
        }
        int[][] moves = {{1,0}, {-1,0}, {0,1}, {0,-1}, {-1,-1}, {1,1}, {-1, 1}, {1,-1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0,0});
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        int steps = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                int[] entry = queue.poll();
                if (entry[0] == m-1 && entry[1] == n-1) {
                    return steps;
                }
                for (int[] move : moves) {
                    int newX = entry[0] + move[0];
                    int newY = entry[1] + move[1];
                    if (newX >=0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == 0 && !visited[newX][newY]) {
                        queue.offer(new int[] {newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
            steps++;
        }

        return -1;
    }

}
