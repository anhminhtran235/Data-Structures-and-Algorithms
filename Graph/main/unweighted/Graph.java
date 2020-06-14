package Graph.main.unweighted;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    List<EdgeNode> edges;
    boolean isDirected;
    int numNodes;

    public Graph(int numNodes, boolean isDirected) {
        if (numNodes < 0) {
            throw new IndexOutOfBoundsException("Number of nodes cannot be negative");
        }

        edges = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            edges.add(null);
        }
        this.isDirected = isDirected;
        this.numNodes = numNodes;
    }

    public void addEdge(int from, int to) {
        if (from >= this.numNodes || from < 0) {
            throw new IndexOutOfBoundsException("Invalid from node: " + from);
        } else if (to >= this.numNodes || to < 0) {
            throw new IndexOutOfBoundsException("Invalid to node: " + to);
        }

        addEdge(from, to, this.isDirected);
    }

    // Allow multi edge. Also, notice the recursive call when graph is undirected
    private void addEdge(int from, int to, boolean isDirected) {
        EdgeNode head = edges.get(from);
        if (head == null) {
            edges.set(from, new EdgeNode(to, null));
        } else {
            edges.set(from, new EdgeNode(to, head));
        }

        if (isDirected == false) {
            addEdge(to, from, true);    // recursive call
        }
    }

    public void printGraph() {
        for (int i = 0; i < edges.size(); i++) {
            System.out.print(i);
            EdgeNode edgeTemp = edges.get(i);
            while (edgeTemp != null) {
                System.out.print("->" + edgeTemp.y);
                edgeTemp = edgeTemp.next;
            }
            System.out.println();
        }
    }
}