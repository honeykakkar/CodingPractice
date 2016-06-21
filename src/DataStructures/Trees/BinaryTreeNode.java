package DataStructures.Trees;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/19/2016
 */
public class BinaryTreeNode {

    public int data;
    public BinaryTreeNode left;
    public BinaryTreeNode right;
    public boolean visited;

    public BinaryTreeNode(int data){
        this.data = data;
        this.left = null;
        this.right = null;
        visited = false;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }
}
