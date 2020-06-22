package Graph.main.unweighted;

import java.util.*;

/**
 * Implementation of unweighted Graph using adjacency list representation
 * This graph can be either undirected or directed.
 * Multi-edge and self loop are not allowed
 */
public class Graph {
    List<LinkedList<Node>> adjacencies;
    boolean isDirected;
    int numNodes;

    public static class Node {
        public int id;
        public Node(int id) {
            this.id = id;
        }
        @Override
        public boolean equals(Object other) {
            if (other instanceof Node) {
                return this.id == ((Node)other).id;
            }
            return false;
        }
        @Override
        public int hashCode() {
            return this.id;
        }
    }

    public Graph(int numNodes, boolean isDirected) {
        if (numNodes < 0) {
            throw new IndexOutOfBoundsException("Number of nodes cannot be negative");
        }

        adjacencies = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            adjacencies.add(new LinkedList<Node>());
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
     * Not allow self loop or multiedge
     */
    private boolean addEdge(int from, int to, boolean isDirected) {
        if (from == to || this.adjacencies.get(from).contains(new Node(to))) {
            return false;
        }
        adjacencies.get(from).add(new Node(to));
        if (isDirected == false) {
            adjacencies.get(to).add(new Node(from));
        }
        return true;
    }

    public void printGraph() {
        for (int i = 0; i < adjacencies.size(); i++) {
            System.out.print(i);
            LinkedList<Node> nodeLL = adjacencies.get(i);
            for (Node node : nodeLL) {
                System.out.print("->" + node.id);
            }
            System.out.println();
        }
    }

    public boolean hasEdge(int source, int destination) {
        return this.adjacencies.get(source).contains(new Node(destination));
    }

    /**
     * Not allow multi edge or self loop
     */
    public static Graph generateRandomGraph(int numVertices, int numEdges, boolean isDirected) {
        if (isDirected && numEdges > numVertices * numVertices / 2
            || !isDirected && numEdges > numVertices * numVertices) {
                throw new IllegalArgumentException("Cannot have that many edges!");
            }

        Graph g = new Graph(numVertices, isDirected);
        for (int i = 0; i < numEdges; i++) {
            int vertex1 = (int)(Math.random() * numVertices);
            int vertex2 = (int)(Math.random() * numVertices);
            if (!g.addEdge(vertex1, vertex2, isDirected)) { // If fail to add edge
                i--;
                continue;
            }
        }

        return g;
    }
}