package DataStructures.Graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/10/2016
 */

// A DAG is given to us, we need to find maximum number of edges that can be added to this DAG,
// after which new graph still remain a DAG.

// The output should be a graph in which further adding even a single edge would result in a cycle.

public class AddEdges {

    private void performTopSort(Stack<Vertex> stack, HashMap<Vertex, Boolean> visited, Graph graph, Vertex current) {
        visited.put(current, true);
        for (Vertex neighbour : graph.getAdjacencyList().get(current)) {
            if (!visited.get(neighbour))
                performTopSort(stack, visited, graph, neighbour);
        }
        stack.push(current);
    }

    private Stack<Vertex> performTopSort(Graph graph) {
        Stack<Vertex> stack = new Stack<>();
        HashMap<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex V : graph.getVertices())
            visited.put(V, false);

        for (Vertex vertex : graph.getVertices()) {
            if (!visited.get(vertex))
                performTopSort(stack, visited, graph, vertex);
        }
        return stack;
    }

    private void addMaxEdges(Graph graph) {
        Stack<Vertex> topSortStack = performTopSort(graph);
        for (int i = topSortStack.size() - 1; i >= 0; --i) {
            Vertex iVertex = topSortStack.elementAt(i);
            HashSet<Vertex> neighbours = graph.getAdjacencyList().get(iVertex);
            for (int j = i - 1; j >= 0; --j) {
                Vertex jVertex = topSortStack.elementAt(j);
                if (!neighbours.contains(jVertex))
                    graph.addEdge(iVertex.getName(), jVertex.getName(), 0);
            }
        }
    }

    public static void main(String[] args) {
        Graph testGraph = new Graph(GraphType.DIRECTED);
        testGraph.addEdge("2", "3", 0);
        testGraph.addEdge("3", "1", 0);
        testGraph.addEdge("4", "0", 0);
        testGraph.addEdge("4", "1", 0);
        testGraph.addEdge("5", "0", 0);
        testGraph.addEdge("5", "2", 0);
        System.out.println("Total number of edges in current graph: " + testGraph.getEdges().size());
        isGraphCyclic cycleDetector = new isGraphCyclic();
        boolean result = cycleDetector.isCyclic(testGraph);
        System.out.println("The graph does" + (result ? " " : " not ") + "contain a cycle.");

        AddEdges edgesAdder = new AddEdges();
        edgesAdder.addMaxEdges(testGraph);
        System.out.println("Total number of edges in current graph: " + testGraph.getEdges().size());
        result = cycleDetector.isCyclic(testGraph);
        System.out.println("The graph does" + (result ? " " : " not ") + "contain a cycle.");
    }
}
