package Graph.test.weighted;

import Graph.main.weighted.BellmanFordIterative;
import Graph.main.weighted.BellmanFordRecursive;
import Graph.main.weighted.Graph;

public class testBellmanFord {
    public static void main(String[] args) {
        int numVertices = 35;
        int numEdges = 800;
        boolean isDirected = true;
        boolean allowNegativeWeight = false;
        Graph g = Graph.generateRandomGraph(numVertices, numEdges, isDirected, allowNegativeWeight);

        System.out.println("Graph " + (isDirected ? "(directed):" : "(undirected):"));
        g.printGraph();
        System.out.print("Recursive BellmanFord - ");
        BellmanFordRecursive.printShortestPaths(g, 0);
        System.out.println();
        System.out.print("Iterative BellmanFord - ");
        BellmanFordIterative.printShortestPaths(g, 0);
        System.out.println();
        System.out.print("Iterative BellmanFord (optimize space) - ");
        BellmanFordIterative.printShortestPaths(g, 0);
    }
}