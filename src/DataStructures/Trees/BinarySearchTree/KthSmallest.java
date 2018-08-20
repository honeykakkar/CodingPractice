package DataStructures.Trees.BinarySearchTree;

import DataStructures.Trees.BinaryTree.BinaryTreeNode;

import java.util.Stack;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/15/2016
 */
public class KthSmallest {

    private static int number_rec = 0;
    private static int count = 0;
    private static int number_itr = 0;

    private int kthSmallest_rec(BinaryTreeNode<Integer> root, int k) {
        count = k;
        helper(root);
        return number_rec;
    }

    private void helper(BinaryTreeNode<Integer> n) {
        if (n.left != null)
            helper(n.left);
        count--;

        if (count == 0) {
            number_rec = n.data;
            return;
        }

        if (n.right != null)
            helper(n.right);
    }

    private void kthSmallest_iter(BinaryTreeNode<Integer> root, int k) {
        if (root == null)
            return;

        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        BinaryTreeNode<Integer> current = root;

        while (current != null || !stack.empty()) {

            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            --k;
            if (k == 0) {
                number_itr = current.data;
                return;
            }
            current = current.right;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> BST = new BinarySearchTree<>();
        BST.insert(9, 2, 6, 8, 3, 1, 10, 21, 13, 24);
        System.out.println("Displaying current tree:\n");
        BST.displayTree();

        KthSmallest kthSmallest = new KthSmallest();
        int kth = kthSmallest.kthSmallest_rec(BST.getRoot(), 1);
        System.out.println(kth);

        kthSmallest.kthSmallest_iter(BST.getRoot(), 1);
        System.out.println(number_itr);
    }
}
