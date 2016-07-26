package DataStructures.Graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 7/15/2016
 */
public class BipartiteGraph {

    boolean isBipartite(Graph graph, Vertex source){
        HashMap<Vertex, String> labels = new HashMap<>();
        for (Vertex V : graph.getVertices())
            labels.put(V, "Black");
        labels.put(source, "White");
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(source);
        while(!queue.isEmpty()){
            Vertex head = queue.poll();
            String parentColor = labels.get(head);
            String neighColor = (parentColor.equals("Black")) ? "White" : "Black";
            for (Vertex neighbour : graph.getAdjacencyList().get(head)){
                if(labels.get(neighbour).equals("Black")){
                    labels.put(neighbour, neighColor);
                    queue.add(neighbour);
                }
                else
                    if(labels.get(neighbour).equals(labels.get(head)))
                        return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Graph testGraph = new Graph(GraphType.DIRECTED);
        testGraph.addEdge("0", "1", 1);
        testGraph.addEdge("0", "4", 1);
        testGraph.addEdge("0", "3", 1);
        testGraph.addEdge("1", "3", 1);
        testGraph.addEdge("1", "4", 1);
        testGraph.addEdge("2", "0", 1);
        testGraph.addEdge("2", "1", 1);
        testGraph.addEdge("4", "3", 1);
        BipartiteGraph currObject = new BipartiteGraph();
        System.out.println(testGraph);
        long startTime = System.nanoTime();
        boolean result = currObject.isBipartite(testGraph, testGraph.getVertex("0"));
        long endTime = System.nanoTime();
        System.out.println("The graph is " + ((result)? "" : "not ") + "a bipartite graph");
        System.out.println("\nThe algorithm took " + (endTime - startTime) + " nanoseconds.\n");

        Graph testGraph1 = new Graph(GraphType.UNDIRECTED);
        testGraph1.addEdge("0", "1", 1);
        testGraph1.addEdge("2", "1", 1);
        testGraph1.addEdge("2", "3", 1);
        testGraph1.addEdge("2", "5", 1);
        testGraph1.addEdge("4", "5", 1);
        System.out.println(testGraph1);
        startTime = System.nanoTime();
        result = currObject.isBipartite(testGraph1, testGraph1.getVertex("0"));
        endTime = System.nanoTime();
        System.out.println("The graph is " + ((result)? "" : "not ") + "a bipartite graph");
        System.out.println("\nThe algorithm took " + (endTime - startTime) + " nanoseconds.");
    }
}
