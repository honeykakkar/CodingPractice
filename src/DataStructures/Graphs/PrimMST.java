package DataStructures.Graphs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 10/29/2016
 */

// A spanning tree of a graph is a subgraph that is a tree and connects all the vertices together.

// A  single graph can have many different spanning trees.
// A minimum spanning tree (MST) or minimum weight spanning tree for a weighted, connected and undirected graph is
// a spanning tree with weight less than or equal to the weight of every other spanning tree.
// The weight of a spanning tree is the sum of weights given to each edge of the spanning tree.
// If a graph's edges all have distinct weights, the MST is unique.

// Prim's algorithm is a greedy algorithm that finds a minimum spanning tree for a weighted undirected graph.

public class PrimMST {

    HashMap<Vertex, Vertex> parents = new HashMap<>();
    HashMap<Vertex, Integer> keys = new HashMap<>();
    HashMap<Vertex, Boolean> verticesMST = new HashMap<>();
    Graph graph;

    public PrimMST(Graph graph){
        this.graph = graph;
        for(Vertex vertex : graph.getVertices()) {
            verticesMST.put(vertex, false);
            keys.put(vertex, Integer.MAX_VALUE);
        }

        Vertex firstVertex = graph.getVertices().iterator().next();
        keys.put(firstVertex, 0);
        parents.put(firstVertex, null);
    }

    // method to find the vertex with minimum key value, from the set of vertices not yet included in MST
    public Vertex findMinKeyVertex(){
        int min = Integer.MAX_VALUE;
        Vertex minVertex = null;
        for(Vertex vertex : verticesMST.keySet()){
            if(!verticesMST.get(vertex) && keys.get(vertex) < min){
                min = keys.get(vertex);
                minVertex = vertex;
            }
        }
        return minVertex;
    }

    public void findMST() {

        for (int i=0; i<graph.getVertices().size() - 1; ++i){
            Vertex current = findMinKeyVertex();
            verticesMST.put(current, true);

            for(Vertex neighbour : graph.getAdjacencyList().get(current)){
                Edge edge = graph.getEdge(current, neighbour);
                int edgeWeight = edge.getWeight();
                int prevValue = keys.get(neighbour);

                if(!verticesMST.get(neighbour) && edgeWeight < prevValue){
                    keys.put(neighbour, edgeWeight);
                    parents.put(neighbour, current);
                }
            }
        }
    }

    // method to displayMSTEdges the list of elements
    public <T> void display(ArrayList<T> array){
        for (T element : array)
            System.out.println(element + " ");
        System.out.println();
    }

    void displayMSTEdges(){
        ArrayList<Edge> edgesMST = new ArrayList<>(parents.size());
        for (Vertex source : parents.keySet()){
            if(parents.get(source) != null){
                Edge connection = graph.getEdge(source, parents.get(source));
                edgesMST.add(connection);
            }
        }
        display(edgesMST);
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
        PrimMST currObject = new PrimMST(testGraph);
        long startTime = System.nanoTime();
        currObject.findMST();
        long endTime = System.nanoTime();
        System.out.println("The algorithm took " + (endTime - startTime) + " nanoseconds.\n");
        currObject.displayMSTEdges();
    }
}
