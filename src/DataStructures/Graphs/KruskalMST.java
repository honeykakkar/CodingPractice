package DataStructures.Graphs;

import DataStructures.Graphs.UnionFindAlgo.DisjointSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 10/29/2016
 */

// A spanning tree of a graph is a subgraph that is a tree and connects all the vertices together.

// A  single graph can have many different spanning trees.
// A minimum spanning tree (MST) or minimum weight spanning tree for a weighted, connected and undirected graph is
// a spanning tree with weight less than or equal to the weight of every other spanning tree.
// The weight of a spanning tree is the sum of weights given to each edge of the spanning tree.
// If a graph's edges all have distinct weights, the MST is unique.

// To find a maximum spanning tree of an edge-weighted graph, have a list of edges in the decreasing order of weights.

public class KruskalMST {

    ArrayList<Edge> findMST(Graph graph){
        DisjointSet disjointSet = new DisjointSet(graph);
        HashSet<Edge> edges = graph.getEdges();
        List<Edge> sortedEdges = new ArrayList<>();
        sortedEdges.addAll(edges);
        // Have a collection of edges in the increasing order of their weights
        Collections.sort(sortedEdges);

        ArrayList<Edge> minSpanTree = new ArrayList<>();
        // Pick each edge one by one and check if it makes a cycle or not
        for (Edge edge : sortedEdges) {
            Vertex source = edge.getSource();
            Vertex destination = edge.getDestination();

            // If it doesn't make a cycle, add the edge to the list of minSpanTree and put both vertices in the DisJoint set
            if (!disjointSet.isConnected(source, destination)) {
                minSpanTree.add(edge);
                disjointSet.union(source, destination);
            }
        }
        return minSpanTree;
    }

    // method to displayMSTEdges the list of elements
    public <T> void displayMSTEdges(ArrayList<T> array){
        for (T element : array)
            System.out.println(element + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        KruskalMST kruskalMST = new KruskalMST();
        Graph testGraph = new Graph(GraphType.DIRECTED);
        testGraph.addEdge("0", "1", 7);
        testGraph.addEdge("0", "4", 4);
        testGraph.addEdge("0", "3", 2);
        testGraph.addEdge("1", "3", 1);
        testGraph.addEdge("1", "4", 1);
        testGraph.addEdge("2", "0", 2);
        testGraph.addEdge("2", "1", 6);
        testGraph.addEdge("4", "3", 2);
        testGraph.addEdge("5", "0", 3);
        testGraph.addEdge("5", "2", 5);
        System.out.println(testGraph.toString());
        kruskalMST.displayMSTEdges(kruskalMST.findMST(testGraph));

        Graph testGraph1 = new Graph(GraphType.UNDIRECTED);
        testGraph1.addEdge("A", "B", 2);
        testGraph1.addEdge("A", "C", 3);
        testGraph1.addEdge("A", "D", 3);
        testGraph1.addEdge("B", "C", 4);
        testGraph1.addEdge("B", "E", 3);
        testGraph1.addEdge("C", "E", 1);
        testGraph1.addEdge("C", "D", 5);
        testGraph1.addEdge("D", "F", 7);
        testGraph1.addEdge("E", "F", 8);
        testGraph1.addEdge("F", "G", 9);
        testGraph1.addEdge("A", "B", 2);
        System.out.println(testGraph1);
        kruskalMST.displayMSTEdges(kruskalMST.findMST(testGraph1));

        Graph testGraph2 = new Graph(GraphType.UNDIRECTED);
        testGraph2.addEdge("0", "1", 4);
        testGraph2.addEdge("0", "7", 8);
        testGraph2.addEdge("1", "2", 8);
        testGraph2.addEdge("1", "7", 11);
        testGraph2.addEdge("2", "3", 7);
        testGraph2.addEdge("2", "5", 4);
        testGraph2.addEdge("2", "8", 2);
        testGraph2.addEdge("3", "4", 9);
        testGraph2.addEdge("3", "5", 14);
        testGraph2.addEdge("5", "4", 10);
        testGraph2.addEdge("7", "6", 1);
        testGraph2.addEdge("7", "8", 7);
        testGraph2.addEdge("6", "5", 2);
        testGraph2.addEdge("6", "8", 6);
        System.out.println(testGraph2.toString());
        kruskalMST.displayMSTEdges(kruskalMST.findMST(testGraph2));
    }
}

