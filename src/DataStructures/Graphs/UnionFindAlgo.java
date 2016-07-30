package DataStructures.Graphs;

import java.util.HashMap;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/22/2016
 */
public class UnionFindAlgo {

    class DSElement{
        Vertex parent;
        int rank;
    }

    class DisjointSet{
        HashMap<Vertex, DSElement> disjointSet;

        DisjointSet(){
            disjointSet = new HashMap<>();
        }

        DisjointSet(Graph graph){
            disjointSet = new HashMap<>();
            for(Vertex vertex : graph.getVertices()){
                disjointSet.put(vertex, new DSElement());
                disjointSet.get(vertex).parent = vertex;
                disjointSet.get(vertex).rank = 0;
            }
        }

        Vertex find(Vertex vertex){
            if(disjointSet.get(vertex).parent != vertex)
                disjointSet.get(vertex).parent = find(disjointSet.get(vertex).parent);

            return disjointSet.get(vertex).parent;
        }

        void union(Vertex v1, Vertex v2){
            Vertex v1Parent = find(v1);
            Vertex v2Parent = find(v2);

            if(disjointSet.get(v1Parent).rank < disjointSet.get(v2Parent).rank)
                disjointSet.get(v1Parent).parent = v2Parent;
            else
                if(disjointSet.get(v1Parent).rank > disjointSet.get(v2Parent).rank)
                    disjointSet.get(v2Parent).parent = v1Parent;
            else {
                    disjointSet.get(v2Parent).parent = v1Parent;
                    ++disjointSet.get(v1Parent).rank;
                }
        }

        void insert(Vertex vertex) {
            disjointSet.put(vertex, new DSElement());
            disjointSet.get(vertex).parent = vertex;
            disjointSet.get(vertex).rank = 0;
        }

        boolean isConnected(Vertex v1, Vertex v2){
            Vertex rep1 = find(v1);
            Vertex rep2 = find(v2);
            return rep1.equals(rep2);
        }
    }

    public static void main(String[] args) {
        System.out.println("\nTesting Disjoint set with Vertex type elements");
        System.out.println("-----------------------------------------------\n");
        UnionFindAlgo UFAlgo = new UnionFindAlgo();
        DisjointSet vSet = UFAlgo.new DisjointSet();
        Vertex V1 = new Vertex("V1");
        Vertex V2 = new Vertex("V2");
        Vertex V3 = new Vertex("V3");
        Vertex V5 = new Vertex("V5");
        Vertex V8 = new Vertex("V8");
        Vertex V13 = new Vertex("V13");
        Vertex V21 = new Vertex("V21");
        Vertex V34 = new Vertex("V34");

        vSet.insert(V1);
        vSet.insert(V2);
        vSet.insert(V3);
        vSet.insert(V5);
        vSet.insert(V8);
        vSet.insert(V13);
        vSet.insert(V21);
        vSet.insert(V34);

        vSet.union(V1, V21);
        vSet.union(V2, V13);
        vSet.union(V3, V8);
        vSet.union(V1, V8);
        vSet.union(V3,V5);
        vSet.union(V3,V34);

        System.out.println("The representative of the set with vertex V5 is : " + vSet.find(V5));
        System.out.println("The representative of the set with vertex V3 is : " + vSet.find(V3));
        System.out.println("The representative of the set with vertex V21 is : " + vSet.find(V21));
        System.out.println("The representative of the set with vertex V8 is : " + vSet.find(V8));
        System.out.println("The representative of the set with vertex V13 is : " + vSet.find(V13));
        System.out.println("The representative of the set with vertex V2 is : " + vSet.find(V2));

        System.out.println(vSet.isConnected(V8,V3));
        System.out.println(vSet.isConnected(V34,V5));
        System.out.println(vSet.isConnected(V2,V3));
        System.out.println(vSet.isConnected(V1,V21));
        System.out.println(vSet.isConnected(V21,V13));
        System.out.println(vSet.isConnected(V8,V1));
    }
}
