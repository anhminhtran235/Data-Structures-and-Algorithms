package Graph.main.weighted;

public class NodeWrapper implements Comparable<NodeWrapper>{
    public Graph.Node node;
    public int distance;
    public int parent;
    public NodeWrapper(Graph.Node node, int distance, int parent) {
        this.node = node;
        this.distance = distance;
        this.parent = parent;
    }

    @Override
    public int compareTo(NodeWrapper other) {
        if (this.node.id == other.node.id) {
            return 0;
        } else {
            return this.distance - other.distance;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof NodeWrapper)) {
            return false;
        }
        NodeWrapper other = (NodeWrapper)o;
        return this.node.equals(other.node);
    }

    @Override
    public int hashCode() {
        return this.node.hashCode();
    }

    public String toString() {
        return this.distance + "";
    }
}