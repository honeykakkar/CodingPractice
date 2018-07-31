package DataStructures.Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 10/30/2016
 */

public class GetAllPaths {

    private void getAllPaths(Vertex source, Vertex dest, Graph graph) {
        HashMap<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex v : graph.getVertices())
            visited.put(v, false);

        List<List<Vertex>> allPaths = new ArrayList<>();
        List<Vertex> currentPath = new ArrayList<>();
        getPaths(allPaths, currentPath, visited, source, dest, graph, 0);
        printPaths(allPaths);
    }

    private void getPaths(List<List<Vertex>> allPaths, List<Vertex> currentPath, HashMap<Vertex, Boolean> visited, Vertex source, Vertex dest, Graph graph, int pathIndex) {

        visited.put(source, true);

        if (pathIndex < currentPath.size())
            currentPath.set(pathIndex, source);
        else
            currentPath.add(source);

        if (source == dest)
            allPaths.add(new ArrayList<>(currentPath));
        else {
            for (Vertex neighbour : graph.getAdjacencyList().get(source)) {
                if (!visited.get(neighbour))
                    getPaths(allPaths, currentPath, visited, neighbour, dest, graph, pathIndex + 1);
            }
        }

        // backtracking here
        currentPath.remove(currentPath.size() - 1);
        --pathIndex;
        visited.put(source, false);
    }

    private void printPaths(List<List<Vertex>> allPaths) {
        for (List<Vertex> path : allPaths) {
            for (Vertex vertex : path)
                System.out.print(vertex + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Graph testGraph = new Graph(GraphType.UNDIRECTED);
        testGraph.addEdge("0", "1", 4);
        testGraph.addEdge("0", "7", 8);
        testGraph.addEdge("1", "2", 8);
        testGraph.addEdge("1", "7", 11);
        testGraph.addEdge("2", "3", 7);
        testGraph.addEdge("2", "5", 4);
        testGraph.addEdge("2", "8", 2);
        testGraph.addEdge("3", "4", 9);
        testGraph.addEdge("3", "5", 14);
        testGraph.addEdge("5", "4", 10);
        testGraph.addEdge("7", "6", 1);
        testGraph.addEdge("7", "8", 7);
        testGraph.addEdge("6", "5", 2);
        testGraph.addEdge("6", "8", 6);
        System.out.println(testGraph.toString());

        GetAllPaths pathFinder = new GetAllPaths();
        pathFinder.getAllPaths(testGraph.getVertex("3"),
                testGraph.getVertex("6"), testGraph);
    }
}
