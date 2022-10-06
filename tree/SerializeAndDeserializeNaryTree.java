package tree;

import common.Node;

import java.util.*;

public class SerializeAndDeserializeNaryTree {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);

        return sb.toString();
    }

    private void dfs(Node root, StringBuilder sb) {
        if (root == null) {
            sb.append("null").append(",");
            return;
        }
        sb.append(root.val).append(",");
        sb.append("size=").append(root.children.size()).append(",");
        for (Node child : root.children) {
            dfs(child, sb);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        String[] tokens = data.split(",");
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(tokens));
        return helper(queue);
    }

    private Node helper(Queue<String> queue) {
        String data = queue.poll();
        if (data.equals("null")) {
            return null;
        }
        int value = Integer.valueOf(data);
        Node root = new Node(value);
        String sizeStr = queue.poll();
        int childrenSize = Integer.valueOf(sizeStr.substring(sizeStr.indexOf("=")+1));
        List<Node> children = new ArrayList<>();
        for (int i=0; i<childrenSize; i++) {
            children.add(helper(queue));
        }
        root.children = children;

        return root;
    }

}
