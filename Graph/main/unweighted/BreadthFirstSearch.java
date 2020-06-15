package Graph.main.unweighted;

import java.security.InvalidParameterException;
import java.util.*;

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
                bfs(g, i, visited, parent, true);
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
        bfs(g, start, visited, parent, true);
    }

    /**
     * Main logic of bfs
     */
    private static void bfs(Graph g, int start, boolean[] visited, int[] parent, boolean print) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (print) {
                System.out.print(node + "(parent:" + parent[node] + ") ");
            }
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

    public static void findPath(Graph g, int source, int destination) {
        if (g == null) {
            throw new InvalidParameterException("null graph");
        } else if (source >= g.numNodes || source < 0) {
            throw new IndexOutOfBoundsException("Invalid source: " + source);
        } else if (destination >= g.numNodes || destination < 0) {
            throw new IndexOutOfBoundsException("Invalid destination: " + destination);
        }

        boolean[] visited = new boolean[g.numNodes];
        int[] parent = new int[g.numNodes];
        Arrays.fill(parent, -1);
        
        bfs(g, source, visited, parent, false);       
        printPath(source, destination, parent);
    } 

    private static void printPath(int source, int destination, int[] parent) {
        if (parent[destination] == -1) {
            System.out.println("Path from " + source + " to " + destination + " does not exist");
        } else {
            Stack<Integer> stack = new Stack<>();
            int node = destination;
            while (node != source) {
                if (node == -1) {
                    System.out.println("Something goes wrong when printing path");
                    return;
                }
                stack.push(node);
                node = parent[node];
            }
            System.out.print(source);
            while (!stack.isEmpty()) {
                System.out.print("->" + stack.pop());
            }
        }
    }
}