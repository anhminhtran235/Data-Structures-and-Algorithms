package Graph.test.unweighted;

import Graph.main.unweighted.DepthFirstSearch;
import Graph.main.unweighted.Graph;

/**
 * testDepthFirstSearch
 */
public class testDepthFirstSearch {

    public static void main(String[] args) {
        int numVertices = 5;
        int numEdges = 7;
        boolean isDirected = true;
        Graph g = Graph.generateRandomGraph(numVertices, numEdges, isDirected); // Allow multi edge and self loop

        System.out.println("Graph " + (isDirected ? "(directed):" : "(undirected):"));
        g.printGraph();
        System.out.print("DFS from node 1: ");
        DepthFirstSearch.printDFS(g, 1);
        System.out.println();
        System.out.print("DFS the whole graph: ");
        DepthFirstSearch.printDFS(g);
        System.out.println();
        System.out.print("Path from 1 to 2: ");
        DepthFirstSearch.findPath(g, 1, 2);
    }
}