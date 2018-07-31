package DataStructures.Graphs;

import java.util.HashMap;
import java.util.Stack;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 10/29/2016
 */

// Dijkstra's algorithm is an algorithm for finding the shortest paths between nodes in a graph
// with NON-NEGATIVE WEIGHTS

// For a given source node in the graph, the algorithm finds the shortest path between that node and every other.

// It can also be used for finding the shortest paths from a single node to a single destination node by
// stopping the algorithm once the shortest path to the destination node has been determined.

public class Dijkstra {

    private final HashMap<Vertex, Vertex> parents = new HashMap<>();
    private final HashMap<Vertex, Integer> distances = new HashMap<>();
    private final HashMap<Vertex, Boolean> visited = new HashMap<>();
    private final Graph graph;
    private final Vertex source;

    private Dijkstra(Graph graph, Vertex source) {
        this.graph = graph;
        this.source = source;
        for (Vertex vertex : graph.getVertices()) {
            visited.put(vertex, false);
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(source, 0);
        parents.put(source, null);
    }

    // method to find the vertex with minimum key value, from the set of vertices not yet visited
    private Vertex findMinKeyVertex() {
        int min = Integer.MAX_VALUE;
        Vertex minVertex = null;
        for (Vertex vertex : visited.keySet()) {
            if (!visited.get(vertex) && distances.get(vertex) < min) {
                min = distances.get(vertex);
                minVertex = vertex;
            }
        }
        return minVertex;
    }

    private void findShortestPaths() {

        for (int i = 0; i < graph.getVertices().size() - 1; ++i) {
            Vertex current = findMinKeyVertex();
            visited.put(current, true);

            for (Vertex neighbour : graph.getAdjacencyList().get(current)) {
                Edge edge = graph.getEdge(current, neighbour);
                int edgeWeight = edge.getWeight();
                int prevValue = distances.get(neighbour);

                if (distances.get(current) != Integer.MAX_VALUE &&
                        !visited.get(neighbour) && edgeWeight + distances.get(current) < prevValue) {
                    distances.put(neighbour, edgeWeight + distances.get(current));
                    parents.put(neighbour, current);
                }
            }
        }
    }


    private void printDistances() {
        for (Vertex V : distances.keySet())
            System.out.println("The shortest distance from vertex " + source + " as source to vertex " + V + " : " +
                    (distances.get(V) == Integer.MAX_VALUE ? "Can't reach" : distances.get(V)));
        System.out.println();
    }

    private void printPaths() {
        for (Vertex V : distances.keySet()) {
            Stack<Vertex> st = new Stack<>();
            while (parents.get(V) != null) {
                st.push(V);
                V = parents.get(V);
            }

            if (distances.get(V) != Integer.MAX_VALUE)
                st.push(V);

            System.out.print("The shortest path from vertex " + source + " as source to vertex " + st.get(0) + " : ");
            while (!st.isEmpty())
                System.out.print(st.pop() + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
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
        Vertex source = testGraph.getVertex("5");

        Dijkstra currObject = new Dijkstra(testGraph, source);
        long startTime = System.nanoTime();
        currObject.findShortestPaths();
        long endTime = System.nanoTime();
        System.out.println("The algorithm took " + (endTime - startTime) + " nanoseconds.\n");
        currObject.printDistances();
        currObject.printPaths();
    }
}
