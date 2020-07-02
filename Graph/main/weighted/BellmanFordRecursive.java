package Graph.main.weighted;

public class BellmanFordRecursive {
    public static void printShortestPaths(Graph g, int source) {
        Graph gReverse = new Graph(g.numNodes, g.isDirected);
        for (int i = 0; i < g.numNodes; i++) {
            for (Graph.Node adj : g.adjacencies.get(i)) {
                gReverse.addEdge(adj.id, i, adj.weight);
            }
        }

        long[] distance = new long[g.numNodes];
        for (int i = 0; i < g.numNodes; i++) {
            distance[i] = shortestPathHelper(gReverse, source, i, g.numNodes - 1);
        }

        System.out.print("Shortest paths from " + source + " to the rest: ");
        for (long i : distance) {
            String info; 
            if (i == Integer.MAX_VALUE) info = "Infinity";
            else if (i == Integer.MIN_VALUE) info = "-Infinity";
            else info = i + "";
            System.out.print(info + " ");
        }

        System.out.println();
        System.out.println("Note: The current implementation of BellmanFord Recursive doesn't check negative cycle");
    }

    public static int shortestPathHelper(Graph gReverse, int source, int dest, int k) {
        if (k == 0) {
            return (dest == source) ? 0 : Integer.MAX_VALUE;
        }

        int minDistance = Integer.MAX_VALUE;
        if (source == dest) minDistance = 0;
        for (Graph.Node adj : gReverse.adjacencies.get(dest)) {
            int distToAdj = shortestPathHelper(gReverse, source, adj.id, k - 1);
            if (distToAdj != Integer.MAX_VALUE && minDistance > distToAdj + adj.weight) {
                minDistance = distToAdj + adj.weight;
            }
        }

        return minDistance;
    }
}