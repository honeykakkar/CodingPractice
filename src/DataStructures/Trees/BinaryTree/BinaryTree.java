package DataStructures.Trees.BinaryTree;

import java.util.*;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/19/2016
 */
public class BinaryTree {

    protected BinaryTreeNode root;

    public BinaryTree() {
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

    public void displayTree() {
        BinaryTreePrinter.print(root);
    }

    public void insert(int... newData) {
        for (int data : newData)
            insert(data);
    }

    // Inserting new nodes in a BFS fashion (filled from left to right, one level at a time).
    public void insert(int newData) {
        BinaryTreeNode newNode = new BinaryTreeNode(newData);
        if (root == null) {
            root = newNode;
            return;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (true) {
            BinaryTreeNode n = queue.remove();
            if (n.left == null) {
                n.left = newNode;
                break;
            } else
                queue.offer(n.left);

            if (n.right == null) {
                n.right = newNode;
                break;
            } else
                queue.offer(n.right);
        }
    }

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

    public void pushPath(ArrayList<ArrayList<Integer>> allPaths, ArrayList<Integer> path, int pathIndex) {
        ArrayList<Integer> currentPath = new ArrayList<>();
        for (int i = 0; i < pathIndex; ++i)
            currentPath.add(path.get(i));
        allPaths.add(currentPath);
    }

    public void printPaths(ArrayList<ArrayList<Integer>> allPaths) {
        for (ArrayList<Integer> path : allPaths) {
            for (Integer nodeData : path)
                System.out.print(nodeData + " ");
            System.out.println();
        }
    }

    public void levelOrderTraversal() {
        if (this.root == null)
            return;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode head = queue.remove();
            if (head.left != null)
                queue.offer(head.left);
            if (head.right != null)
                queue.offer(head.right);
            System.out.print(head.data + " ");
        }
    }

    public void inOrderTraversal(BinaryTreeNode current) {
        if (current == null)
            return;
        inOrderTraversal(current.left);
        System.out.print(current.data + " ");
        inOrderTraversal(current.right);
    }

    public BinaryTreeNode searchNode(BinaryTreeNode root, int value) {
        if (root == null)
            return null;

        if (root.data == value)
            return root;

        BinaryTreeNode temp = searchNode(root.left, value);
        if (temp != null)
            return temp;

        return searchNode(root.right, value);
    }

    public BinaryTreeNode lowestCommonAncestor_recursive(BinaryTreeNode root, BinaryTreeNode node1, BinaryTreeNode node2) {
        if (root == null || root == node1 || root == node2)
            return root;

        BinaryTreeNode left = lowestCommonAncestor_recursive(root.left, node1, node2);
        BinaryTreeNode right = lowestCommonAncestor_recursive(root.right, node1, node2);

        if (left == null)
            return right;
        else {
            if (right == null)
                return left;
        }
        return root;
    }

    public BinaryTreeNode lowestCommonAncestor_iterative(BinaryTreeNode node1, BinaryTreeNode node2) {
        if (this.root == null || node1 == null || node2 == null)
            return null;

        HashMap<BinaryTreeNode, BinaryTreeNode> parents = new HashMap<>();
        parents.put(root, null);

        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);

        while (!parents.containsKey(node1) || !parents.containsKey(node2)) {
            BinaryTreeNode popped = stack.pop();
            if (popped != null && popped.left != null) {
                stack.push(popped.left);
                parents.put(popped.left, popped);
            }

            if (popped != null && popped.right != null) {
                stack.push(popped.right);
                parents.put(popped.right, popped);
            }
        }

        Set<BinaryTreeNode> set = new HashSet<>();
        while (node1 != null) {
            set.add(node1);
            node1 = parents.get(node1);
        }

        while (node2 != null) {
            if (set.contains(node2))
                return node2;
            node2 = parents.get(node2);
        }
        return node2;
    }

    // method to count the number of nodes in sub-trees of a given node
    public int countChildren(BinaryTreeNode node) {
        if (node == null)
            return 0;

        int count = 0;
        if(node.left != null)
            count += countChildren(node.left) + 1;
        if(node.right != null)
            count += countChildren(node.right) + 1;

        return count;
    }

    // method to count the number of nodes which are complete (have both children)
    public int countCompleteNodes(BinaryTreeNode node){
        if (node == null)
            return 0;

        int count = 0;
        if(node.left != null && node.right != null)
            ++count;

        count += countCompleteNodes(node.left);
        count += countCompleteNodes(node.right);
        return count;
    }

    public int getDiameter(){
        if(root == null)
            return 0;
        return getDiameter(root);
    }

    public int getDiameter(BinaryTreeNode node){
        if(node == null)
            return 0;

        int leftHeight = getHeight(node.left) + 1;
        int rightHeight = getHeight(node.right) + 1;

        int leftDiameter = getDiameter(node.left);
        int rightDiameter = getDiameter(node.right);

        return Math.max(leftHeight + rightHeight + 1, Math.max(leftDiameter, rightDiameter));
    }

    // method to get the height of a node in BST. Leaf being at 0 height
    public int getHeight(BinaryTreeNode currentNode){
        if(currentNode == null)
            return -1;
        return 1 + Math.max(getHeight(currentNode.left), getHeight(currentNode.right));
    }

    // method to return an array with count of nodes at each level
    public ArrayList<Integer> getWidths(){
        ArrayList<Integer> widths = new ArrayList<>();
        if(this.root == null)
            return widths;
        getWidth(widths, root, 0);
        return widths;
    }

    public void displayWidths(){
        List<Integer> widths = getWidths();
        for(int i=0; i< widths.size(); ++i){
            System.out.println("Number of nodes at level " + i + ": " + widths.get(i));
        }
    }

    // method to count nodes at each level
    public void getWidth(ArrayList<Integer> widths, BinaryTreeNode node, int depth){
        if(node == null)
            return;
        if(depth >= widths.size())
            widths.add(1);
        else
            widths.set(depth, widths.get(depth) + 1);

        getWidth(widths, node.left, depth + 1);
        getWidth(widths, node.right, depth + 1);
    }

    public static void main(String[] args) {
        BinaryTree BT = new BinaryTree();
        BT.insert(9, 15, 7, 6, 4, 21, 8, 3);
        BT.displayTree();
        BT.levelOrderTraversal();
        System.out.println();
        BT.inOrderTraversal(BT.getRoot());
        BinaryTreeNode node1 = BT.searchNode(BT.getRoot(), 8);
        BinaryTreeNode node2 = BT.searchNode(BT.getRoot(), 3);
        System.out.println();

        BinaryTreeNode lca = BT.lowestCommonAncestor_recursive(BT.getRoot(), node1, node2);
        System.out.println(lca);
        System.out.println();

        lca = BT.lowestCommonAncestor_iterative(node1, node2);
        System.out.println(lca);

        System.out.println(BT.getDiameter());

    }
}
