package bfs;

import common.NestedInteger;
import java.util.List;

/**
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.
 *
 * The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth. Let maxDepth be the maximum depth of any integer.
 *
 * The weight of an integer is maxDepth - (the depth of the integer) + 1.
 *
 * Return the sum of each integer in nestedList multiplied by its weight.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: 8
 * Explanation: Four 1's with a weight of 1, one 2 with a weight of 2.
 * 1*1 + 1*1 + 2*2 + 1*1 + 1*1 = 8
 * Example 2:
 *
 *
 * Input: nestedList = [1,[4,[6]]]
 * Output: 17
 * Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1.
 * 1*3 + 4*2 + 6*1 = 17
 */
public class NestedListWeightSumTwo {
    int maxDepth = 0;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        findDepth(nestedList, 1);
        return helper(nestedList, 1);
    }

    private void findDepth (List<NestedInteger> list, int depth) {
        if (list.isEmpty()) {
            maxDepth = Math.max(maxDepth, depth);
        } else {
            for (NestedInteger ni : list) {
                if (ni.isInteger()) {
                    maxDepth = Math.max(maxDepth, depth);
                } else {
                    findDepth(ni.getList(), depth+1);
                }
            }
        }
    }

    private int helper(List<NestedInteger> list, int depth) {
        int sum = 0;
        for (NestedInteger ni : list) {
            if (ni.isInteger()) {
                sum += (maxDepth - depth + 1) * ni.getInteger();
            } else {
                sum += helper(ni.getList(), depth+1);
            }
        }

        return sum;
    }

}
