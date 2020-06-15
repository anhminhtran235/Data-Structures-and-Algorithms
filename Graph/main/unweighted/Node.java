package Graph.main.unweighted;

/**
 * EdgeNode for Adjacency list implementation of Graph
 */
public class Node {
    public int id;
    public Node next;
    public Node(int y, Node next) {
        this.id = y;
        this.next = next;
    }
}