package DataStructures.Trees;

import java.util.ArrayList;

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

    public BinarySearchTree(BinaryTreeNode root) {
        this.root = root;
    }

    public BinaryTreeNode getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode root) {
        this.root = root;
    }

    public void insert(int... newData){
        for(int data : newData)
            this.root = insert(this.root, data);
    }

    public BinaryTreeNode insert(BinaryTreeNode currentNode, int newData){
        if(currentNode == null)
            currentNode = new BinaryTreeNode(newData);
        else{
            if(newData < currentNode.data)
                currentNode.left = insert(currentNode.left, newData);
            else
                currentNode.right = insert(currentNode.right, newData);
        }
        return currentNode;
    }

    public void displayTree(){
        BinaryTreePrinter.print(this.root, 5);
    }

    public void getPaths(BinaryTreeNode currentNode, ArrayList<ArrayList<Integer>> allPaths, ArrayList<Integer> path, int pathIndex){
        if(currentNode == null)
            return;

        try{
            if(path.get(pathIndex) != null)
                path.set(pathIndex, currentNode.data);
        }
        catch (IndexOutOfBoundsException E){
            path.add(pathIndex, currentNode.data);
        }
        ++pathIndex;

        if(currentNode.left == null && currentNode.right == null)
            pushPath(allPaths, path, pathIndex);
        else{
            getPaths(currentNode.left, allPaths, path, pathIndex);
            getPaths(currentNode.right, allPaths, path, pathIndex);
        }
    }

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

    public int getHeight(BinaryTreeNode currentNode){
        if(currentNode == null)
            return -1;
        return 1 + Math.max(getHeight(currentNode.left), getHeight(currentNode.right));
    }

    public static void main(String[] args) {
        BinarySearchTree BST = new BinarySearchTree();
        BST.insert(9,2,6,8,3,1,10,21,13,24);
        System.out.println("Displaying current tree:\n");
        BST.displayTree();
        ArrayList<ArrayList<Integer>> allPaths = new ArrayList<>();
        BST.getPaths(BST.root, allPaths, new ArrayList<>(), 0);
        System.out.println("Displaying all paths from root to leaf nodes in the tree:");
        BST.printPaths(allPaths);
        System.out.println("\nHeight of the tree is: " + BST.getHeight(BST.root));
    }
}
