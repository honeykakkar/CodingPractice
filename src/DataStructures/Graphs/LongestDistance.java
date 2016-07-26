package DataStructures.Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 7/14/2016
 */

// The following algorithm calculates the longest distance from
// a given vertex as source to all other vertices in the graph.

public class LongestDistance {

    void topologicalSort(Graph graph, Vertex current, HashMap<Vertex, Boolean> visited, Stack<Vertex> stack){
        visited.put(current, true);
        for (Vertex neighbour : graph.getAdjacencyList().get(current)){
            if(!visited.get(neighbour))
                topologicalSort(graph, neighbour, visited, stack);
        }
        stack.push(current);
    }

    // performing topological sort using DFS approach
    ArrayList<Vertex> performTopSort(Graph graph) {
        Stack<Vertex> stack = new Stack<>();
        HashMap<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex v : graph.getVertices())
            visited.put(v, false);
        for (Vertex v : graph.getVertices()) {
            if (!visited.get(v))
                topologicalSort(graph, v, visited, stack);
        }
        ArrayList<Vertex> result = new ArrayList<>();
        for (int i = stack.size()-1; i>=0; --i)
            result.add(stack.get(i));
        return result;
    }

    HashMap<Vertex, Integer> findLongestPath(Graph graph, Vertex source){
        ArrayList<Vertex> topSort = performTopSort(graph);
        HashMap<Vertex, Integer> distances = new HashMap<>();
        for (Vertex V : graph.getVertices())
            distances.put(V, (V==source)? 0 : Integer.MIN_VALUE);
        int src = topSort.indexOf(source);
        for(int i=src; i<topSort.size(); ++i){
            Vertex current = topSort.get(i);
            for(Vertex neighbour : graph.getAdjacencyList().get(current)){
                Edge edge = graph.getEdge(current, neighbour);
                if(edge != null){
                    int prevValue = distances.get(neighbour);
                    if(distances.get(current) + edge.getWeight() > prevValue){
                        distances.put(neighbour, distances.get(current) + edge.getWeight());
                    }
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        Graph testGraph = new Graph(GraphType.DIRECTED);
        testGraph.addEdge("0", "1", 7);
        testGraph.addEdge("0", "4", 4);
        testGraph.addEdge("0", "3", 2);
        testGraph.addEdge("1", "3", 1);
        testGraph.addEdge("1", "4", -1);
        testGraph.addEdge("2", "0", 2);
        testGraph.addEdge("2", "1", 6);
        testGraph.addEdge("4", "3", -2);
        testGraph.addEdge("5", "0", 3);
        testGraph.addEdge("5", "2", 5);
        System.out.println(testGraph.toString());
        LongestDistance currObject = new LongestDistance();
        Vertex source = testGraph.getVertex("5");
        long startTime = System.nanoTime();
        HashMap<Vertex, Integer> distances = currObject.findLongestPath(testGraph, source);
        long endTime = System.nanoTime();
        for (Vertex V : distances.keySet())
            System.out.println("The longest distance from vertex " + source + " as source to vertex " + V + " : " +
                    (distances.get(V) == Integer.MIN_VALUE ? "Can't reach" : distances.get(V)));
        System.out.println("\nThe algorithm took " + (endTime - startTime) + " nanoseconds.");

        Graph testGraph1 = new Graph(GraphType.UNDIRECTED);
        testGraph1.addEdge("0", "1", 7);
        testGraph1.addEdge("0", "4", 4);
        testGraph1.addEdge("0", "3", 2);
        testGraph1.addEdge("1", "3", 1);
        testGraph1.addEdge("1", "4", -1);
        testGraph1.addEdge("2", "0", 2);
        testGraph1.addEdge("2", "1", 6);
        testGraph1.addEdge("4", "3", -2);
        testGraph1.addEdge("5", "0", 3);
        testGraph1.addEdge("5", "2", 5);
        System.out.println(testGraph.toString());
        currObject = new LongestDistance();
        source = testGraph.getVertex("5");
        startTime = System.nanoTime();
        distances = currObject.findLongestPath(testGraph, source);
        endTime = System.nanoTime();
        for (Vertex V : distances.keySet())
            System.out.println("The longest distance from vertex " + source + " as source to vertex " + V + " : " +
                    (distances.get(V) == Integer.MIN_VALUE ? "Can't reach" : distances.get(V)));
        System.out.println("\nThe algorithm took " + (endTime - startTime) + " nanoseconds.");
    }
}
