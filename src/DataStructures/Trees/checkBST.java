package DataStructures.Trees;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 7/13/2016
 */
public class checkBST {

    boolean isBST(BinaryTreeNode root, int min, int max){
        if(root == null)
            return true;
        if(root.data > min && root.data < max){
            if (isBST(root.left, min, root.data) && isBST(root.right, root.data, max))
                return true;
        }
        return false;
    }

    boolean isBST(BinaryTreeNode root){
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        BinaryTree BT = new BinaryTree();
        BT.insert(9, 5, 20, 3, 7, 15, 22, 2);
        checkBST currObject = new checkBST();
        BT.displayTree();
        long startTime = System.nanoTime();
        boolean result = currObject.isBST(BT.getRoot());
        long endTime = System.nanoTime();
        System.out.println("The given binary tree is " + (result ? "" : "not ") + "a binary search tree.");
        System.out.println("\nThe algorithm took " + (endTime - startTime) + " nanoseconds.");

    }
}
