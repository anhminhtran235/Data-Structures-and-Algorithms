package Graph.test.weighted;

import Graph.main.weighted.Graph;
import Graph.main.weighted.Prim;

public class testPrim {
    public static void main(String[] args) {
        int numVertices = 4;
        int numEdges = 5;
        boolean isDirected = false;
        boolean allowNegativeWeight = true;
        Graph g = Graph.generateRandomGraph(numVertices, numEdges, isDirected, allowNegativeWeight);

        System.out.println("Graph " + (isDirected ? "(directed):" : "(undirected):"));
        g.printGraph();
        Prim.printMinimumSpanningTree(g);
    }
}