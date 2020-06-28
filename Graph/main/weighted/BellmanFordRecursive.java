package Graph.main.weighted;

import java.util.Arrays;

public class BellmanFordRecursive {
    public static void printShortestPaths(Graph g, int source) {
        // Build an adjacency matrix
        int[][] matrix = new int[g.numNodes][g.numNodes];
        for (int[] arr : matrix) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        for (int i = 0; i < g.numNodes; i++) {
            matrix[i][i] = 0;
        }
        for (int i = 0; i < g.numNodes; i++) {
            for (Graph.Node adjNode : g.adjacencies.get(i)) {
                matrix[i][adjNode.id] = adjNode.weight;
            }
        }

        long[][] distance = new long[g.numNodes][g.numNodes];
        for (long[] arr: distance) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        for (int i = 0; i < g.numNodes; i++) {
            distance[source][i] = 0;
        }
        for (int i = 0; i < g.numNodes; i++) {
            shortestPath(matrix, distance, source, i, g.numNodes - 1);
        }
    }

    // Find the shortest path from source to dest which goes through at most k edges
    private static long shortestPath(int[][] matrix, long[][] dist, int source, int dest, int k) {
        if (k == 0) {
            return (source == dest) ? 0 : Integer.MAX_VALUE;
        }
        
        if (dist[dest][k] == Integer.MAX_VALUE) {  
            long minDistance = Integer.MAX_VALUE;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][dest] != Integer.MAX_VALUE) {
                    minDistance = Math.min(minDistance, shortestPath(matrix, dist, source, j, k - 1) + matrix[j][dest]);
                }
            }
            dist[dest][k] = minDistance;
        }

        return dist[dest][k];
    }
}