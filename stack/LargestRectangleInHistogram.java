package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0;
        for (int i=0; i<=heights.length; i++) {
            int num = i == heights.length ? Integer.MIN_VALUE : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > num) {
                int index = stack.pop();
                int leftSmall = stack.isEmpty() ? -1 : stack.peek();
                max = Math.max(max, (i - leftSmall - 1) * heights[index]);
            }
            stack.push(i);
        }

        return max;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram app = new LargestRectangleInHistogram();
        int[] heights = {2,1,2};
        System.out.println(app.largestRectangleArea(heights));
    }

}
