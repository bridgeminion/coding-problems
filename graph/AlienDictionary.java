package graph;

import java.util.*;

/**
 * There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.
 *
 * You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted lexicographically by the rules of this new language.
 *
 * Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.
 *
 * A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 * Example 2:
 *
 * Input: words = ["z","x"]
 * Output: "zx"
 * Example 3:
 *
 * Input: words = ["z","x","z"]
 * Output: ""
 * Explanation: The order is invalid, so return "".
 */
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
