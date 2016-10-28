package DataStructures.Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/19/2016
 */
public class BinarySearchTree {

    private BinaryTreeNode root;

    public BinarySearchTree(){
        this.root = null;
    }

    // To initialize a BST as a copy of already existing BST
    public BinarySearchTree(BinaryTreeNode root) {
        this.root = root;
    }

    // method to get the root node of BST
    public BinaryTreeNode getRoot() {
        return root;
    }

    // method to change the root node of BST
    public void setRoot(BinaryTreeNode root) {
        this.root = root;
    }

    // helper method to insert multiple nodes in the BST.
    public void insert(int... newData){
        for(int data : newData)
            this.root = insert(this.root, data);
    }

    // method to insert a node
    public BinaryTreeNode insert(BinaryTreeNode rootNode, int newData){
        if(rootNode == null)
            rootNode = new BinaryTreeNode(newData);
        else{
            if(newData < rootNode.data)
                rootNode.left = insert(rootNode.left, newData);
            else
                rootNode.right = insert(rootNode.right, newData);
        }

        // return the same root if unchanged
        return rootNode;
    }

    // method to delete a node from BST
    public void deleteNode(int deleteData){
        if(root == null)
            return;
        root = delete(root, deleteData);
    }

    // recursive method to delete a node from BST
    public BinaryTreeNode delete(BinaryTreeNode rootNode, int deleteData) {
        if (rootNode == null)
            return null;

        if (deleteData < rootNode.data)
            rootNode.left = delete(rootNode.left, deleteData);
        else {
            if (deleteData > rootNode.data)
                rootNode.right = delete(rootNode.right, deleteData);
            else {
                if (rootNode.left == null)
                    return rootNode.right;
                else {
                    if (rootNode.right == null)
                        return rootNode.left;

                    // when current node has both children. Find inorder successor and replace current node with its data
                    // Delete the inorder successor.
                    rootNode.data = getMinimumMaxValue(rootNode.right).data;
                    rootNode.right = delete(rootNode.right, rootNode.data);
                }
            }
        }
        return rootNode;
    }

    public void displayTree(){
        BinaryTreePrinter.print(this.root);
    }

    // method to search a node in BST with a given value
    public BinaryTreeNode searchNode(int value){
        BinaryTreeNode resultNode = null;
        BinaryTreeNode temp = this.root;

        while(temp != null){
            if(value < temp.data)
                temp = temp.left;
            else{
                if(value > temp.data)
                    temp = temp.right;
                else{
                    resultNode = temp;
                    break;
                }
            }
        }
        return resultNode;
    }

    // method to get all paths in the BST from its root node to its leaf nodes
    // Path list is shared during the traversal which maintains the visited nodes.
    // The current Current path is represented by path list from 0 to pathIndex. pathIndex represents the depth of node
    public void getPaths(BinaryTreeNode currentNode, ArrayList<ArrayList<Integer>> allPaths, ArrayList<Integer> path, int pathIndex) {
        if (currentNode == null)
            return;

        if (pathIndex < path.size() && path.get(pathIndex) != null)
            path.set(pathIndex, currentNode.data);
        else
            path.add(currentNode.data);
        ++pathIndex;

        if (currentNode.left == null && currentNode.right == null)
            pushPath(allPaths, path, pathIndex);
        else {
            getPaths(currentNode.left, allPaths, path, pathIndex);
            getPaths(currentNode.right, allPaths, path, pathIndex);
        }
    }

    // method to add current path to the list. Current path is represented by path list from 0 to pathIndex.
    public void pushPath(ArrayList<ArrayList<Integer>> allPaths, ArrayList<Integer> path, int pathIndex){
        ArrayList<Integer> currentPath = new ArrayList<>();
        for(int i=0; i<pathIndex; ++i)
            currentPath.add(path.get(i));
        allPaths.add(currentPath);
    }

    public void printPaths(ArrayList<ArrayList<Integer>> allPaths){
        for (ArrayList<Integer> path : allPaths){
            for (Integer nodeData : path)
                System.out.print(nodeData + " ");
            System.out.println();
        }
    }

    // method to get the height of a node in BST. Leaf being at 0 height
    public int getHeight(BinaryTreeNode currentNode){
        if(currentNode == null)
            return -1;
        return 1 + Math.max(getHeight(currentNode.left), getHeight(currentNode.right));
    }

    public int getDepth(BinaryTreeNode currentNode){
        if (this.root == null || currentNode == null)
            return -1;

        int depth = 0;
        BinaryTreeNode temp = root;
        while(temp != null){
            if(currentNode.data < temp.data)
                temp = temp.left;
            else {
                if (currentNode.data > temp.data)
                    temp = temp.right;
                else
                    break;
            }
            ++depth;
        }
        return depth;
    }

