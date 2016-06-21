package DataStructures.Graphs;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/16/2016
 */
public class Edge {
    private Vertex source;
    private Vertex destination;
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public void setDestination(Vertex destination) {
        this.destination = destination;
    }

    public Edge(int weight){
        this.weight = weight;
    }

    public Edge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}
