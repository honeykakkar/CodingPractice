package DataStructures.Graphs;

import java.util.HashMap;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/24/2016
 */

public class isGraphCyclic {

    private boolean isCyclicDirected(Vertex current, Graph graph, HashMap<Vertex, Boolean> recursionStack, HashMap<Vertex, Boolean> visited) {
        if (!visited.get(current)) {
            visited.put(current, true);
            recursionStack.put(current, true);
            for (Vertex neighbour : graph.getAdjacencyList().get(current)) {
                if (!visited.get(neighbour) && isCyclicDirected(neighbour, graph, recursionStack, visited))
                    return true;
                else if (recursionStack.get(neighbour))
                    return true;
            }
        }
        recursionStack.put(current, false);
        return false;
    }

    private boolean isCyclicDirected(Graph graph){
        HashMap<Vertex, Boolean> recursionStack = new HashMap<>();
        HashMap<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex v : graph.getVertices()) {
            visited.put(v, false);
            recursionStack.put(v, false);
        }

        // Performing DFS from each vertex. If the path contains any vertex which has already been
        // visited, it means the graph contains a cycle
        for (Vertex v : graph.getVertices()){
            if(isCyclicDirected(v, graph, recursionStack, visited))
                return true;
        }
        return false;
    }

    private boolean isCyclicUndirected(Vertex current, Graph graph, HashMap<Vertex, Boolean> visited, Vertex parent){
        visited.put(current, true);
        for (Vertex neighbour : graph.getAdjacencyList().get(current)) {
            if (!visited.get(neighbour)) {
                if (isCyclicUndirected(neighbour, graph, visited, current))
                    return true;
            }
            else if (neighbour != parent)
                return true;
        }
        return false;
    }

    private boolean isCyclicUndirected(Graph graph){
        HashMap<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex v : graph.getVertices())
            visited.put(v, false);
        for (Vertex v : graph.getVertices()){
            if(!visited.get(v)){
                if(isCyclicUndirected(v, graph, visited, null))
                    return true;
            }
        }
        return false;
    }

    boolean isCyclic(Graph graph){
        if (graph.getGraphType() == GraphType.UNDIRECTED)
            return isCyclicUndirected(graph);
        return isCyclicDirected(graph);
    }

    public static void main(String[] args) {
        Graph testGraph = new Graph(GraphType.DIRECTED);
        testGraph.addEdge("V0","V1",1);
        testGraph.addEdge("V0","V2",1);
        testGraph.addEdge("V2","V0",1);
        testGraph.addEdge("V1","V2",1);
        testGraph.addEdge("V2","V3",1);
        testGraph.addEdge("V3","V3",1);
        System.out.println(testGraph.toString());
        isGraphCyclic currentObj = new isGraphCyclic();
        boolean result = currentObj.isCyclic(testGraph);
        System.out.println("The graph does" + (result?" ":" not ") + "contain a cycle.\n");

        Graph testGraph1 = new Graph(GraphType.UNDIRECTED);
        testGraph1.addEdge("V0", "V1", 1);
        testGraph1.addEdge("V1", "V2", 1);
        testGraph1.addEdge("V2", "V3", 1);
        testGraph1.addEdge("V3", "V4", 1);
        System.out.println(testGraph1.toString());
        currentObj = new isGraphCyclic();
        result = currentObj.isCyclic(testGraph1);
        System.out.println("The graph does" + (result?" ":" not ") + "contain a cycle.");
    }
}
