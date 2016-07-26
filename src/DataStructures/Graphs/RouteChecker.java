package DataStructures.Graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/20/2016
 */

// Check if there exists a route between two given nodes in a directed graph

// Algorithm : Perform BFS or DFS starting from any of the two nodes.
// If the other node appears during the traversal, return true, else false.

public class RouteChecker {

    // This method performs BFS to check if there exists any path between two vertices
    private boolean ifRouteExists(Vertex source, Vertex dest){
        if(source == dest)
            return true;
        if(source == null || dest == null)
            return false;
        Queue<Vertex> visitQ = new LinkedList<>();
        visitQ.offer(source);
        while(!visitQ.isEmpty()){
            Vertex current = visitQ.remove();
            current.visited = true;
            for(Edge outEdge : current.getAllNeighbours()){
                Vertex end = outEdge.getDestination();
                if(!end.visited){
                    if(end == dest)
                        return true;
                    else
                        visitQ.add(end);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(GraphType.DIRECTED);
        graph.addEdge("V1","V2",1);
        graph.addEdge("V2","V3",1);
        graph.addEdge("V1","V3",2);
        graph.addEdge("V3","V4",1);
        graph.addEdge("V5","V6",3);
        graph.addEdge("V4","V6",2);
        graph.addEdge("V6","V1",3);
        RouteChecker currentObject = new RouteChecker();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of source vertex:");
        String s = scanner.nextLine().toUpperCase();
        Vertex source = graph.getVertex(s);
        System.out.println("Enter name of destination vertex:");
        String d = scanner.nextLine().toUpperCase();
        Vertex dest = graph.getVertex(d);
        long startTime = System.nanoTime();
        Boolean result = currentObject.ifRouteExists(source, dest);
        long endTime = System.nanoTime();
        System.out.println("There does " + (result ? "" : "not ") + "exist a path between the given vertices " + s + " and " + d);
        System.out.println("\nThe algorithm took " + (endTime - startTime) + " nanoseconds.");
    }
}
