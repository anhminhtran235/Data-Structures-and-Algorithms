package Graph.test.unweighted;

import Graph.main.unweighted.ArticulationPoint;
import Graph.main.unweighted.Graph;

public class testArticulationPoint {
    public static void main(String[] args) {
        int numVertices = 5;
        int numEdges = 5;
        boolean isDirected = false;
        Graph g = Graph.generateRandomGraph(numVertices, numEdges, isDirected); // Not allow multi edge or self loop

        System.out.println("Graph " + (isDirected ? "(directed):" : "(undirected):"));
        g.printGraph();
        System.out.print("Articulation point: ");
        ArticulationPoint.printArticulationPoint(g);
    }
}