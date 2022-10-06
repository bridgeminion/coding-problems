package tree;

import common.TreeNode;

import java.util.*;

/**
 * Given the root of a binary search tree, a target value, and an integer k, return the k values in the BST that are closest to the target. You may return the answer in any order.
 *
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [4,2,5,1,3], target = 3.714286, k = 2
 * Output: [4,3]
 * Example 2:
 *
 * Input: root = [1], target = 0.000000, k = 1
 * Output: [1]
 */
public class ClosestBinarySearchTreeValueTwoSolutionTwo {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Deque<Integer> dq = new LinkedList<>();
        dfs (root, dq);
        while (dq.size() > k) {
            if (Math.abs(dq.peekFirst() - target) > Math.abs(dq.peekLast() - target)) {
                dq.pollFirst();
            } else {
                dq.pollLast();
            }
        }

        return new ArrayList(dq);
    }

    private void dfs(TreeNode root, Deque<Integer> dq) {
        if (root == null) {
            return;
        }
        dfs(root.left, dq);
        dq.add(root.val);
        dfs(root.right, dq);
    }

    public static void main(String[] args) {
        ClosestBinarySearchTreeValueTwoSolutionTwo app = new ClosestBinarySearchTreeValueTwoSolutionTwo();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node4.left = node2;
        node4.right = node5;
        node2.left = node1;
        node2.right = node3;
        app.closestKValues(node4, 3.714286, 2);

    }

}
