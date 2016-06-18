package DataStructures.Graph;

import java.util.HashSet;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/16/2016
 */
public class Vertex {

    private HashSet<Edge> neighbours;
    private String name;

    Vertex(String name){
        this.neighbours = new HashSet<>();
        this.name = name;
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
}
