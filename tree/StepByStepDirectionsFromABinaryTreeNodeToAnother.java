package tree;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

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
