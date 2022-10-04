package tree;

import common.TreeNode;

public class BinaryTreeMaximumPathSum {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);

        return max;
    }

    public int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        left = left > 0 ? left : 0;
        right = right > 0 ? right : 0;
        max = Math.max(max, root.val + left + right);

        return root.val + Math.max(left, right);
    }
}
