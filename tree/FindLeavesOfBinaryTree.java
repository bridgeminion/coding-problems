package tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
 *
 * Collect all the leaf nodes.
 * Remove all the leaf nodes.
 * Repeat until the tree is empty.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5]
 * Output: [[4,5,3],[2],[1]]
 * Explanation:
 * [[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers since per each level it does not matter the order on which elements are returned.
 * Example 2:
 *
 * Input: root = [1]
 * Output: [[1]]
 */
public class FindLeavesOfBinaryTree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        Map<Integer, List<Integer>> result = new TreeMap<>();
        getHeight(root, result);

        return new ArrayList(result.values());
    }
    private int getHeight(TreeNode root, Map<Integer, List<Integer>> map) {
        if (root == null) {
            return 0;
        }
        int height = Math.max(getHeight(root.left, map), getHeight(root.right, map)) + 1;
        map.putIfAbsent(height, new ArrayList<>());
        map.get(height).add(root.val);

        return height;
    }

}
