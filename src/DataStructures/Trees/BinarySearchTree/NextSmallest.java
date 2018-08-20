package DataStructures.Trees.BinarySearchTree;

import DataStructures.Trees.BinaryTree.BinaryTreeNode;

import java.util.Stack;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/15/2016
 */

// Equivalent to inorder iterative traversal.

public class NextSmallest {

    private final Stack<BinaryTreeNode<Integer>> stack = new Stack<>();

    private NextSmallest(BinaryTreeNode<Integer> root) {
        pushAll(root);
    }

    /* returns whether we have a next smallest number */
    private boolean hasNext() {
        return !stack.isEmpty();
    }

    /* returns the next smallest number */
    private int next() {
        BinaryTreeNode<Integer> tmpNode = stack.pop();
        pushAll(tmpNode.right);
        return tmpNode.data;
    }

    private void pushAll(BinaryTreeNode<Integer> node) {
        for (; node != null; node = node.left)
            stack.push(node);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> BST = new BinarySearchTree<>();
        BST.insert(9, 2, 6, 8, 3, 1, 10, 21, 13, 24, 23, 22);
        System.out.println("Displaying current tree:\n");
        BST.displayTree();

        NextSmallest nextSmallest = new NextSmallest(BST.getRoot());
        while (nextSmallest.hasNext()) {
            System.out.println(nextSmallest.next());
        }
    }
}
