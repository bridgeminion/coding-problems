package common;

public class DoublyNode {
    public int val;
    public DoublyNode left;
    public DoublyNode right;

    public DoublyNode() {
    }

    public DoublyNode(int _val) {
        val = _val;
    }

    public DoublyNode(int _val, DoublyNode _left, DoublyNode _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}