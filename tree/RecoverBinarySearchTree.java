package tree;

import common.TreeNode;

/**
 * You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,3,null,null,2]
 * Output: [3,1,null,null,2]
 * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
 * Example 2:
 *
 *
 * Input: root = [3,1,4,null,null,2]
 * Output: [2,1,4,null,null,3]
 * Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 *
 */
public class RecoverBinarySearchTree {
    TreeNode first = null, second = null, prevNode = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {
        helper(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.left);
        if (root.val < prevNode.val && first == null) {
            first = prevNode;
        }
        if (root.val < prevNode.val && first != null) {
            second = root;
        }
        prevNode = root;
        helper(root.right);
    }

     public static void main(String[] args) {
        RecoverBinarySearchTree app = new RecoverBinarySearchTree();
        TreeNode root = new TreeNode(3);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(2);
//        root.left = node2;
//        root.right = node3;
//        node3.left = node4;
        node2.left = root;
        root.right = node4;
        app.recoverTree(node2);

    }

}
