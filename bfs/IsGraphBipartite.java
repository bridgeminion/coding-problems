package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes
 * that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:
 *
 * There are no self-edges (graph[u] does not contain u).
 * There are no parallel edges (graph[u] does not contain duplicate values).
 * If v is in graph[u], then u is in graph[v] (the graph is undirected).
 * The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.
 *
 * Return true if and only if it is bipartite.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * Output: false
 * Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.
 * Example 2:
 *
 *
 * Input: graph = [[1,3],[0,2],[1,3],[0,2]]
 * Output: true
 * Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.
 */
public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        int m = graph.length;
        int[] colors = new int[m];
        for (int i=0; i<m; i++) {
            if (colors[i] == 0 && !bfs(colors, graph, i, 1)) {
                return false;
            }
        }

        return true;
    }

    private boolean bfs(int[] colors, int[][] graph, int i, int color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j=0; j<size; j++) {
                int node = queue.poll();
                if (colors[node] == 0) {
                    colors[node] = color;
                    for (int g : graph[node]) {
                        queue.offer(g);
                    }
                } else {
                    if (colors[node] != color) {
                        return false;
                    }
                }
            }
            color = -color;
        }

        return true;
    }

    public boolean isBipartite1(int[][] graph) {
        int m = graph.length;
        int[] colors = new int[m];
        for (int i=0; i<m; i++) {
            if (graph[i].length > 0) {
                if (colors[i] == 0 && !isValid1(colors, graph, i, 1)) {
                    return false;
                }
            }
         }

        return true;
    }

    private boolean isValid1(int[] colors, int[][] graph, int i, int color) {
        System.out.println("index=" + i);
        if (colors[i] != 0) {
            return colors[i] == color;
        }
        colors[i] = color;
        for (int node : graph[i]) {
            if (!isValid1(colors, graph, node, -color)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        IsGraphBipartite app = new IsGraphBipartite();
        int[][] graph = {{},{2},{1},{},{},{7,8},{7,8,9},{5,6},{5,6},{6},{12,13,14},{12},{10,11},{10},{10},{18},{17,18},{16},{15,16},{},{22,23,24},{22,23,24},{20,21},{20,21},{20,21},{27,28,29},{27,28,29},{25,26},{25,26},{25,26},{32,33,34},{33},{30},{30,31},{30},{37,39},{38},{35},{36},{35},{44},{43,44},{},{41},{40,41},{47,48,49},{47,48,49},{45,46},{45,46},{45,46}};
        System.out.println(app.isBipartite(graph));
    }
}
