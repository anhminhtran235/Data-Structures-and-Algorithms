package Graph.test.unweighted;

import Graph.main.unweighted.Graph;

public class testGraph {
    public static void main(String[] args) {
        int numVertices = 6;
        boolean isDirected = true;
        Graph g = new Graph(numVertices, isDirected);
        g.addEdge(1, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 4);
        g.addEdge(3, 5);
        g.addEdge(1, 4);
        g.addEdge(1, 5);
        g.addEdge(4, 2);
        g.addEdge(4, 5);
        System.out.println("Graph " + (isDirected ? "(directed):" : "(undirected):"));
        g.printGraph();
    }
}