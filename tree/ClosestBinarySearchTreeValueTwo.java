package tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
