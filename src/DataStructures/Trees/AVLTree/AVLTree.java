package DataStructures.Trees.AVLTree;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/7/2016
 */

/*
The AVL trees are more balanced compared to Red Black Trees,
but they may cause more rotations during insertion and deletion.

So if your application involves many frequent insertions and deletions,
then Red Black trees should be preferred.

And if the insertions and deletions are less frequent and search is more frequent operation,
then AVL tree should be preferred over Red Black Tree.
 */

public class AVLTree {

    AVLNode root;

    // A utility function to get height of the tree
    int height(AVLNode N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // A utility function to right rotate subtree rooted with y
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    int getBalance(AVLNode N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    void insert(int... values) {
        for (int data : values)
            insert(data);
    }

    void insert(int data) {
        this.root = insert(this.root, data);
    }

    AVLNode insert(AVLNode node, int data) {
 
        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return (new AVLNode(data));


        if (data < node.data)
            node.left = insert(node.left, data);
        else
            node.right = insert(node.right, data);
 
        /* 2. Update height of this ancestor node */
        node.height = 1 + Math.max(height(node.left),
                height(node.right));
 
        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases

        // Left Left Case
        if (balance > 1 && data < node.left.data)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && data > node.right.data)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);  // Performing left rotate on node's left node
            return rightRotate(node);   // Performing right rotate on the node itself
        }

        // Right Left Case
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);  // Performing right rotate on node's right node
            return leftRotate(node);    // Performing left rotate on the node itself
        }
 
        /* return the (unchanged) node pointer */
        return node;
    }

    private AVLNode getMinimumMaxValue(AVLNode node) {
        AVLNode temp = node;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    public void delete(int data) {
        if (root == null)
            return;

        this.root = deleteNode(this.root, data);
    }

    AVLNode deleteNode(AVLNode root, int data) {
        // Standard BST deletion
        if (root == null)
            return null;

        if (data < root.data)
            root.left = deleteNode(root.left, data);
        else {
            if (data > root.data)
                root.right = deleteNode(root.right, data);
            else {      // if node holding the deleteData has only one child
                if (root.left == null)
                    return root.right;
                else {
                    if (root.right == null)
                        return root.left;

                    // when current node has both children. Find inorder successor and replace current node with its data
                    // Delete the inorder successor.
                    root.data = getMinimumMaxValue(root.right).data;    // Copy inorder successor's value to root
                    root.right = deleteNode(root.right, root.data);     // Delete inorder successor
                }
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    void display() {
        AVLTreePrinter.print(this.root);
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.insert(1, 2, 3, 4, 5, 6, 7, 8);
        avlTree.display();

        avlTree.delete(6);
        avlTree.display();

        avlTree.delete(7);
        avlTree.display();

        avlTree.insert(6);
        avlTree.display();
    }
}
