package bfs;

import common.TreeNode;

import java.util.*;

public class BinaryTreeVerticalOrderTraversal {
    class Entry {
        TreeNode node;
        int index;
        public Entry (TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<Entry> queue = new LinkedList<>();
        queue.offer(new Entry(root, 0));
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                Entry entry = queue.poll();
                map.putIfAbsent(entry.index, new ArrayList<>());
                map.get(entry.index).add(entry.node.val);
                if (entry.node.left != null) {
                    queue.offer(new Entry(entry.node.left, entry.index-1));
                }
                if (entry.node.right != null) {
                    queue.offer(new Entry(entry.node.right, entry.index+1));
                }
            }
        }
        return new ArrayList<>(map.values());
    }
}
