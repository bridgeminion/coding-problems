package twopointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design a data structure that will be initialized with a string array, and then it should answer queries of the shortest distance between two different strings from the array.
 *
 * Implement the WordDistance class:
 *
 * WordDistance(String[] wordsDict) initializes the object with the strings array wordsDict.
 * int shortest(String word1, String word2) returns the shortest distance between word1 and word2 in the array wordsDict.
 *
 *
 * Example 1:
 *
 * Input
 * ["WordDistance", "shortest", "shortest"]
 * [[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
 * Output
 * [null, 3, 1]
 *
 * Explanation
 * WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
 * wordDistance.shortest("coding", "practice"); // return 3
 * wordDistance.shortest("makes", "coding");    // return 1
 *
 */
public class WordDistance {
    Map<String, List<Integer>> map;
    public WordDistance(String[] wordsDict) {
        map = new HashMap<>();
        for (int i=0; i<wordsDict.length; i++) {
            String word = wordsDict[i];
            map.putIfAbsent(word, new ArrayList<>());
            map.get(word).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);
        int i1 = 0, i2 = 0, min = Integer.MAX_VALUE;
        while (i1 < l1.size() && i2 < l2.size()) {
            min = Math.min(min, Math.abs(l1.get(i1) - l2.get(i2)));
            if (l1.get(i1) < l2.get(i2)) {
                i1++;
            } else {
                i2++;
            }
        }

        return min;
    }

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        WordDistance app = new WordDistance(words);
        System.out.println(app.shortest("coding", "practice"));
    }
}
