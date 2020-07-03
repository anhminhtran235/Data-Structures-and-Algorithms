package Graph.main.weighted;

public class FloydWarshallIterative {
    public static void correctAllPairShortestPaths(Graph g) {
        double[][] dp = new double[g.numNodes][g.numNodes];
        int n = g.numNodes;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                if (i != j) {
                    dp[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        for (int i = 0; i < g.numNodes; i++) {
            for (Graph.Node adj : g.adjacencies.get(i)) {
                dp[i][adj.id] = adj.weight;
                if (!g.isDirected) {
                    dp[adj.id][i] = adj.weight;
                }
            }
        }
        // Compute all pairs shortest paths.
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }
    
        // Identify negative cycles by propagating the value 'NEGATIVE_INFINITY'
        // to every edge that is part of or reaches into a negative cycle.
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = Double.NEGATIVE_INFINITY;
                    }

        
        for (int i = 0; i < g.numNodes; i++) {
            System.out.print("Shortest distance from " + i + " to the rest: ");
            for (int j = 0; j < g.numNodes; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printAllPairShortestPaths(Graph g) {
        int[][] matrix = new int[g.numNodes][g.numNodes];
        int[][] distance = new int[g.numNodes][g.numNodes];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i != j) {
                    matrix[i][j] = Integer.MAX_VALUE;
                    distance[i][j] = Integer.MAX_VALUE;
                } else {
                    distance[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < g.numNodes; i++) {
            for (Graph.Node adj : g.adjacencies.get(i)) {
                matrix[i][adj.id] = adj.weight;
                distance[i][adj.id] = adj.weight;
                if (!g.isDirected) {
                    matrix[adj.id][i] = adj.weight;
                    distance[adj.id][i] = adj.weight;
                }
            }
        }

        for (int k = 0; k < g.numNodes; k++) {
            for (int i = 0; i < g.numNodes; i++) {
                for (int j = 0; j < g.numNodes; j++) {
                    if (distance[i][k] != Integer.MAX_VALUE &&
                        matrix[k][j] != Integer.MAX_VALUE) {

                        if (distance[i][j] > distance[i][k] + matrix[k][j]) {
                            distance[i][j] = distance[i][k] + matrix[k][j];
                        }
                    }
                }
            }
        }

        for (int k = 0; k < g.numNodes; k++) {
            for (int i = 0; i < g.numNodes; i++) {
                for (int j = 0; j < g.numNodes; j++) {
                    if (distance[i][k] != Integer.MAX_VALUE &&
                        matrix[k][j] != Integer.MAX_VALUE) {

                        if (distance[i][j] > distance[i][k] + matrix[k][j]) {
                            distance[i][j] = Integer.MIN_VALUE;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < g.numNodes; i++) {
            System.out.print("Shortest distance from " + i + " to the rest: ");
            for (int j = 0; j < g.numNodes; j++) {
                String res;
                if (distance[i][j] == Integer.MAX_VALUE) res = "Infinity";
                else if (distance[i][j] == Integer.MIN_VALUE) res = "-Infinity";
                else res = distance[i][j] + "";
                System.out.print(res + " ");
            }
            System.out.println();
        }
    }
}
