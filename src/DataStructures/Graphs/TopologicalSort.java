package DataStructures.Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 7/10/2016
 */

// Topological sort can be thought as the ordering of vertices in the order of decreasing in-degrees

public class TopologicalSort {

    void topologicalSort(Graph graph, Vertex current, HashMap<Vertex, Boolean> visited, Stack<Vertex> stack){
        visited.put(current, true);
        for (Vertex neighbour : graph.getAdjacencyList().get(current)){
            if(visited.get(neighbour) == false)
                topologicalSort(graph, neighbour, visited, stack);
        }
        stack.push(current);
    }

    // performing topological sort using DFS approach
    void performTopSort(Graph graph){
        Stack<Vertex> stack = new Stack<>();
        HashMap<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex v : graph.getVertices())
            visited.put(v, false);
        System.out.print("\nOne of the topological sorts of graph is: ");
        for (Vertex v : graph.getVertices()){
            if(visited.get(v) == false)
                topologicalSort(graph, v, visited, stack);
        }

        for (int i= stack.size()-1; i>=0; --i)
            System.out.print(stack.get(i) + "  ");
    }

    // solving topological sort using the approach of in-degrees of each vertex
    void getAllTopSorts(Graph graph, ArrayList<Vertex> topSort, HashMap<Vertex, Boolean> visited, HashMap<Vertex, Integer> inDegrees){
        boolean flag = false;
        for (Vertex currV : graph.getVertices()){
            if(inDegrees.get(currV) == 0 && visited.get(currV) == false){
                for(Vertex neighbour : graph.getAdjacencyList().get(currV)){
                    Integer prevValue = inDegrees.get(neighbour);
                    inDegrees.put(neighbour, prevValue - 1);
                }

                topSort.add(currV);
                visited.put(currV, true);
                getAllTopSorts(graph, topSort, visited, inDegrees);

                // backtracking here
                visited.put(currV, false);
                topSort.remove(topSort.size()-1);
                for(Vertex neighbour : graph.getAdjacencyList().get(currV)){
                    Integer prevValue = inDegrees.get(neighbour);
                    inDegrees.put(neighbour, prevValue + 1);
                }
                flag = true;
            }
        }

        if(!flag){
            for (Vertex V : topSort)
                System.out.print(V + "  ");
            System.out.println();
        }
    }

    void performAllTopSorts(Graph graph){
        HashMap<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex v : graph.getVertices())
            visited.put(v, false);
        ArrayList<Vertex> topSort = new ArrayList<>();
        HashMap<Vertex, Integer> inDegrees = graph.getIndegrees();
        getAllTopSorts(graph, topSort, visited, inDegrees);
    }

    public static void main(String[] args) {
        TopologicalSort currObject = new TopologicalSort();
        Graph testGraph = new Graph(GraphType.DIRECTED);
        testGraph.addEdge("V5", "V0", 1);
        testGraph.addEdge("V5", "V2", 1);
        testGraph.addEdge("V4", "V0", 1);
        testGraph.addEdge("V4", "V1", 1);
        testGraph.addEdge("V2", "V3", 1);
        testGraph.addEdge("V3", "V1", 1);
        System.out.print("Displaying the adjacency list of graph:\n" + testGraph.toString());
        currObject.performTopSort(testGraph);
        System.out.println("\n\nDisplaying all topological sorts of the graph:");
        currObject.performAllTopSorts(testGraph);
    }
}
