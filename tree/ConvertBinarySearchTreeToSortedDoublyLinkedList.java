package tree;

import common.DoublyNode;

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
