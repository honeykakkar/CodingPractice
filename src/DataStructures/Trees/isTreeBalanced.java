package DataStructures.Trees;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/19/2016
 */

// Implement a function to check if a binary tree is balanced

public class isTreeBalanced {

    public int getHeight(BinaryTreeNode currentNode){
        if(currentNode == null)
            return -1;
        return 1 + Math.max(getHeight(currentNode.left), getHeight(currentNode.right));
    }

    boolean isBalanced(BinaryTreeNode currentNode){
        if(currentNode == null)
            return true;
        int leftHeight = getHeight(currentNode.left);
        int rightHeight = getHeight(currentNode.right);
        return (Math.abs(leftHeight - rightHeight) <= 1) && isBalanced(currentNode.left) && isBalanced(currentNode.right);
    }

    public static void main(String[] args) {
        isTreeBalanced currentObject = new isTreeBalanced();
        BinarySearchTree BST = new BinarySearchTree();
        BST.insert(9,2,6,8,5,1,13,21,13,24,11,10);
        System.out.println("Displaying current tree:\n");
        BST.displayTree();
        System.out.println("The tree is " + (currentObject.isBalanced(BST.getRoot()) ? "balanced." : "unbalanced."));
        BST.insert(7);
        System.out.println("Displaying tree after inserting a new node(7):\n");
        BST.displayTree();
        System.out.println("The tree is " + (currentObject.isBalanced(BST.getRoot()) ? "balanced." : "unbalanced."));
    }
}
