package stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class OneThreeTwoPattern {
    public boolean find132pattern(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        int secondMax = Integer.MIN_VALUE;
        for (int i=nums.length-1; i>=0; i--) {
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                secondMax = stack.pop();
            }
            stack.push(nums[i]);
            if (nums[i] < secondMax) {
                return true;
            }
        }

        return false;
    }
}
