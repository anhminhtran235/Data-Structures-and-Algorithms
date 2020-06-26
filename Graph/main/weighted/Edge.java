package Graph.main.weighted;

public class Edge implements Comparable<Edge>{
    public Edge(int node1, int node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }
    public int node1;
    public int node2;
    public int weight;

    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }

    public String toString() {
        return node1 + "-" + node2 + "(weight:" + weight + ")";
    }
}
