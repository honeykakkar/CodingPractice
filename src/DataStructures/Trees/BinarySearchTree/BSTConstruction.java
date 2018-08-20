package DataStructures.Trees.BinarySearchTree;

import DataStructures.Trees.BinaryTree.BinaryTreeNode;

import java.util.HashMap;
import java.util.Stack;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/16/2016
 */

public class BSTConstruction {

    private BinarySearchTree<Integer> preOrder_InOrder(int[] preOrder, int[] inOrder) {

        HashMap<Integer, Integer> inOrderMap = new HashMap<>(inOrder.length);
        for (int i = 0; i < inOrder.length; ++i)
            inOrderMap.put(inOrder[i], i);

        BinaryTreeNode<Integer> root = preInorderBuild(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1, inOrderMap);
        getPostOrder(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1, inOrderMap);
        System.out.println("\n");
        return new BinarySearchTree<>(root);
    }

    // Method to display the post order from in-order and pre-order
    private void getPostOrder(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd, HashMap<Integer, Integer> inOrderMap) {
        if (preStart > preEnd || inStart > inEnd)
            return;

        int root = preOrder[preStart];
        int inRoot = inOrderMap.get(root);
        int leftChildren = inRoot - inStart;

        getPostOrder(preOrder, preStart + 1, preStart + leftChildren, inOrder, inStart, inRoot - 1, inOrderMap);
        getPostOrder(preOrder, preStart + leftChildren + 1, preEnd, inOrder, inRoot + 1, inEnd, inOrderMap);
        System.out.print(root + " ");
    }

    private BinaryTreeNode<Integer> preInorderBuild(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd, HashMap<Integer, Integer> inOrderMap) {
        if (preStart > preEnd || inStart > inEnd)
            return null;

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(preOrder[preStart]);
        int inRoot = inOrderMap.get(root.data);
        int leftChildren = inRoot - inStart;

        root.left = preInorderBuild(preOrder, preStart + 1, preStart + leftChildren, inOrder, inStart, inRoot - 1, inOrderMap);
        root.right = preInorderBuild(preOrder, preStart + leftChildren + 1, preEnd, inOrder, inRoot + 1, inEnd, inOrderMap);

        return root;
    }


    private BinarySearchTree<Integer> postOrder_InOrder(int[] postOrder, int[] inOrder) {

        HashMap<Integer, Integer> inOrderMap = new HashMap<>(inOrder.length);
        for (int i = 0; i < inOrder.length; ++i)
            inOrderMap.put(inOrder[i], i);

        BinaryTreeNode<Integer> root = postInorderBuild(postOrder, 0, postOrder.length - 1, inOrder, 0, inOrder.length - 1, inOrderMap);
        return new BinarySearchTree<>(root);
    }

    private BinaryTreeNode<Integer> postInorderBuild(int[] postOrder, int postStart, int postEnd, int[] inOrder, int inStart, int inEnd, HashMap<Integer, Integer> inOrderMap) {
        if (postStart > postEnd || inStart > inEnd)
            return null;

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(postOrder[postEnd]);
        int inRoot = inOrderMap.get(root.data);
        int leftChildren = inRoot - inStart;

        root.left = postInorderBuild(postOrder, postStart, postStart + leftChildren - 1, inOrder, inStart, inRoot - 1, inOrderMap);
        root.right = postInorderBuild(postOrder, postStart + leftChildren, postEnd - 1, inOrder, inRoot + 1, inEnd, inOrderMap);

        return root;
    }

    // Method to build a BST from its pre-order
    private BinarySearchTree<Integer> preOrder(int[] preOrder) {
        if (preOrder == null || preOrder.length == 0)
            return null;

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(preOrder[0]);
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        int index = 1;
        while (index < preOrder.length) {

            BinaryTreeNode temp = null;

            // If preOrder[index] is greater than stack top, keep popping elements from stack
            // to find the parent of the element. Its parent would be the first element which is lesser to it.
            while (!stack.empty() && preOrder[index] > stack.peek().data) {
                temp = stack.pop();
            }

            // It means that preOrder[index] is not greater than the stack top
            if (temp != null) {
                BinaryTreeNode<Integer> rightChild = new BinaryTreeNode<>(preOrder[index]);
                temp.right = rightChild;
                stack.push(rightChild);
            } else {
                BinaryTreeNode<Integer> leftChild = new BinaryTreeNode<>(preOrder[index]);
                stack.peek().left = leftChild;
                stack.push(leftChild);
            }
            ++index;
        }
        return new BinarySearchTree<>(root);
    }

    public static void main(String[] args) {
        int[] inOrder = new int[]{1, 2, 3, 6, 8, 9, 10, 13, 21, 22, 23, 24};
        int[] preOrder = new int[]{9, 2, 1, 6, 3, 8, 10, 21, 13, 24, 23, 22};
        int[] postOrder = new int[]{1, 3, 8, 6, 2, 13, 22, 23, 24, 21, 10, 9};

        BSTConstruction bstConstructor = new BSTConstruction();
        BinarySearchTree BST = bstConstructor.preOrder_InOrder(preOrder, inOrder);
        BST.displayTree();
        System.out.println();

        BST = bstConstructor.postOrder_InOrder(postOrder, inOrder);
        BST.displayTree();
        System.out.println();

        BST = bstConstructor.preOrder(preOrder);
        BST.displayTree();
        System.out.println();
    }
}
