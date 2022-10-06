package tree;

import common.Node;

import java.util.*;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * For example, you may serialize the following 3-ary tree
 *
 *
 *
 *
 * as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessarily need to follow this format.
 *
 * Or you can follow LeetCode's level order traversal serialization format, where each group of children is separated by the null value.
 *
 *
 *
 *
 * For example, the above tree may be serialized as [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].
 *
 * You do not necessarily need to follow the above-suggested formats, there are many more different formats that work so please be creative and come up with different approaches yourself.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Example 2:
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [1,null,3,2,4,null,5,6]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 */
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