    public void levelOrderTraversal(){
        if(this.root == null)
            return;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            BinaryTreeNode head = queue.remove();
            if(head.left != null)
                queue.offer(head.left);
            if(head.right != null)
                queue.offer(head.right);
            System.out.print(head.data + " ");
        }
    }

    public void inOrderTraversal(BinaryTreeNode current){
        if(current == null)
            return;
        inOrderTraversal(current.left);
        System.out.print(current.data + " ");
        inOrderTraversal(current.right);
    }

    private BinaryTreeNode getMinimumMaxValue(BinaryTreeNode node){
        BinaryTreeNode temp = node;
        while(temp.left != null){
            temp = temp.left;
        }
        return temp;
    }

    private BinaryTreeNode getMaximumMinValue(BinaryTreeNode node){
        BinaryTreeNode temp = node;
        while(temp.right != null){
            temp = temp.right;
        }
        return temp;
    }

    public BinaryTreeNode getInorderPredecessor(BinaryTreeNode searchNode) {
        BinaryTreeNode inorderPredecessor = null;
        if(root == null || searchNode == null)
            return null;

        if(searchNode.left != null)
            return getMaximumMinValue(searchNode.left);

        BinaryTreeNode temp = root;
        while(temp != null){

            if(searchNode.data > temp.data){
                inorderPredecessor = temp;
                temp = temp.right;
            }
            else{
                if(searchNode.data < temp.data)
                    temp = temp.left;
                else
                    break;
            }
        }
        return inorderPredecessor;
    }

    public BinaryTreeNode getInorderSuccessor(BinaryTreeNode searchNode){
        BinaryTreeNode inorderSuccessor = null;
        if(root == null || searchNode == null)
            return null;

        if(searchNode.right != null)
            return getMinimumMaxValue(searchNode.right);

        BinaryTreeNode temp = root;
        while(temp != null){

            if(searchNode.data < temp.data){
                inorderSuccessor = temp;
                temp = temp.left;
            }
            else{
                if(searchNode.data > temp.data)
                    temp = temp.right;
                else
                    break;
            }
        }

        return inorderSuccessor;
    }

    public static void main(String[] args) {
        BinarySearchTree BST = new BinarySearchTree();
        BST.insert(9,2,6,8,3,1,10,21,13,24);
        System.out.println("Displaying current tree:\n");
        BST.displayTree();

        int nodeValue = 24;
        BinaryTreeNode node = BST.searchNode(nodeValue);
        System.out.println("The depth of node " + nodeValue + " is: " + BST.getDepth(node));
        System.out.println("The height of node " + nodeValue + " is: " + BST.getHeight(node));
        System.out.println("The inorder successor of node " + nodeValue + " is: " + BST.getInorderSuccessor(node));
        System.out.println("The inorder predecessor of node " + nodeValue + " is: " + BST.getInorderPredecessor(node));
        System.out.println();

        nodeValue = 10;
        node = BST.searchNode(nodeValue);
        System.out.println("The depth of node " + nodeValue + " is: " + BST.getDepth(node));
        System.out.println("The height of node " + nodeValue + " is: " + BST.getHeight(node));
        System.out.println("The inorder successor of node " + nodeValue + " is: " + BST.getInorderSuccessor(node));
        System.out.println("The inorder predecessor of node " + nodeValue + " is: " + BST.getInorderPredecessor(node));
        System.out.println();

        nodeValue = 6;
        node = BST.searchNode(nodeValue);
        System.out.println("The depth of node " + nodeValue + " is: " + BST.getDepth(node));
        System.out.println("The height of node " + nodeValue + " is: " + BST.getHeight(node));
        System.out.println("The inorder successor of node " + nodeValue + " is: " + BST.getInorderSuccessor(node));
        System.out.println("The inorder predecessor of node " + nodeValue + " is: " + BST.getInorderPredecessor(node));
        System.out.println();

        ArrayList<ArrayList<Integer>> allPaths = new ArrayList<>();
        BST.getPaths(BST.root, allPaths, new ArrayList<>(), 0);
        System.out.println("Displaying all paths from root to leaf nodes in the tree:");
        BST.printPaths(allPaths);
        System.out.println("\nHeight of the tree is: " + BST.getHeight(BST.root));

        BST.deleteNode(2);
        System.out.println("Displaying current tree:\n");
        BST.displayTree();

        BST.deleteNode(10);
        System.out.println("Displaying current tree:\n");
        BST.displayTree();
    }
}
