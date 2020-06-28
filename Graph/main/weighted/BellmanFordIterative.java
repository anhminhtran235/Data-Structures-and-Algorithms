package Graph.main.weighted;

public class BellmanFordIterative {
    public static void printShortestPaths(Graph g, int source) {
        // Ex: dp[1][2]: shortest distance from source to '1', going through at most '2' nodes
        long[][] dp = new long[g.numNodes][g.numNodes+1];
        // Init dp table
        for (int i = 0; i < g.numNodes; i++) {
            dp[i][0] = (i == source) ? 0 : Integer.MAX_VALUE;
        }

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

        boolean hasNegativeWeight = false;
        for (int i = 0; i < g.numNodes; i++) {
            for (Graph.Node node : g.adjacencies.get(i)) {
                if (dp[node.id][g.numNodes] > dp[i][g.numNodes-1] + node.weight) {
                    hasNegativeWeight = true;
                }
            }
        }

        if (!hasNegativeWeight) {
            System.out.print("Shortest path from " + source + " to the rest: ");
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
}