package Graph.test.weighted;

import Graph.main.weighted.*;

public class testKruskal {
    public static void main(String[] args) {
        int numVertices = 400;
        int numEdges = 10000;
        boolean isDirected = false;
        boolean allowNegativeWeight = true;
        Graph g = Graph.generateRandomGraph(numVertices, numEdges, isDirected, allowNegativeWeight);

        System.out.println("Graph " + (isDirected ? "(directed):" : "(undirected):"));
        g.printGraph();

        System.out.println("Prim MST: ");
        Prim.printMST(g);
        System.out.println();

        System.out.println("Kruskal MST: ");
        Kruskal.printMST(g);
    }
}