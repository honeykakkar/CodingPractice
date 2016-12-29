package DataStructures.DisjointSet;

import DataStructures.Graphs.Vertex;

import java.util.HashMap;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/22/2016
 */
public class DisjointSet<T> {



    final HashMap<T, DSElement<T>> elements;

    public DisjointSet() {
        elements = new HashMap<>();
    }

    public DisjointSet(int N) {
        elements = new HashMap<>(N);
    }

    public void insert(T newData){
        DSElement<T> newElement = new DSElement<>(newData, 0);
        newElement.parent = newElement;
        elements.put(newData, newElement);
    }

    public boolean union(T data1, T data2){
        DSElement<T> element1 = elements.get(data1);
        DSElement<T> element2 = elements.get(data2);
        if(element1 == null || element2 == null || element1.equals(element2))
            return false;

        if(element1.rank >= element2.rank){
            element2.parent = element1;
            if(element1.rank == element2.rank)
                ++element1.rank;
        }
        else
            element1.parent = element2;
        return true;
    }

    public T find(T data){
        if(elements.get(data) != null)
            return find(elements.get(data)).data;
        return null;
    }

    public DSElement<T> find(DSElement<T> current){
        DSElement<T> currParent = current.parent;
        if(currParent == current)
            return currParent;
        current.parent = find(currParent);
        return currParent;
    }

    public boolean isConnected(T data1, T data2){
        T rep1 = find(data1);
        T rep2 = find(data2);
        return rep1.equals(rep2);
    }

    public static void main(String[] args) {
        System.out.println("Testing Disjoint set with Integer type elements");
        System.out.println("-----------------------------------------------\n");
        DisjointSet<Integer> set = new DisjointSet<>();
        set.insert(1);
        set.insert(2);
        set.insert(3);
        set.insert(5);
        set.insert(8);
        set.insert(13);
        set.insert(21);
        set.insert(34);

        set.union(1,21);
        set.union(2,13);
        set.union(3,8);
        set.union(1,8);
        set.union(3,5);
        set.union(3,34);

        System.out.println("The representative of the set with element 5 is : " + set.find(5));
        System.out.println("The representative of the set with element 3 is : " + set.find(3));
        System.out.println("The representative of the set with element 21 is : " + set.find(21));
        System.out.println("The representative of the set with element 8 is : " + set.find(8));
        System.out.println("The representative of the set with element 13 is : " + set.find(13));
        System.out.println("The representative of the set with element 2 is : " + set.find(2));

        System.out.println(set.isConnected(8,3));
        System.out.println(set.isConnected(34,5));
        System.out.println(set.isConnected(2,3));
        System.out.println(set.isConnected(2,13));
        System.out.println(set.isConnected(21,13));
        System.out.println(set.isConnected(21,8));

        System.out.println("\nTesting Disjoint set with Vertex type elements");
        System.out.println("-----------------------------------------------\n");
        DisjointSet<Vertex> vSet = new DisjointSet<>();
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
        System.out.println(vSet.isConnected(V2,V13));
        System.out.println(vSet.isConnected(V21,V13));
        System.out.println(vSet.isConnected(V21,V8));
    }
}
