package Graph.test.weighted;

import Graph.main.weighted.DijkstraNaive;
import Graph.main.weighted.Graph;

public class testDijkstraNaive {
    public static void main(String[] args) {
        int numVertices = 5;
        int numEdges = 12;
        boolean isDirected = true;
        boolean allowNegativeWeight = false;
        Graph g = Graph.generateRandomGraph(numVertices, numEdges, isDirected, allowNegativeWeight);

        System.out.println("Graph " + (isDirected ? "(directed):" : "(undirected):"));
        g.printGraph();
        DijkstraNaive.printShortestPath(g, 1, 2);
        DijkstraNaive.printShortestPath(g, 1, 3);
        DijkstraNaive.printShortestPath(g, 2, 1);
        DijkstraNaive.printShortestPath(g, 3, 4);

    }
}