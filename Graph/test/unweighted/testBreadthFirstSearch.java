package Graph.test.unweighted;

import Graph.main.unweighted.BreadthFirstSearch;
import Graph.main.unweighted.Graph;

public class testBreadthFirstSearch {
    public static void main(String[] args) {
        Graph g = new Graph(6, false); 
        g.addEdge(1, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 4);
        g.addEdge(3, 5);
        g.addEdge(1, 4);
        g.addEdge(1, 5);
        g.addEdge(4, 2);
        g.addEdge(4, 5);
        System.out.print("BFS from node 1: ");
        BreadthFirstSearch.printBFS(g, 1);
        System.out.println();
        System.out.print("BFS the whole graph: ");
        BreadthFirstSearch.printBFS(g);
    }
}