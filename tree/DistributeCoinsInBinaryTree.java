package tree;

import common.TreeNode;

public class DistributeCoinsInBinaryTree {
    int sum = 0;
    public int distributeCoins(TreeNode root) {
        helper(root);

        return sum;
    }

    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        sum += Math.abs(left[0] - left[1]);
        sum += Math.abs(right[0] - right[1]);

        return new int[] {left[0]+right[0]+1, left[1]+right[1]+root.val};
    }
}
