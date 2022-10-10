package binarysearch;

import java.util.Random;
import java.util.TreeMap;

public class RandomPickWithWeight {
    int sum = 0;
    Random random;
    TreeMap<Integer, Integer> map;
    public RandomPickWithWeight(int[] w) {
        random = new Random();
        map = new TreeMap<>();
        for (int i=0; i<w.length; i++) {
            sum += w[i];
            map.put(sum, i);
        }
    }

    public int pickIndex() {
        int index = random.nextInt(sum);
        return map.higherEntry(index).getValue();
    }

}
