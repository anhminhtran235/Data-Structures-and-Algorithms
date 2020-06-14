package Graph.main.unweighted;

/**
 * EdgeNode for Adjacency list implementation of Graph
 */
public class EdgeNode {
    public int y;
    public EdgeNode next;
    public EdgeNode(int y, EdgeNode next) {
        this.y = y;
        this.next = next;
    }
}