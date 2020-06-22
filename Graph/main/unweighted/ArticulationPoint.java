package Graph.main.unweighted;

import java.util.*;

public class ArticulationPoint {
    private static int id;
    
    public static void printArticulationPoint(Graph g) {
        if (g.isDirected) {
            System.out.println("Directed graph doesn't have articulation point!");
        } else if (!isGraphConnected(g)) {
            System.out.println("The graph is not connected in the first place");
        } else {
            id = 0;
            int[] ids = new int[g.numNodes];
            int[] low = new int[g.numNodes];
            int[] parent = new int[g.numNodes];
            Arrays.fill(ids, -1);
            Arrays.fill(parent, -1);
            DFS(g, 0, ids, low, parent);
        }
    }

    private static void DFS(Graph g, int at, int[] ids, int[] low, int[] parent) {
        ids[at] = low[at] = id++;
        boolean isArticulation = false;
        int childCount = 0;

        for (Graph.Node adj: g.adjacencies.get(at)) {
            if (ids[adj.id] == -1) {
                parent[adj.id] = at;
                DFS(g, adj.id, ids, low, parent);
                childCount++;
                if (low[adj.id] >= ids[at]) {
                    isArticulation = true;
                }
                low[at] = Math.min(low[at], low[adj.id]);
            } else if (adj.id != parent[at]) {
                low[at] = Math.min(low[at], ids[adj.id]);
            }
        }
        if (parent[at] != -1 && isArticulation
            || parent[at] == -1 && childCount > 1) {
                System.out.print(at + " ");
            }
    }

    /**
     * Traverse the whole graph from node 0. See if numVisited == g.numNodes
     */
    private static boolean isGraphConnected(Graph g) {
        if (g.isDirected) {
            throw new IllegalArgumentException("This function is not written for directed graph");
        } 
        int numVerticesVisited = 0;
        boolean[] visited = new boolean[g.numNodes];
        Queue<Graph.Node> queue = new LinkedList<>();
        queue.add(new Graph.Node(0));
        visited[0] = true;

        while (!queue.isEmpty()) {
            Graph.Node node = queue.poll();
            numVerticesVisited++;
            for (Graph.Node adjNode: g.adjacencies.get(node.id)) {
                if (!visited[adjNode.id]) {
                    visited[adjNode.id] = true;
                    queue.add(adjNode);
                }
            }
        }

        return numVerticesVisited == g.numNodes;
    }
}