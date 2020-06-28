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

        long[][] distance = new long[g.numNodes][g.numNodes + 1];
        for (long[] arr: distance) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        for (int i = 0; i < g.numNodes; i++) {
            distance[source][i] = 0; // Shortest distance from source to source goes through at most i edges = 0
        }
        for (int i = 0; i < g.numNodes; i++) {
            shortestPath(matrix, distance, source, i, g.numNodes);
        }

        for (int i = 0; i < g.numNodes; i++) {
            shortestPath(matrix, distance, source, i, g.numNodes);
        }
        boolean hasNegativeCycle = false;
        for (int i = 0; i < g.numNodes; i++) {
            if (distance[i][g.numNodes] != distance[i][g.numNodes-1]) {
                hasNegativeCycle = true;
            }
        }
        if (!hasNegativeCycle) {
            System.out.print("Shortest distances from " + source + " to the rest: ");
            for (int i = 0; i < g.numNodes; i++) {
                System.out.print(distance[i][g.numNodes-1] + " ");
            }
        } else {
            System.out.println("Negative cycle exist!");
        }
    }

    // Find the shortest path from source to dest, going through at most k edges
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