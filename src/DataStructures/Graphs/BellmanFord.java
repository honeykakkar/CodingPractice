package DataStructures.Graphs;

import java.util.HashMap;
import java.util.Stack;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 7/14/2016
 */

//Unlike Dijkstra's algorithm, the Bellman–Ford algorithm can be used on graphs with negative edge weights,
// as long as the graph contains no negative cycle reachable from the source vertex s.

// Now in order to check if there is no negative cycle we have to perform the Nth iteration.
// If there is no change in the value of distances map in the Nth loop then
// the graph has no negative cycle which means we can find the shortest path.
// Otherwise the graph has negative cycle and we cannot find the shortest path

// Complexity : |V|*|E|
public class BellmanFord {

    private final HashMap<Vertex, Integer> distances = new HashMap<>();
    private final HashMap<Vertex, Vertex> parents = new HashMap<>();
    private final Graph graph;
    private final Vertex source;

    private BellmanFord(Graph graph, Vertex source) {
        this.graph = graph;
        this.source = source;

        for (Vertex V : graph.getVertices())
            distances.put(V, (V == source) ? 0 : Integer.MAX_VALUE);
    }

    private void findShortestPath() {
        for (int i = 0; i < graph.getVertices().size() - 1; ++i) {

            for (Edge edge : graph.getEdges()) {
                if (edge != null) {
                    Vertex source = edge.getSource();
                    Vertex destination = edge.getDestination();
                    int prevValue = distances.get(destination);

                    // Two ways to reach to neighbour: current + (current-neighbour), OR directly to neighbour.
                    // Compare them both. Whichever is longer, pick that one.
                    if (distances.get(source) != Integer.MAX_VALUE &&
                            distances.get(source) + edge.getWeight() < prevValue) {
                        distances.put(destination, distances.get(source) + edge.getWeight());
                        parents.put(destination, source);
                    }
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
        testGraph.addEdge("S", "A", 10);
        testGraph.addEdge("S", "E", 8);
        testGraph.addEdge("A", "C", 2);
        testGraph.addEdge("B", "A", 1);
        testGraph.addEdge("C", "B", -2);
        testGraph.addEdge("D", "A", -4);
        testGraph.addEdge("D", "C", -1);
        testGraph.addEdge("E", "D", 1);
        System.out.println(testGraph.toString());
        Vertex source = testGraph.getVertex("S");
        BellmanFord currObject = new BellmanFord(testGraph, source);
        long startTime = System.nanoTime();
        currObject.findShortestPath();
        long endTime = System.nanoTime();
        System.out.println("The algorithm took " + (endTime - startTime) + " nanoseconds.\n");
        currObject.printDistances();
        currObject.printPaths();
    }
}
