package DataStructures.Graphs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/30/2016
 */

/*
A directed graph is strongly connected if there is a path between all pairs of vertices.
A strongly connected component (SCC) of a directed graph is a maximal strongly connected sub-graph.

Following class contains method to find SCCs in a directed graph.
 */

public class StronglyConnected {

    // Method to print all strongly connected components using Kosaraju’s algorithm.
    // The idea is to perform topological sort on the original graph
    // Then, transpose the graph, meaning reverse each edge of the graph
    // Then, perform DFS on transposed graph in topological order of vertices from the original graph.
    void getSCCs(Graph graph) {
        TopologicalSort topSorter = new TopologicalSort();
        ArrayList<Vertex> topSort = topSorter.getTopSort(graph);    // Perform topological sort
        System.out.println(topSort.toString());
        graph = graph.getTranspose();   // Get the transpose of the graph

        System.out.println("\nStrongly Connected Components in the given graph are:");
        DepthFirstSearch DFSPerformer = new DepthFirstSearch();
        HashMap<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex v : graph.getVertices())
            visited.put(v, false);
        for (Vertex v : topSort) {
            Vertex vertex = graph.getVertex(v.toString());
            if (!visited.get(vertex)) {
                DFSPerformer.DFS(graph, vertex, visited);   // Perform DFS on transposed graph
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        StronglyConnected SCCFinder = new StronglyConnected();
        Graph graph = new Graph(GraphType.DIRECTED);
        graph.addEdge("1", "0", 1);
        graph.addEdge("0", "2", 1);
        graph.addEdge("0", "3", 1);
        graph.addEdge("3", "4", 1);
        graph.addEdge("2", "1", 1);
        SCCFinder.getSCCs(graph);
    }
}