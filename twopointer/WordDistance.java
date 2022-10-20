package twopointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
