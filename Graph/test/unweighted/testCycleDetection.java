package Graph.test.unweighted;

import Graph.main.unweighted.CycleDetection;
import Graph.main.unweighted.Graph;

public class testCycleDetection {
    public static void main(String[] args) {
        int numVertices = 5;
        int numEdges = 3;
        boolean isDirected = false;
        Graph g = Graph.generateRandomGraph(numVertices, numEdges, isDirected); // Not allow multi edge or self loop

        System.out.println("Graph " + (isDirected ? "(directed):" : "(undirected):"));
        g.printGraph();
        boolean hasCycle = CycleDetection.hasCycleDFS(g); 
        if (hasCycle) {
            System.out.println("The graph has a cycle");
        } else {
            System.out.println("The graph has no cycle");
        }
    }
}