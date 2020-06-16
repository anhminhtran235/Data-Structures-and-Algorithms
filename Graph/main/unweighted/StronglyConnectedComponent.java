package Graph.main.unweighted;

import java.util.*;

public class StronglyConnectedComponent {
    public static void printStronglyConnectedComponent(Graph g) {
        if (!g.isDirected) {
            boolean[] visited = new boolean[g.numNodes];
            int count = 1;
            for (int i = 0; i < g.numNodes; i++) {
                if (!visited[i]) {
                    printStronglyConnectedComponentUndirected(g, i, visited, count++);
                }
            }
        }
    }

    // A simple BFS
    private static void printStronglyConnectedComponentUndirected(Graph g, int start, boolean[] visited, int count) {
        Queue<Graph.Node> queue = new LinkedList<>();
        queue.add(new Graph.Node(start));
        visited[start] = true;
        System.out.print("Strongly connected component #" + count + ": ");
        while (!queue.isEmpty()) {
            Graph.Node node = queue.poll();
            System.out.print(node.id + " ");
            for (Graph.Node adjNode: g.nodes.get(node.id)) {
                if (!visited[adjNode.id]) {
                    queue.add(adjNode);
                    visited[adjNode.id] = true;
                }
            }
        }
        System.out.println();
    }
}