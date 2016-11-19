package DataStructures.Trees.AVLTree;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/7/2016
 */
public class AVLNode {

    AVLNode left, right;
    int data;
    int height;

    /* Constructor */
    public AVLNode() {
        left = null;
        right = null;
        data = 0;
        height = 0;
    }

    /* Constructor */
    public AVLNode(int n) {
        left = null;
        right = null;
        data = n;
        height = 0;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return String.valueOf(this.data);
    }
}