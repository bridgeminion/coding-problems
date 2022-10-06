package tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
public class ClosestBinarySearchTreeValueTwo {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((e1, e2) -> Double.compare(Math.abs(target - e2), Math.abs(target - e1)));
        dfs (root, k, pq);
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }

        return result;
    }

    private void dfs(TreeNode root, int k, PriorityQueue<Integer> pq) {
        if (root == null) {
            return;
        }
        dfs(root.left, k, pq);
        pq.offer(root.val);
        if (pq.size() > k) {
            pq.poll();
        }
        dfs(root.right, k, pq);
    }

    public static void main(String[] args) {
        ClosestBinarySearchTreeValueTwo app = new ClosestBinarySearchTreeValueTwo();
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
