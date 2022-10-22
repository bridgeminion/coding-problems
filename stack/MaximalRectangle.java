package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 6
 * Explanation: The maximal rectangle is shown in the above picture.
 * Example 2:
 *
 * Input: matrix = [["0"]]
 * Output: 0
 * Example 3:
 *
 * Input: matrix = [["1"]]
 * Output: 1
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] grid = new int[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (i == 0) {
                    grid[i][j] = matrix[i][j] - '0';
                } else {
                    if (matrix[i][j] == '1') {
                        grid[i][j] = grid[i-1][j] + 1;
                    }
                }
            }
        }
        int max = 0;
        for (int i=0; i<m; i++) {
            max = Math.max(max, helper(grid[i]));
        }

        return max;
    }

    private int helper(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0;
        for (int i=0; i<=nums.length; i++) {
            int num = i == nums.length ? Integer.MIN_VALUE : nums[i];
            while (!stack.isEmpty() && nums[stack.peek()] > num) {
                int index = stack.pop();
                int leftSmall = stack.isEmpty() ? -1 : stack.peek();
                max = Math.max(max, (i - leftSmall - 1) * nums[index]);
            }
            stack.push(i);
        }
        return max;
    }

    public static void main(String[] args) {
        MaximalRectangle app = new MaximalRectangle();
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(app.maximalRectangle(matrix));
    }
}
