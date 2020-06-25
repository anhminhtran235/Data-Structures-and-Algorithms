package Graph.test.weighted;

import Graph.main.weighted.DijkstraBinaryHeap;
import Graph.main.weighted.DijkstraNaive;
import Graph.main.weighted.Graph;

public class testDijkstraHeap {
    public static void main(String[] args) {
        int numVertices = 15;
        int numEdges = 50;
        boolean isDirected = true;
        boolean allowNegativeWeight = false;
        Graph g = Graph.generateRandomGraph(numVertices, numEdges, isDirected, allowNegativeWeight);

        System.out.println("Graph " + (isDirected ? "(directed):" : "(undirected):"));
        g.printGraph();
        System.out.println();
        System.out.println("Dijkstra Binary Heap: ");
        DijkstraBinaryHeap.printShortestPath(g, 1, 2);
        DijkstraBinaryHeap.printShortestPath(g, 1, 3);
        DijkstraBinaryHeap.printShortestPath(g, 2, 1);
        DijkstraBinaryHeap.printShortestPath(g, 3, 4);
        System.out.println();
        System.out.println("Dijkstra Naive: ");
        DijkstraNaive.printShortestPath(g, 1, 2);
        DijkstraNaive.printShortestPath(g, 1, 3);
        DijkstraNaive.printShortestPath(g, 2, 1);
        DijkstraNaive.printShortestPath(g, 3, 4);
    }
}