package Graph.main.weighted;

import java.util.*;

public class BellmanFordIterative {
    public static void printShortestPaths(Graph g, int source) {
        // Ex: dp[1][2]: shortest distance from source to '1', going through at most '2' nodes
        long[][] dp = new long[g.numNodes][g.numNodes+1];
        // Init dp table
        for (int i = 0; i < g.numNodes; i++) {
            dp[i][0] = (i == source) ? 0 : Integer.MAX_VALUE;
        }

        // The heart of BellmanFord
        for (int j = 1; j < g.numNodes; j++) {
            copyCol(dp, j-1, j);
            for (int i = 0; i < g.numNodes; i++) {
                for (Graph.Node node : g.adjacencies.get(i)) {
                    if (dp[node.id][j] > dp[i][j-1] + node.weight) {
                        dp[node.id][j] = dp[i][j-1] + node.weight;
                    }
                }
            }
        }

        // Check for negative cycles
        boolean hasNegativeWeight = false;
        for (int i = 0; i < g.numNodes; i++) {
            for (Graph.Node node : g.adjacencies.get(i)) {
                if (dp[node.id][g.numNodes] > dp[i][g.numNodes-1] + node.weight) {
                    hasNegativeWeight = true;
                    
                }
            }
        }

        if (!hasNegativeWeight) {
            System.out.print("Shortest distances from " + source + " to the rest: ");
            for (int i = 0; i < g.numNodes; i++) {
                System.out.print(dp[i][g.numNodes-1] + " ");
            }
        } else {
            System.out.println("Negative cycle exists");
        }
    }

    private static void copyCol (long[][] arr, int fromCol, int toCol) {
        for (int i = 0; i < arr.length; i++) {
            arr[i][toCol] = arr[i][fromCol];
         }
    }

    public static void printShortestPathsBetter(Graph g, int source) {
        // Init dp table (distance table)
        long[] dp = new long[g.numNodes + 1]; // Ex: dp[1] - shortest path so far from source to node 1
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[source] = 0;

        // We can also implement BellmanFord with edges list representation
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < g.numNodes; i++) {
            for (Graph.Node adjNode : g.adjacencies.get(i)) {
                edges.add(new Edge(i, adjNode.id, adjNode.weight));
            }
        }
        
        // The heart of BellmanFord
        for (int i = 0; i < g.numNodes; i++) {
            for (int j = 0; j < edges.size(); j++) {
                Edge e = edges.get(j);
                if (dp[e.node2] > dp[e.node1] + e.weight) {
                    dp[e.node2] = dp[e.node1] + e.weight;
                }
            }
        }

        // Run this loop another tim to check for negative cycles
        for (int j = 0; j < edges.size(); j++) {
            Edge e = edges.get(j);
            if (dp[e.node2] > dp[e.node1] + e.weight) {
                System.out.print("Negative cycle exists");
                return;
            }
        }

        // Print shortest distances if there are no negative cycles
        System.out.print("Shortest distance from " + source + " to the rest: ");
        for (long i : dp) {
            System.out.print(i + " ");
        }
    }
}