package DataStructures.Trees.BinarySearchTree;

import java.util.Stack;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/18/2016
 */
public class ValidPreOrder {

    private boolean isValidPreOrderBST(int[] preOrder) {

        Stack<Integer> s = new Stack<>();
        int root = Integer.MIN_VALUE;

        for (int aPreOrder : preOrder) {
            if (aPreOrder < root) {
                return false;
            }

            while (!s.empty() && s.peek() < aPreOrder) {
                root = s.pop();
            }
            s.push(aPreOrder);
        }
        return true;
    }

    //If we treat null's as leaves, then the binary tree will always be full.
    // A full binary tree has a good property that # of leaves = # of nonleaves + 1.
    // Since we are given a pre-order serialization, we just need to find the shortest prefix
    // of the sequence satisfying the property above.

    // If such prefix does not exist, then the serialization is definitely invalid;
    // otherwise, the serialization is valid if and only if the prefix is the entire sequence.

    private boolean isValidPreOrderBT(String preOrder) {
        int nonLeaves = 0, leaves = 0, i;
        String[] nodes = preOrder.split(",");
        for (i = 0; i < nodes.length && nonLeaves + 1 != leaves; i++)
            if (nodes[i].equals("#"))
                leaves++;
            else
                nonLeaves++;

        return nonLeaves + 1 == leaves && i == nodes.length;
    }

    public static void main(String[] args) {
        int[] preOrder = new int[]{9, 2, 1, 6, 3, 8, 10, 21, 13, 24, 23, 22};
        ValidPreOrder checker = new ValidPreOrder();
        boolean result = checker.isValidPreOrderBST(preOrder);
        System.out.println(result);

        preOrder = new int[]{40, 30, 35, 20, 80, 100};
        result = checker.isValidPreOrderBST(preOrder);
        System.out.println(result);

        String preOrderBT = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        result = checker.isValidPreOrderBT(preOrderBT);
        System.out.println(result);

        preOrderBT = "9,#,#,1";
        result = checker.isValidPreOrderBT(preOrderBT);
        System.out.println(result);
    }
}
