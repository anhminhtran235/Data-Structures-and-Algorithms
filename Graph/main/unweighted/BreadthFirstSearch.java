package Graph.main.unweighted;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A class that contains implementation of BFS
 */
public class BreadthFirstSearch {

    /**
     * Print BFS the whole graph
     */
    public static void printBFS(Graph g) {
        if (g == null) {
            throw new InvalidParameterException("null graph");
        }

        boolean[] visited = new boolean[g.numNodes];
        int[] parent = new int[g.numNodes];
        Arrays.fill(parent, -1);

        for (int i = 0; i < g.numNodes; i++) {
            if (!visited[i]) {
                printBFS(g, i, visited, parent);
            }
        }
    }

    /**
     * Print BFS from start node
     */
    public static void printBFS(Graph g, int start) {
        if (g == null) {
            throw new InvalidParameterException("null graph");
        } else if (start >= g.numNodes || start < 0) {
            throw new IndexOutOfBoundsException("Invalid starting point: " + start);
        }

        boolean[] visited = new boolean[g.numNodes];
        int[] parent = new int[g.numNodes];
        Arrays.fill(parent, -1);
        printBFS(g, start, visited, parent);
    }

    /**
     * Main logic of bfs
     */
    private static void printBFS(Graph g, int start, boolean[] visited, int[] parent) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + "(parent:" + parent[node] + ") ");
            EdgeNode p = g.edges.get(node);
            while (p != null) {
                int successorNode = p.y;
                if (!visited[successorNode]) {
                    visited[successorNode] = true;
                    parent[successorNode] = node;
                    queue.add(successorNode);
                }
                p = p.next;
            }
        }
    }
}