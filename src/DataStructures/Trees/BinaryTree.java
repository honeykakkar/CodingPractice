package DataStructures.Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/19/2016
 */
public class BinaryTree {

    private BinaryTreeNode root;

    public BinaryTree(){
        this.root = null;
    }

    public BinaryTree(BinaryTreeNode root) {
        this.root = root;
    }

    public BinaryTreeNode getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode root) {
        this.root = root;
    }

    public void displayTree(){
        BinaryTreePrinter.print(this.root, 5);
    }

    public void insert(int... newData){
        for (int data : newData)
            insert(data);
    }

    // Inserting new nodes in a BFS fashion (filled from left to right, one level at a time).
    public void insert(int newData){
        BinaryTreeNode newNode = new BinaryTreeNode(newData);
        if(root == null) {
            root = newNode;
            return;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(true) {
            BinaryTreeNode n = queue.remove();
            if(n.left == null) {
                n.left = newNode;
                break;
            } else
                queue.offer(n.left);

            if(n.right == null) {
                n.right = newNode;
                break;
            } else
                queue.offer(n.right);
        }
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

    public static void main(String[] args) {
        BinaryTree BT = new BinaryTree();
        BT.insert(9, 15, 7, 6, 4, 21, 8, 3);
        BT.displayTree();
    }
}
