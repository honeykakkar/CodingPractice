package DataStructures.Graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 7/9/2016
 */

public class DepthFirstSearch {

    private void performDFS(Graph graph) {
        int connectedComponents = 0;
        HashMap<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex v : graph.getVertices())
            visited.put(v, false);
        System.out.print("\nComplete DFS of graph is: ");
        for (Vertex v : graph.getVertices()) {
            if (!visited.get(v)) {
                DFS(graph, v, visited); // To perform DFS on not-connected components of graph.
                ++connectedComponents; // Each call to this DFS represent a single connected component.
            }
        }
        System.out.println("\nTotal connected components: " + connectedComponents);
    }

    private void performDFS(Graph graph, Vertex source) {
        HashMap<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex v : graph.getVertices())
            visited.put(v, false);
        System.out.print("\nDFS of graph from " + source + " as source is: ");
        DFS(graph, source, visited);
    }

    void DFS(Graph graph, Vertex current, HashMap<Vertex, Boolean> visited) {
        visited.put(current, true);
        System.out.print(current + "  ");
        HashSet<Vertex> destNeighbours = graph.getAdjacencyList().get(current);
        for (Vertex v : destNeighbours) {
            if (!visited.get(v))
                DFS(graph, v, visited);
        }
    }

    private void performIterativeDFS(Graph graph, Vertex source) {
        Stack<Vertex> stack = new Stack<>();
        HashMap<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex v : graph.getVertices())
            visited.put(v, false);
        System.out.print("\nIterative DFS from " + source + " as source is:");

        stack.push(source);
        while (!stack.isEmpty()) {
            Vertex top = stack.pop();
            if (visited.get(top))
                continue;

            visited.put(top, true);
            System.out.print(top + "  ");
            stack.addAll(graph.getAdjacencyList().get(top));
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
        DepthFirstSearch currObj = new DepthFirstSearch();
        currObj.performDFS(testGraph, testGraph.getVertex("V2"));
        currObj.performDFS(testGraph);
        currObj.performIterativeDFS(testGraph, testGraph.getVertex("V2"));

        Graph testGraph1 = new Graph(GraphType.DIRECTED);
        testGraph1.addEdge("V5", "V0", 1);
        testGraph1.addEdge("V5", "V2", 1);
        testGraph1.addEdge("V4", "V0", 1);
        testGraph1.addEdge("V4", "V1", 1);
        testGraph1.addEdge("V2", "V3", 1);
        testGraph1.addEdge("V3", "V1", 1);
        System.out.print("\n\nDisplaying the adjacency list of graph:\n" + testGraph1.toString());
        currObj.performDFS(testGraph1, testGraph1.getVertex("V5"));
    }
}
