package stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class SmallestSubsequenceOfDistinctCharacters {
    public String smallestSubsequence(String s) {
        int[] lastIndex = new int[26];
        int[] seen = new int[26];
        for (int i=0; i<s.length(); i++) {
            lastIndex[s.charAt(i)-'a'] = i;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i=0; i<s.length(); i++) {
            int c = s.charAt(i)-'a';
            if (seen[c] > 0) {
                continue;
            }
            while (!stack.isEmpty() && c < stack.peek() && lastIndex[stack.peek()] > i) {
                seen[stack.pop()]--;
            }
            stack.push(c);
            seen[c]++;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append((char)(stack.pollLast() + 'a'));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        SmallestSubsequenceOfDistinctCharacters app = new SmallestSubsequenceOfDistinctCharacters();
        System.out.println(app.smallestSubsequence("cbaacabcaaccaacababa"));
    }

}
