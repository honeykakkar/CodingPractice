package DataStructures.Trees.BinarySearchTree;

import DataStructures.Trees.BinaryTree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 10/29/2016
 */
public class ArrayToBST {

    public BinaryTreeNode<Integer> arrayToBST(ArrayList<Integer> array) {
        Collections.sort(array);
        return arrayToBST(array, 0, array.size() - 1);
    }

    public BinaryTreeNode<Integer> arrayToBST(ArrayList<Integer> array, int lo, int hi) {
        if (lo > hi)
            return null;
        int mid = (hi - lo) / 2 + lo;
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(array.get(mid));
        root.left = arrayToBST(array, lo, mid - 1);
        root.right = arrayToBST(array, mid + 1, hi);
        return root;
    }

    public static void main(String[] args) {
        ArrayToBST BSTConverter = new ArrayToBST();
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 1; i <= 15; ++i)
            array.add(i * i);
        BinaryTreeNode<Integer> root = BSTConverter.arrayToBST(array);
        BinarySearchTree<Integer> convertedBST = new BinarySearchTree<>(root);
        convertedBST.displayTree();
    }
}
