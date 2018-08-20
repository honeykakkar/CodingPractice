package DataStructures.Trees.BinaryTree;

/*
 * Author: Honey Kakkar
 * Created on: January 05, 2017
 * Package: DataStructures.Trees.BinaryTree
 * Project: Coding Practice in JAVA
 */

/*
Given a binary tree where all the right nodes are either leaf nodes with a sibling
(a left node that shares the same parent node) or empty, flip it upside down and
turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5

return the root of the binary tree [4,5,2,#,#,3,1]
   4
  / \
 5   2
    / \
   3   1
 */

public class UpsideDown {

    private <T extends Comparable<T>> BinaryTreeNode<T> getUpsideDown(BinaryTreeNode<T> root) {
        if (root == null)
            return null;

        return upsideDown(root, null);
    }

    private <T extends Comparable<T>> BinaryTreeNode<T> upsideDown(BinaryTreeNode<T> node, BinaryTreeNode<T> parent) {
        if (node == null)
            return parent;

        // The root of the upside down tree lies at the leftmost position in the original tree.
        // Every recursive call returns the root i.e. the leftmost node.
        BinaryTreeNode<T> root = upsideDown(node.left, node);
        node.left = (parent == null) ? null : parent.right;
        node.right = parent;
        return root;
    }

    public static void main(String[] args) {
        BinaryTree<Integer> BT = new BinaryTree<>();
        BT.insert(1, 2, 3, 4, 5);
        BT.displayTree();

        UpsideDown upsideDown = new UpsideDown();
        BT.root = upsideDown.getUpsideDown(BT.root);
        BT.displayTree();
    }
}