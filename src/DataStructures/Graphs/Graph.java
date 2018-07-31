package DataStructures.Graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 6/16/2016
 */

public class Graph {

    private final HashMap<Vertex, HashSet<Vertex>> adjacencyList;
    private final HashMap<String, Vertex> vertices;
    private int verticesCount;
    private int edgesCount;
    private final GraphType graphType;

    Graph() {
        adjacencyList = new HashMap<>();
        vertices = new HashMap<>();
        verticesCount = 0;
        edgesCount = 0;
        graphType = GraphType.DIRECTED;
    }

    public Graph(GraphType graphType) {
        adjacencyList = new HashMap<>();
        vertices = new HashMap<>();
        verticesCount = 0;
        edgesCount = 0;
        this.graphType = graphType;
    }

    public HashMap<Vertex, HashSet<Vertex>> getAdjacencyList() {
        return adjacencyList;
    }

    public GraphType getGraphType() {
        return graphType;
    }

    // method to add an isolated vertex to graph
    private Vertex addVertex(String vName) {
        Vertex newV = null;
        if (!vertices.containsKey(vName)) {
            newV = new Vertex(vName);
            vertices.put(vName, newV);
            adjacencyList.put(newV, new HashSet<>());
            ++verticesCount;
        }
        return newV;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Vertex v : vertices.values()) {
            s.append(v).append(": ");
            for (Vertex w : adjacencyList.get(v)) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    // method to add an edge in the graph
    public void addEdge(String source, String dest, int weight) {
        Vertex vSource, vDest;
        if (hasVertex(source))
            vSource = vertices.get(source);
        else
            vSource = addVertex(source);

        if (hasVertex(dest))
            vDest = vertices.get(dest);
        else
            vDest = addVertex(dest);

        adjacencyList.get(vSource).add(vDest);  // add vertex vertex in the list of vertices that can be reached from the source
        Edge nEdge = new Edge(vSource, vDest, weight);  // create a new edge between source and vertex
        vSource.addNeighbour(nEdge);    // add the edge to the list of outgoing edges of the vertex
        ++edgesCount;
        if (graphType == GraphType.UNDIRECTED) {     // if undirected, do the inverse of it too as source can reach vertex and vice versa in case of undirected edge
            adjacencyList.get(vDest).add(vSource);
            Edge n1Edge = new Edge(vDest, vSource, weight);
            vDest.addNeighbour(n1Edge);
            ++edgesCount;
        }
    }

    // method to get edge between two given vertices
    public Edge getEdge(Vertex source, Vertex dest) {
        if (source == null || dest == null || !adjacencyList.containsKey(source))
            return null;

        for (Edge outgoingEdge : source.getAllNeighbours()) {
            if (outgoingEdge.getDestination().equals(dest))
                return outgoingEdge;
        }
        return null;
    }

    // method to get a collection of all edges in the graph
    public HashSet<Edge> getEdges() {
        HashSet<Edge> edges = new HashSet<>();
        for (Vertex current : adjacencyList.keySet())
            edges.addAll(current.getAllNeighbours());

        if (this.graphType == GraphType.UNDIRECTED) {
            ArrayList<Edge> uEdges = new ArrayList<>(edges);
            for (int i = 0; i < uEdges.size(); ++i) {
                Edge currEdge = uEdges.get(i);
                for (int j = i; j < uEdges.size(); ++j) {
                    Edge dupEdge = uEdges.get(j);
                    if (dupEdge.getDestination().equals(currEdge.getSource()) &&
                            dupEdge.getSource().equals(currEdge.getDestination()))
                        edges.remove(dupEdge);
                }
            }
        }
        return edges;
    }

    // method to get the collection of all vertices of the graph
    public Collection<Vertex> getVertices() {
        return vertices.values();
    }

    // method to check graph contains a particular vertex
    private boolean hasVertex(String vName) {
        return vertices.containsKey(vName);
    }

    public Vertex getVertex(String vName) {
        if (hasVertex(vName))
            return vertices.get(vName);
        return null;
    }

    // method to get the in-degrees of each vertex in the graph.
    // Out-degree is represented by size of the neighbours of vertex
    HashMap<Vertex, Integer> getIndegrees() {
        HashMap<Vertex, Integer> inDegrees = new HashMap<>();
        for (Edge edge : this.getEdges()) {
            Vertex dest = edge.getDestination();
            inDegrees.put(dest, inDegrees.getOrDefault(dest, 0) + 1);
        }
        return inDegrees;
    }

    // Method to get a new graph which is a transpose of this graph

    Graph getTranspose() {
        if (this.graphType == GraphType.UNDIRECTED)
            return this;

        Graph transposeGraph = new Graph(GraphType.DIRECTED);
        Collection<Edge> edges = this.getEdges();
        for (Edge edge : edges) {
            transposeGraph.addEdge(edge.getDestination().toString(), edge.getSource().toString(), edge.getWeight());
        }
        return transposeGraph;
    }

    public static void main(String[] args) {
        Graph testGraph = new Graph(GraphType.UNDIRECTED);
        testGraph.addEdge("V1", "V2", 1);
        testGraph.addEdge("V2", "V3", 1);
        testGraph.addEdge("V1", "V3", 1);
        testGraph.addEdge("V3", "V4", 1);
        System.out.println(testGraph.toString());
        System.out.println("The graph contains " + testGraph.getEdges().size() + " edges.\n");

        Graph testGraph1 = new Graph(GraphType.DIRECTED);
        testGraph1.addEdge("V0", "V1", 1);
        testGraph1.addEdge("V0", "V2", 1);
        testGraph1.addEdge("V2", "V0", 1);
        testGraph1.addEdge("V1", "V2", 1);
        testGraph1.addEdge("V2", "V3", 1);
        testGraph1.addEdge("V3", "V3", 1);
        System.out.println(testGraph1.toString());
        System.out.println("The graph contains " + testGraph1.getEdges().size() + " edges.");
    }
}
