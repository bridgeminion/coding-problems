package graph;

import java.util.*;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                inDegree.put(c, 0);
            }
        }
        for (int i=1; i<words.length; i++) {
            String w1 = words[i-1];
            String w2 = words[i];
            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                return "";
            }
            for (int j=0; j<w1.length() && j<w2.length(); j++) {
                char c1 = w1.charAt(j), c2 = w2.charAt(j);
                if (c1 == c2) continue;
                graph.putIfAbsent(c1, new HashSet<>());
                if (!graph.get(c1).contains(c2)) {
                    graph.get(c1).add(c2);
                    inDegree.put(c2, inDegree.getOrDefault(c2, 0) + 1);
               }
                break;
            }
        }
        Queue<Character> queue = ew LinkedList<>();
        for (Map.Entry<Character, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }
        String result = "";
        while (!queue.isEmpty()) {
            char l = queue.poll();
            result += l;
            if (graph.containsKey(l)) {
                for (char s : graph.get(l)) {
                    inDegree.put(s, inDegree.get(s)-1);
                    if (inDegree.get(s) == 0) {
                        queue.offer(s);
                    }
                }
            }
        }

        return result.length() == inDegree.size() ? result : "";
    }

    public static void main(String[] args) {
        AlienDictionary app = new AlienDictionary();
        String[] words = {"a", "b", "a"};
        System.out.println(app.alienOrder(words));
    }

}
