package DataStructures.Graphs;

import java.util.HashMap;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/24/2016
 */

public class isGraphCyclic {

    private boolean isCyclic(Vertex current, Graph graph, HashMap<Vertex, Boolean> recursionStack, HashMap<Vertex, Boolean> visited) {
        if (!visited.get(current)) {
            visited.put(current, true);
            recursionStack.put(current, true);
            for (Vertex neighbour : graph.getAdjacencyList().get(current)) {
                if (!visited.get(neighbour) && isCyclic(neighbour, graph, recursionStack, visited))
                    return false;
                else if (recursionStack.get(neighbour))
                    return false;
            }
        }

        recursionStack.put(current, false);
        return true;
    }

    private boolean isCyclic(Graph graph){
        HashMap<Vertex, Boolean> recursionStack = new HashMap<>();
        HashMap<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex v : graph.getVertices()) {
            visited.put(v, false);
            recursionStack.put(v, false);
        }

        // Performing DFS from each vertex. If the path contains any vertex which has already been
        // visited, it means the graph contains a cycle

        for (Vertex v : graph.getVertices()){
            if(isCyclic(v, graph, recursionStack, visited))
                return true;
        }
        return false;
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
        System.out.println(result);
    }
}
