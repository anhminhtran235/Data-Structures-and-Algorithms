package Graph.test.weighted;

import Graph.main.weighted.BellmanFordRecursive;
import Graph.main.weighted.Graph;

public class testBellmanFord {
    public static void main(String[] args) {
        int numVertices = 4;
        int numEdges = 5;
        boolean isDirected = true;
        boolean allowNegativeWeight = true;
        Graph g = Graph.generateRandomGraph(numVertices, numEdges, isDirected, allowNegativeWeight);

        System.out.println("Graph " + (isDirected ? "(directed):" : "(undirected):"));
        g.printGraph();
        BellmanFordRecursive.printShortestPaths(g, 0);
    }
}