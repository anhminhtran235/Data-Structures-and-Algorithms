package Graph.main.unweighted;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of unweighted Graph using adjacency list representation
 * This graph can be either undirected or directed.
 */
public class Graph {
    List<Node> edges;
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

    /**
     * Allow multi edge and self loop. Also, notice the recursive call when the graph is undirected
    **/
    private void addEdge(int from, int to, boolean isDirected) {
        Node head = edges.get(from);
        if (head == null) {
            edges.set(from, new Node(to, null));
        } else {
            edges.set(from, new Node(to, head));
        }

        if (isDirected == false) {
            addEdge(to, from, true);    // recursive call
        }
    }

    public void printGraph() {
        for (int i = 0; i < edges.size(); i++) {
            System.out.print(i);
            Node edgeTemp = edges.get(i);
            while (edgeTemp != null) {
                System.out.print("->" + edgeTemp.id);
                edgeTemp = edgeTemp.next;
            }
            System.out.println();
        }
    }

    /**
     * Allow multi edge and self loop
     */
    public static Graph generateRandomGraph(int numVertices, int numEdges, boolean isDirected) {
        Graph g = new Graph(numVertices, isDirected);
        for (int i = 0; i < numEdges; i++) {
            int vertex1 = (int)(Math.random() * numVertices);
            int vertex2 = (int)(Math.random() * numVertices);
            g.addEdge(vertex1, vertex2);
        }

        return g;
    }
}