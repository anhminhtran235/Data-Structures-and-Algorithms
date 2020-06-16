package Graph.test.unweighted;

import Graph.main.unweighted.Graph;
import Graph.main.unweighted.StronglyConnectedComponent;

public class testStronglyConnectedComponent {
    public static void main(String[] args) {
        int numVertices = 50;
        int numEdges = 40;
        boolean isDirected = false;
        Graph g = Graph.generateRandomGraph(numVertices, numEdges, isDirected);

        System.out.println("Graph " + (isDirected ? "(directed):" : "(undirected):"));
        g.printGraph();

        System.out.println("Print all strongly connected components: ");
        StronglyConnectedComponent.printStronglyConnectedComponent(g);
    }
}