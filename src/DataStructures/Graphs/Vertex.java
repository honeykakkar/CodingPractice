package DataStructures.Graphs;

import java.util.HashSet;
import java.util.Objects;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/16/2016
 */
public class Vertex {

    private HashSet<Edge> neighbours;
    private String name;
    public boolean visited;

    Vertex(String name){
        this.neighbours = new HashSet<>();
        this.name = name;
        this.visited = false;
    }

    public void addNeighbour(Edge newNB){
        this.neighbours.add(newNB);
    }

    public HashSet<Edge> getAllNeighbours(){
        return this.neighbours;
    }

    public boolean hasNeighbour(Edge edge){
        return neighbours.contains(edge);
    }

    public boolean removeNeighbour(Edge oldNB){
        if(!neighbours.contains(oldNB))
            return false;
        else
            neighbours.remove(oldNB);
        return true;
    }

    public String toString()
    {
        return name;
    }

    public int getNeighbourCount(){
        return neighbours.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vertex))
            return false;
        Vertex Obj = (Vertex)obj;
        return this.getAllNeighbours().equals(Obj.getAllNeighbours()) && Objects.equals(this.name, Obj.name);
    }
}
