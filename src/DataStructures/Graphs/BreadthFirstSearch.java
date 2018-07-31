package DataStructures.Graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 7/9/2016
 */

public class BreadthFirstSearch {

    private void performBFS(Graph graph) {
        HashMap<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex v : graph.getVertices())
            visited.put(v, false);
        System.out.print("\nComplete BFS of graph is: ");
        for (Vertex v : graph.getVertices()) {
            if (!visited.get(v))
                BFS(graph, v, visited);
        }
    }

    private void performBFS(Graph graph, Vertex source) {
        HashMap<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex v : graph.getVertices())
            visited.put(v, false);
        System.out.print("\nIterative BFS of graph from " + source + " as source is: ");
        BFS(graph, source, visited);
    }


    private void BFS(Graph graph, Vertex source, HashMap<Vertex, Boolean> visited) {
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            Vertex head = queue.poll();
            if (visited.get(head))
                continue;

            visited.put(head, true);
            System.out.print(head + "  ");
            queue.addAll(graph.getAdjacencyList().get(head));
        }
    }

    public static void main(String[] args) {
        Graph testGraph = new Graph(GraphType.DIRECTED);
        testGraph.addEdge("V0", "V1", 1);
        testGraph.addEdge("V0", "V2", 1);
        testGraph.addEdge("V1", "V2", 1);
        testGraph.addEdge("V2", "V0", 1);
        testGraph.addEdge("V2", "V3", 1);
        testGraph.addEdge("V3", "V3", 1);
        System.out.print("Displaying the adjacency list of graph:\n" + testGraph.toString());
        BreadthFirstSearch currObj = new BreadthFirstSearch();
        currObj.performBFS(testGraph, testGraph.getVertex("V2"));
        currObj.performBFS(testGraph);
    }
}
