package tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BoundaryOfBinaryTree {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return List.of(root.val);
        }
        List<Integer> result = new ArrayList<>();
        result.add(root.val);
        getLeft(root.left, result);
        getLeaf(root, result);
        getRight(root.right, result);

        return result;
    }

    private void getLeft(TreeNode root, List<Integer> left) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        left.add(root.val);
        if (root.left != null) {
            getLeft(root.left, left);
        } else {
            getLeft(root.right, left);
        }
    }

    private void getRight(TreeNode root, List<Integer> right) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        if (root.right != null) {
            getRight(root.right, right);
        } else {
            getRight(root.left, right);
        }
        right.add(root.val);
    }

    private void getLeaf(TreeNode root, List<Integer> leaf) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            leaf.add(root.val);
        }
        getLeaf(root.left, leaf);
        getLeaf(root.right, leaf);
    }

}
