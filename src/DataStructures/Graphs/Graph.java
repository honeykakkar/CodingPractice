package DataStructures.Graphs;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/16/2016
 */

public class Graph {
    private HashMap<Vertex, HashSet<Vertex>> adjacencyList;
    private HashMap<String, Vertex> vertices;
    private int verticesCount;
    private int edgesCount;
    private GraphType graphType;

    Graph(){
        adjacencyList = new HashMap<>();
        vertices = new HashMap<>();
        verticesCount = 0;
        edgesCount = 0;
        graphType = GraphType.DIRECTED;
    }

    Graph(GraphType graphType){
        adjacencyList = new HashMap<>();
        vertices = new HashMap<>();
        verticesCount = 0;
        edgesCount = 0;
        this.graphType = graphType;
    }

    public Vertex addVertex(String vName){
        Vertex newV = null;
        if(!vertices.containsKey(vName)){
            newV = new Vertex(vName);
            vertices.put(vName,newV);
            adjacencyList.put(newV, new HashSet<>());
            ++verticesCount;
        }
        return newV;
    }

    public String toString() {
        String s = "";
        for (Vertex v : vertices.values()) {
            s += v + ": ";
            for (Vertex w : adjacencyList.get(v)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }

    public void addEdge(String source, String dest, int weight){
        Vertex vSource, vDest;
        if(hasVertex(source))
            vSource = vertices.get(source);
        else
            vSource = addVertex(source);

        if(hasVertex(dest))
            vDest = vertices.get(dest);
        else
            vDest = addVertex(dest);

        adjacencyList.get(vSource).add(vDest);
        Edge nEdge = new Edge(vSource, vDest, weight);
        vSource.addNeighbour(nEdge);
        ++edgesCount;
        if(graphType == GraphType.UNDIRECTED) {
            adjacencyList.get(vDest).add(vSource);
            Edge n1Edge = new Edge(vDest, vSource, weight);
            vDest.addNeighbour(n1Edge);
            ++edgesCount;
        }
    }

    public HashSet<Edge> getEdges(){
        HashSet<Edge> edges = new HashSet<>();
        for (Vertex current : adjacencyList.keySet()){
            for (Edge edge: current.getAllNeighbours())
                edges.add(edge);
        }
        return edges;
    }

    public boolean hasVertex(String vName){
        return vertices.containsKey(vName);
    }

    public Vertex getVertex(String vName){
        if(hasVertex(vName))
            return vertices.get(vName);
        return null;
    }

    public static void main(String[] args) {
        Graph testGraph = new Graph(GraphType.DIRECTED);
        testGraph.addEdge("V1","V2",1);
        testGraph.addEdge("V2","V3",1);
        testGraph.addEdge("V1","V3",2);
        testGraph.addEdge("V3","V4",1);
        System.out.println(testGraph.toString());
    }
}
