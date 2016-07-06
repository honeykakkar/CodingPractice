package DataStructures.Graphs;

import DataStructures.DisjointSet;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/24/2016
 */

public class isGraphAcyclic {

    private boolean isACyclic(Graph G){
        DisjointSet<Vertex> vSet = new DisjointSet<>();
        for (Vertex currVertex : G.getVertices())
            vSet.insert(currVertex);
        for (Edge currEdge : G.getEdges()){
            Vertex source = currEdge.getSource();
            Vertex dest = currEdge.getDestination();
            System.out.println("Source:" + source + "  Dest:" + dest + "  connect:" + vSet.isConnected(source, dest));
            if(vSet.isConnected(source, dest))
                return false;
            vSet.union(source, dest);
        }
        return true;
    }

    public static void main(String[] args) {
        Graph testGraph = new Graph(GraphType.UNDIRECTED);
        testGraph.addEdge("V1","V2",1);
        testGraph.addEdge("V2","V3",1);
        testGraph.addEdge("V1","V3",2);
        testGraph.addEdge("V3","V4",1);
        System.out.println(testGraph.toString());
        System.out.println(testGraph.getEdges().size());
        testGraph.getEdges().forEach(System.out::println);
        isGraphAcyclic currentObj = new isGraphAcyclic();
        boolean result = currentObj.isACyclic(testGraph);
        System.out.println(result);
    }
}
