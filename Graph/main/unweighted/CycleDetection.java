package Graph.main.unweighted;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class CycleDetection {
    
    // Graph has cycle = Graph has back edge
    public static boolean hasCycleDFS(Graph g) {
        if (g == null) {
            throw new InvalidParameterException("null graph");
        }
        
        boolean[] discovered = new boolean[g.numNodes];
        boolean[] processing = new boolean[g.numNodes];
        int[] parent = new int[g.numNodes];
        Arrays.fill(parent, -1);

        for (int i = 0; i < g.numNodes; i++) {
            if (!discovered[i]) {
                if (hasCycleDFS(g, new Graph.Node(i), discovered, processing, parent)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean hasCycleDFS(Graph g, Graph.Node start, boolean[] discovered, boolean[] processing, int[] parent) {
        discovered[start.id] = true;
        processing[start.id] = true;
       for (Graph.Node node: g.adjacencies.get(start.id)) {
            if (!g.isDirected && discovered[node.id] && parent[start.id] != node.id) { // Back edge (undirected graph)
                return true;
            } else if (g.isDirected && processing[node.id]) {  // Back edge (directed graph)
                return true;
            } else if (!discovered[node.id]) {
                parent[node.id] = start.id;
                if (hasCycleDFS(g, node, discovered, processing, parent)) {
                    return true;
                }
            }
        }
        processing[start.id] = false;
        return false;
    }
}