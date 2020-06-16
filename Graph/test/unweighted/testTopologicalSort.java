package Graph.test.unweighted;

import Graph.main.unweighted.Graph;
import Graph.main.unweighted.TopologicalSort;

public class testTopologicalSort {
    public static void main(String[] args) {
        int numVertices = 5;
        int numEdges = 3;
        boolean isDirected = true;
        Graph g = Graph.generateRandomGraph(numVertices, numEdges, isDirected);

        System.out.println("Graph " + (isDirected ? "(directed):" : "(undirected):"));
        g.printGraph();
        System.out.println();
        System.out.print("Topo sort DFS: ");
        TopologicalSort.topoSortDFS(g);
        System.out.println();
        System.out.print("Topo sort BFS: ");
        TopologicalSort.topoSortBFS(g);
    }
}