package DataStructures.Trees.BinaryTree;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 10/28/2016
 */

// Morris traversal allows the traversal of a binary tree without recursion or stack.

public class MorrisTraversal {

    private void morrisTraversal(BinaryTreeNode<Integer> root) {
        BinaryTreeNode<Integer> current, iPredecessor;

        if (root == null)
            return;

        current = root;
        while (current != null) {
            if (current.left == null) {
                System.out.print(current.data + " ");
                current = current.right;
            } else {
                /* Find the inorder predecessor of current */
                iPredecessor = current.left;
                while (iPredecessor.right != null && iPredecessor.right != current)
                    iPredecessor = iPredecessor.right;

                /* Make current as right child of its inorder predecessor */
                if (iPredecessor.right == null) {
                    iPredecessor.right = current;
                    current = current.left;
                } 
  
                 /* Revert the changes made in if part to restore the 
                    original tree i.e.,fix the right child of predecssor*/
                else {
                    iPredecessor.right = null;
                    System.out.print(current.data + " ");
                    current = current.right;
                }   /* End of if condition iPredecessor->right == NULL */

            } /* End of if condition current->left == NULL*/
        } /* End of while */
    }

    public static void main(String[] args) {
        BinaryTree<Integer> BT = new BinaryTree<>();
        BT.insert(9, 15, 7, 6, 4, 21, 8, 3);
        BT.displayTree();

        MorrisTraversal walker = new MorrisTraversal();
        walker.morrisTraversal(BT.getRoot());
        System.out.println("\n");
        BT.displayTree();
    }
}
