package bfs;

import java.util.*;

public class EvaluateDivision {
    class Entry {
        String s;
        double val;
        public Entry (String s, double val) {
            this.s = s;
            this.val = val;
        }
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        int m = values.length;
        for (int i=0; i<m; i++) {
            String from = equations.get(i).get(0);
            String to = equations.get(i).get(1);
            map.putIfAbsent(from, new HashMap<>());
            map.putIfAbsent(to, new HashMap<>());
            map.get(from).put(to, values[i]);
            map.get(to).put(from, 1.0/values[i]);
        }
        double[] result = new double[queries.size()];
        int i = 0;
        for (List<String> query : queries) {
            result[i++] = bfs(query.get(0), query.get(1), map);
        }

        return result;
    }

    private double bfs(String from, String to, Map<String, Map<String, Double>> map) {
        if (!map.containsKey(from)) {
            return -1;
        }
        Queue<Entry> queue = new LinkedList<>();
        queue.offer(new Entry(from, 1.0));
        Set<String> visited = new HashSet<>();
        visited.add(from);
        while (!queue.isEmpty()) {
            Entry entry = queue.poll();
            if (entry.s.equals(to)) {
                return entry.val;
            }
            if (map.containsKey(entry.s)) {
                for (Map.Entry<String, Double> e : map.get(entry.s).entrySet()) {
                    if (visited.add(e.getKey())) {
                        queue.offer(new Entry(e.getKey(), e.getValue() * entry.val));
                    }
                }
            }
        }

        return -1;
    }
}
