package Graph.main.weighted;

import java.util.*;

/**
 * Implementation of weighted Graph using adjacency list representation
 * This graph can be either undirected or directed.
 * Multi-edge and self loop are not allowed
 */
public class Graph {

    public List<LinkedList<Node>> adjacencies;
    public boolean isDirected;
    public int numNodes;
    public static class Node {
        public int id;
        public int weight;

        public Node(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
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
        this.numNodes = numNodes;
        this.isDirected = isDirected;
        this.adjacencies = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            this.adjacencies.add(new LinkedList<Node>());
        }
    }

    public boolean addEdge(int from, int to, int weight) {
        if (from == to || this.adjacencies.get(from).contains(new Node(to))) {
            return false;
        }
        adjacencies.get(from).add(new Node(to, weight));
        if (!this.isDirected) {
            adjacencies.get(to).add(new Node(from, weight));
        }
        return true;
    }

    public boolean deleteEdge(int from, int to) {
        boolean edgeExists = adjacencies.get(from).removeIf(node -> node.id == to);
        if (edgeExists) {
            adjacencies.get(to).removeIf(node -> node.id == from);
            return true;
        }
        return false;
    }

    public void printGraph() {
        for (int i = 0; i < adjacencies.size(); i++) {
            System.out.print(i);
            LinkedList<Node> nodeLL = adjacencies.get(i);
            for (Node node : nodeLL) {
                System.out.print("->" + node.id + "(weight: " + node.weight + ")");
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
    public static Graph generateRandomGraph(int numVertices, int numEdges, boolean isDirected, boolean negativeWeight) {
        if (!isDirected && numEdges > numVertices * (numVertices - 1) / 2
            || isDirected && numEdges > numVertices * (numVertices-1)) {
                throw new IllegalArgumentException("Cannot have that many edges!");
            }

        final int MAX_WEIGHT = 10;
        Graph g = new Graph(numVertices, isDirected);
        for (int i = 0; i < numEdges; i++) {
            int vertex1 = (int)(Math.random() * numVertices);
            int vertex2 = (int)(Math.random() * numVertices);
            int weight = (int)(Math.random() * MAX_WEIGHT);
            if (negativeWeight && Math.random() < 0.5) {
                weight *= -1;
            }
            if (!g.addEdge(vertex1, vertex2, weight)) { // If fail to add edge
                i--;
                continue;
            }
        }

        return g;
    }
}