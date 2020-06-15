package Graph.test.unweighted;

import Graph.main.unweighted.BreadthFirstSearch;
import Graph.main.unweighted.Graph;

public class testBreadthFirstSearch {
    public static void main(String[] args) {
        int numVertices = 5;
        int numEdges = 7;
        boolean isDirected = true;
        Graph g = Graph.generateRandomGraph(numVertices, numEdges, isDirected); // Allow multi edge and self loop

        System.out.println("Graph: ");
        g.printGraph();
        System.out.print("BFS from node 1: ");
        BreadthFirstSearch.printBFS(g, 1);
        System.out.println();
        System.out.print("BFS the whole graph: ");
        BreadthFirstSearch.printBFS(g);
        System.out.println();
        System.out.print("Path from 1 to 2: ");
        BreadthFirstSearch.findPath(g, 1, 2);
    }
}