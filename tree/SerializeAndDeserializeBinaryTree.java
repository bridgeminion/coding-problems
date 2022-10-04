package tree;

import common.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree {
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeDfs(root, sb);

        return sb.toString();
    }

    private void serializeDfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null").append(",");
            return;
        }
        sb.append(root.val).append(",");
        serializeDfs(root.left, sb);
        serializeDfs(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>();
        String[] tokens = data.split(",");
        queue.addAll(Arrays.asList(tokens));

        return deserializeDfs(queue);
    }

    private TreeNode deserializeDfs(Queue<String> queue) {
        String rootStr = queue.poll();
        if (rootStr.equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(rootStr));
        root.left = deserializeDfs(queue);
        root.right = deserializeDfs(queue);

        return root;
    }
}
