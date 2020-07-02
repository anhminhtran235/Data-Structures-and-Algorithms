package Graph.test.weighted;

import Graph.main.weighted.FloydWarshall;
import Graph.main.weighted.Graph;

public class testFloydWarshall {
    public static void main(String[] args) {
        int numVertices = 10;
        int numEdges = 20;
        boolean isDirected = true;
        boolean allowNegativeWeight = false;
        Graph g = Graph.generateRandomGraph(numVertices, numEdges, isDirected, allowNegativeWeight);

        g.printGraph();
        System.out.println("All pair shortest paths: ");
        FloydWarshall.printAllPairShortestPaths(g);
    }
}