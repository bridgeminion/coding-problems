package tree;

import common.TreeNode;

/**
 * You are given the root of a binary tree with n nodes where each node in the tree has node.val coins. There are n coins in total throughout the whole tree.
 *
 * In one move, we may choose two adjacent nodes and move one coin from one node to another. A move may be from parent to child, or from child to parent.
 *
 * Return the minimum number of moves required to make every node have exactly one coin.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,0,0]
 * Output: 2
 * Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
 * Example 2:
 *
 *
 * Input: root = [0,3,0]
 * Output: 3
 * Explanation: From the left child of the root, we move two coins to the root [taking two moves]. Then, we move one coin from the root of the tree to the right child.
 */
public class DistributeCoinsInBinaryTree {
    int sum = 0;
    public int distributeCoins(TreeNode root) {
        helper(root);

        return sum;
    }

    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        sum += Math.abs(left[0] - left[1]);
        sum += Math.abs(right[0] - right[1]);

        return new int[] {left[0]+right[0]+1, left[1]+right[1]+root.val};
    }
}
