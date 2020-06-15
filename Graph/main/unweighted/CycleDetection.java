package Graph.main.unweighted;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class CycleDetection {
    
    // Graph has cycle = Graph has back edge
    public static boolean hasCycle(Graph g) {
        if (g == null) {
            throw new InvalidParameterException("null graph");
        }
        
        boolean[] discovered = new boolean[g.numNodes];
        boolean[] processing = new boolean[g.numNodes];
        int[] parent = new int[g.numNodes];
        Arrays.fill(parent, -1);

        for (int i = 0; i < g.numNodes; i++) {
            if (discovered[i]) {
                if (hasCycle(g, i, discovered, processing, parent)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean hasCycle(Graph g, int start, boolean[] discovered, boolean[] processing, int[] parent) {
        discovered[start] = true;
        processing[start] = true;
        Graph.Node p = g.nodes.get(start);
        while (p != null) {
            if (!g.isDirected && discovered[p.id] && parent[p.id] != start) { // Back edge (undirected graph)
                return true;
            } else if (g.isDirected && processing[p.id]) {  // Back edge (directed graph)
                return true;
            } else {
                parent[p.id] = start;
                if (hasCycle(g, p.id, discovered, processing, parent)) {
                    return true;
                }
                p = p.next;
            }
        }
        processing[start] = false;
        return false;
    }
}