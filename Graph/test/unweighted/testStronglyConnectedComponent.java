package Graph.test.unweighted;

import Graph.main.unweighted.Graph;
import Graph.main.unweighted.StronglyConnectedComponent;

public class testStronglyConnectedComponent {
    public static void main(String[] args) {
        /* Undirected graph */
        int numVertices = 5;
        int numEdges = 4;
        boolean isDirected = false;
        Graph g = Graph.generateRandomGraph(numVertices, numEdges, isDirected);

        System.out.println("Graph " + (isDirected ? "(directed):" : "(undirected):"));
        g.printGraph();

        System.out.println("Print all strongly connected components: ");
        StronglyConnectedComponent.printSCC(g);

        /* Directed graph */
        numVertices = 5;
        numEdges = 8;
        isDirected = true;
        g = Graph.generateRandomGraph(numVertices, numEdges, isDirected);

        System.out.println("Graph " + (isDirected ? "(directed):" : "(undirected):"));
        g.printGraph();

        System.out.println("Print all strongly connected components: ");
        StronglyConnectedComponent.printSCC(g);
    }
}