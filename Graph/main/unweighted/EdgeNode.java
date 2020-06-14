package Graph.main.unweighted;

public class EdgeNode {
    public int y;
    public EdgeNode next;
    public EdgeNode(int y, EdgeNode next) {
        this.y = y;
        this.next = next;
    }
}