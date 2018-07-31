package DataStructures.Graphs;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 6/16/2016
 */
public class Edge implements Comparable<Edge> {
    private Vertex source;
    private Vertex destination;
    private int weight;

    int getWeight() {
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

    Vertex getDestination() {
        return destination;
    }

    public void setDestination(Vertex destination) {
        this.destination = destination;
    }

    public Edge(int weight) {
        this.weight = weight;
    }

    public Edge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Edge))
            return false;
        Edge other = (Edge) obj;
        return this.source.equals(other.source) &&
                this.destination.equals(other.destination) &&
                this.weight == other.weight;
    }

    @Override
    public String toString() {
        return "Source:" + this.source.toString() + "\tDestination:" + this.destination.toString() + "\tWeight:" + this.weight;
    }
}
