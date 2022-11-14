package tree;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.
 *
 * Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:
 *
 * 'L' means to go from a node to its left child node.
 * 'R' means to go from a node to its right child node.
 * 'U' means to go from a node to its parent node.
 * Return the step-by-step directions of the shortest path from node s to node t.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
 * Output: "UURL"
 * Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
 * Example 2:
 *
 *
 * Input: root = [2,1], startValue = 2, destValue = 1
 * Output: "L"
 * Explanation: The shortest path is: 2 → 1.
 *
 */
public class StepByStepDirectionsFromABinaryTreeNodeToAnother {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode lca = findLca(root, startValue, destValue);
//        StringBuilder pathToStart = new StringBuilder();
//        traverse(lca, startValue, pathToStart);
//        StringBuilder pathToDest = new StringBuilder();
//        traverse(lca, destValue, pathToDest);
        String pathToStart = traverseBFS(lca, startValue);
        String pathToDest = traverseBFS(lca, destValue);
        StringBuilder direction = new StringBuilder();
        for (int i=0; i<pathToStart.length(); i++) {
            direction.append("U");
        }
        direction.append(pathToDest);

        return direction.toString();
    }

    private TreeNode findLca (TreeNode root, int start, int dest) {
        if (root == null || root.val == start || root.val == dest) {
            return root;
        }
        TreeNode left = findLca(root.left, start, dest);
        TreeNode right = findLca(root.right, start, dest);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }
        return root;
    }

    private TreeNode traverse(TreeNode root, int target, StringBuilder sb) {
        if (root == null || root.val == target) {
            return root;
        }
        TreeNode left = traverse(root.left, target, sb.append("L"));
        if (left != null) {
            return left;
        }
        sb.setLength(sb.length() - 1);
        TreeNode right = traverse(root.right, target, sb.append("R"));
        if (right != null) {
            return right;
        }
        sb.setLength(sb.length() - 1);
        return null;
    }

    class Entry {
        TreeNode node;
        String path;
        public Entry (TreeNode node, String path) {
            this.node = node;
            this.path = path;
        }
    }

    private String traverseBFS(TreeNode root, int value) {
        Queue<Entry> queue = new LinkedList<>();
        queue.offer(new Entry(root, ""));
        while (!queue.isEmpty()) {
            Entry entry = queue.poll();
            if (entry.node.val == value) {
                return entry.path;
            }
            if (entry.node.left != null) {
                queue.offer(new Entry(entry.node.left, entry.path + "L"));
            }
            if (entry.node.right != null) {
                queue.offer(new Entry(entry.node.right, entry.path + "R"));
            }
        }

        return null;
    }

    public static void main(String[] args) {
        StepByStepDirectionsFromABinaryTreeNodeToAnother app = new StepByStepDirectionsFromABinaryTreeNodeToAnother();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node5.left = node1;
        node5.right = node2;
        node1.left = node3;
        node2.left = node6;
        node2.right = node4;
        System.out.println(app.getDirections(node5, 3, 6));

    }
}
