package DataStructures.Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 7/14/2016
 */

// The following algorithm calculates the longest distance from
// a given vertex as source to all other vertices in the graph.

public class LongestDistance {

    private final HashMap<Vertex, Integer> distances = new HashMap<>();
    private final HashMap<Vertex, Vertex> parents = new HashMap<>();
    private final Vertex source;

    private LongestDistance(Vertex source) {
        this.source = source;
    }

    private void topologicalSort(Graph graph, Vertex current, HashMap<Vertex, Boolean> visited, Stack<Vertex> stack) {
        visited.put(current, true);
        for (Vertex neighbour : graph.getAdjacencyList().get(current)) {
            if (!visited.get(neighbour))
                topologicalSort(graph, neighbour, visited, stack);
        }
        stack.push(current);
    }

    // performing topological sort using DFS approach
    private ArrayList<Vertex> performTopSort(Graph graph) {
        Stack<Vertex> stack = new Stack<>();
        HashMap<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex v : graph.getVertices())
            visited.put(v, false);
        for (Vertex v : graph.getVertices()) {
            if (!visited.get(v))
                topologicalSort(graph, v, visited, stack);
        }
        ArrayList<Vertex> result = new ArrayList<>();
        for (int i = stack.size() - 1; i >= 0; --i)
            result.add(stack.get(i));
        return result;
    }

    private void findLongestPath(Graph graph) {
        ArrayList<Vertex> topSort = performTopSort(graph);

        for (Vertex V : graph.getVertices())
            distances.put(V, (V == source) ? 0 : Integer.MIN_VALUE);
        int src = topSort.indexOf(source);
        for (int i = src; i < topSort.size(); ++i) {
            Vertex current = topSort.get(i);
            for (Vertex neighbour : graph.getAdjacencyList().get(current)) {
                Edge edge = graph.getEdge(current, neighbour);
                if (edge != null) {
                    int prevValue = distances.get(neighbour);

                    // Two ways to reach to neighbour: current + (current-neighbour), OR directly to neighbour.
                    // Compare them both. Whichever is longer, pick that one.

                    if (distances.get(current) + edge.getWeight() > prevValue) {
                        distances.put(neighbour, distances.get(current) + edge.getWeight());
                        parents.put(neighbour, current);
                    }
                }
            }
        }
    }

    private void printDistances() {
        for (Vertex V : distances.keySet())
            System.out.println("The longest distance from vertex " + source + " as source to vertex " + V + " : " +
                    (distances.get(V) == Integer.MIN_VALUE ? "Can't reach" : distances.get(V)));
        System.out.println();
    }

    private void printPaths() {
        for (Vertex V : distances.keySet()) {
            Stack<Vertex> st = new Stack<>();
            while (parents.get(V) != null) {
                st.push(V);
                V = parents.get(V);
            }

            if (distances.get(V) != Integer.MIN_VALUE)
                st.push(V);

            System.out.print("The longest path from vertex " + source + " as source to vertex " + st.get(0) + " : ");
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
        LongestDistance currObject = new LongestDistance(source);
        long startTime = System.nanoTime();
        currObject.findLongestPath(testGraph);
        long endTime = System.nanoTime();
        System.out.println("The algorithm took " + (endTime - startTime) + " nanoseconds.\n");
        currObject.printDistances();
        currObject.printPaths();
    }
}
