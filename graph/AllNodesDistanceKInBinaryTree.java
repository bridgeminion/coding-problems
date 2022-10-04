package graph;

import common.TreeNode;

import java.util.*;

/**
 * Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.
 *
 * You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
 * Output: [7,4,1]
 * Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
 * Example 2:
 *
 * Input: root = [1], target = 1, k = 3
 * Output: []
 */
public class AllNodesDistanceKInBinaryTree {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        buildMap(root, map);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(target.val);
        Set<Integer> visited = new HashSet<>();
        visited.add(target.val);
        int distance = 0;
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (distance == k) {
                for (int i=0; i<size; i++) {
                    result.add(queue.poll());
                }
                return result;
            }
            for (int i=0; i<size; i++) {
                int node = queue.poll();
                Set<Integer> sibs = map.get(node);
                if (sibs != null) {
                    for (int sib : sibs) {
                        if (visited.add(sib)) {
                            queue.offer(sib);
                        }
                    }
                }
            }
            distance++;
        }

        return result;
    }

    private void buildMap(TreeNode root, Map<Integer, Set<Integer>> map) {
        if (root == null) {
            return;
        }
        map.putIfAbsent(root.val, new HashSet<>());
        if (root.left != null) {
            map.putIfAbsent(root.left.val, new HashSet<>());
            map.get(root.val).add(root.left.val);
            map.get(root.left.val).add(root.val);
        }
        if (root.right != null) {
            map.putIfAbsent(root.right.val, new HashSet<>());
            map.get(root.val).add(root.right.val);
            map.get(root.right.val).add(root.val);
        }
        buildMap(root.left, map);
        buildMap(root.right, map);
    }

}
