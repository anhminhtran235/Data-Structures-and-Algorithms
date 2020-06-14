package Graph.main;

public class EdgeNode {
    public int y;
    public int weight;
    public EdgeNode next;
    public EdgeNode(int y, int weight, EdgeNode next) {
        this.y = y;
        this.weight = weight;
        this.next = next;
    }
}