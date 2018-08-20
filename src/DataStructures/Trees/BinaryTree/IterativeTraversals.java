package DataStructures.Trees.BinaryTree;

import DataStructures.Trees.BinarySearchTree.BinarySearchTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 10/28/2016
 */

public class IterativeTraversals {

    private ArrayList<Integer> preOrder_Iterative(BinaryTreeNode<Integer> root) {
        ArrayList<Integer> traversal = new ArrayList<>();
        if (root == null)
            return traversal;

        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            BinaryTreeNode<Integer> top = stack.pop();
            traversal.add(top.data);
            if (top.right != null)
                stack.push(top.right);
            if (top.left != null)
                stack.push(top.left);
        }
        return traversal;
    }

    private ArrayList<Integer> inOrder_Iterative(BinaryTreeNode<Integer> root) {
        ArrayList<Integer> traversal = new ArrayList<>();
        if (root == null)
            return traversal;

        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        BinaryTreeNode<Integer> cur = root;
        stack.push(root);

        while (!stack.empty()) {
            while (cur.left != null) {
                stack.add(cur.left);
                cur = cur.left;
            }
            BinaryTreeNode<Integer> top = stack.pop();
            traversal.add(top.data);

            if (top.right != null) {
                stack.push(top.right);
                cur = top.right;
            }
        }
        return traversal;
    }

    // Pre-order is Root-Left-Right. Perform pre-order in Root-Right-Left fashion and reverse the traversal.
    // The resulting traversal would be Left-Right-Root which is post-order

    private ArrayList<Integer> postOrder_Iterative(BinaryTreeNode<Integer> root) {
        ArrayList<Integer> traversal = new ArrayList<>();
        if (root == null)
            return traversal;

        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            BinaryTreeNode<Integer> top = stack.pop();
            traversal.add(top.data);
            if (top.left != null)
                stack.push(top.left);
            if (top.right != null)
                stack.push(top.right);
        }
        Collections.reverse(traversal);
        return traversal;
    }

    // method to display the list of elements
    private <T> void display(ArrayList<T> array) {
        for (T element : array)
            System.out.print(element + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        BinarySearchTree<Integer> BST = new BinarySearchTree<>();
        BST.insert(9, 2, 6, 8, 3, 1, 10, 21, 13, 24);
        System.out.println("Displaying current tree:\n");
        BST.displayTree();

        IterativeTraversals iterativeWalker = new IterativeTraversals();
        ArrayList<Integer> traversal = iterativeWalker.inOrder_Iterative(BST.getRoot());
        System.out.println("In-order");
        iterativeWalker.display(traversal);
        System.out.println();

        traversal = iterativeWalker.preOrder_Iterative(BST.getRoot());
        System.out.println("Pre-order");
        iterativeWalker.display(traversal);
        System.out.println();

        traversal = iterativeWalker.postOrder_Iterative(BST.getRoot());
        System.out.println("Post-order");
        iterativeWalker.display(traversal);
        System.out.println();
    }
}
