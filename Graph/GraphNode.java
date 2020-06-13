package Graph;

import java.util.*;

public class GraphNode {
    public List<LinkedList<GraphNode>> children;
    public int value;
    public GraphNode(int value) {
        this.children = new ArrayList<>();
        this.value = value;
    }
}