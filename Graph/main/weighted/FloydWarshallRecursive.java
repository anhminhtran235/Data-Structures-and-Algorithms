package Graph.main.weighted;

public class FloydWarshallRecursive {
    public static void printAllPairShortestPaths(Graph g) {
        int[][] matrix = new int[g.numNodes][g.numNodes];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i != j) {
                    matrix[i][j] = 1000000;
                }
            }
        }
        for (int i = 0; i < g.numNodes; i++) {
            for (Graph.Node adj : g.adjacencies.get(i)) {
                matrix[i][adj.id] = adj.weight;
                if (!g.isDirected) {
                    matrix[adj.id][i] = adj.weight;
                }
            }
        }

        int[][] distance = new int[g.numNodes][g.numNodes];
        for (int i = 0; i < g.numNodes; i++) {
            for (int j = 0; j < g.numNodes; j++) {
                distance[i][j] = shortestPathsHelper(matrix, i, j, g.numNodes - 1);
            }
        }

        for (int i = 0; i < g.numNodes; i++) {
            System.out.print("Shortest distances from " + i + " to the rest: ");
            for (int dist : distance[i]) {
                System.out.print(dist + " ");
            }
            System.out.println();
        }
    }

    public static int shortestPathsHelper(int[][] matrix, int source, int dest, int k) {
        if (k == -1) {
            return matrix[source][dest];
        }

        return Math.min(shortestPathsHelper(matrix, source, dest, k - 1), 
                shortestPathsHelper(matrix, source, k, k-1) + shortestPathsHelper(matrix, k, dest, k - 1));
    }
}