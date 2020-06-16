package Graph.main.unweighted;

import java.security.InvalidParameterException;

public class TopologicalSort {
    public static void topoSortDFS(Graph g) {
        if (g == null) {
            throw new InvalidParameterException("null graph");
        } else if (!g.isDirected) {
            throw new InvalidParameterException("Topo sort only works for directed graph");
        }

        if (CycleDetection.hasCycleDFS(g)) {
            System.out.println("Graph contains a cycle. Topo ordering does not exist");
            return;
        } 

        boolean[] visited = new boolean[g.numNodes];
        for (int i = 0; i < g.numNodes; i++) {
            if (!visited[i]) {
                printTopoDFS(g, new Graph.Node(i), visited);
            }
        }
    }

    private static void printTopoDFS(Graph g, Graph.Node source, boolean[] visited) {
        visited[source.id] = true;
        for (Graph.Node adjNode: g.nodes.get(source.id)) {
            if (!visited[adjNode.id]) {
                printTopoDFS(g, adjNode, visited);
            }
        }
        System.out.print(source.id + " ");
    }
}