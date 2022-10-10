package tree;

import common.DoublyNode;

/**
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 *
 * You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list.
 * For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
 *
 * We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor,
 * and the right pointer should point to its successor. You should return the pointer to the smallest element of the linked list.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [4,2,5,1,3]
 *
 *
 * Output: [1,2,3,4,5]
 *
 * Explanation: The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.
 *
 * Example 2:
 *
 * Input: root = [2,1,3]
 * Output: [1,2,3]
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
    DoublyNode first = null, prev = null;
    public DoublyNode treeToDoublyList(DoublyNode root) {
        if (root == null) {
            return null;
        }
        inOrder(root);
        prev.right = first;
        first.left  = prev;

        return first;
    }

    private void inOrder(DoublyNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        if (first == null) {
            first = root;
        }
        if (prev != null) {
            root.left = prev;
            prev.right = root;
        }
        prev = root;
        inOrder(root.right);
    }
    
    public static void main(String[] args) {
        ConvertBinarySearchTreeToSortedDoublyLinkedList app = new ConvertBinarySearchTreeToSortedDoublyLinkedList();
        DoublyNode DoublyNode1 = new DoublyNode(1);
        DoublyNode DoublyNode2 = new DoublyNode(2);
        DoublyNode DoublyNode3 = new DoublyNode(3);
        DoublyNode DoublyNode4 = new DoublyNode(4);
        DoublyNode DoublyNode5 = new DoublyNode(5);
        DoublyNode4.left = DoublyNode2;
        DoublyNode4.right = DoublyNode5;
        DoublyNode2.left = DoublyNode1;
        DoublyNode2.right = DoublyNode3;
        app.treeToDoublyList(DoublyNode4);

    }

}
