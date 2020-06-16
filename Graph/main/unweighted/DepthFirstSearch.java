package Graph.main.unweighted;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Stack;

/**
 * A class that contains implementation of DFS
 */
public class DepthFirstSearch {
    private static int time;

    /**
     * Print DFS the whole graph
     */
    public static void printDFS(Graph g) {
        if (g == null) {
            throw new InvalidParameterException("null graph");
        }

        time = 0;
        boolean[] visited = new boolean[g.numNodes];
        int[] parent = new int[g.numNodes];
        Arrays.fill(parent, -1);
        int[] entryTime = new int[g.numNodes];
        int[] exitTime = new int[g.numNodes];

        for (int i = 0 ; i < g.numNodes; i++) {
            if (!visited[i]) {
                dfs(g, new Graph.Node(i), visited, parent, entryTime, exitTime, true);
            }
        }
    }

    /**
     * Print DFS from start node
     */
    public static void printDFS(Graph g, int start) {
        if (g == null) {
            throw new InvalidParameterException("null graph");
        } else if (start >= g.numNodes || start < 0) {
            throw new IndexOutOfBoundsException("Invalid starting point: " + start);
        }

        time = 0;
        boolean[] visited = new boolean[g.numNodes];
        int[] parent = new int[g.numNodes];
        int[] entryTime = new int[g.numNodes];
        int[] exitTime = new int[g.numNodes];
        dfs(g, new Graph.Node(start), visited, parent, entryTime, exitTime, true);
    }

    /**
     * Main logic of DFS
     */
    private static void dfs(Graph g, Graph.Node start, boolean[] visited, int[] parent,
                             int[] entryTime, int[] exitTime, boolean print) {
        
        visited[start.id] = true;
        entryTime[start.id] = time++;
        if (print) {
            System.out.print("Visited " + start.id + " (parent: " + parent[start.id] + "), ");
        }

       for (Graph.Node node: g.nodes.get(start.id)){
            if (!visited[node.id]) {
                parent[node.id] = start.id;
                dfs(g, node, visited, parent, entryTime, exitTime, print);
            }
        }
        exitTime[start.id] = time++;
        if (print) {
            System.out.print("Exited " + start.id + ", ");
        }
    }

    /**
     * Find path from source to destination. This function can be optimize a lot more,
     * by terminating when visit destination. But I want to reuse dfs()
     */
    public static void findPath(Graph g, int source, int destination) {
        if (g == null) {
            throw new InvalidParameterException("null graph");
        } else if (source >= g.numNodes || source < 0) {
            throw new IndexOutOfBoundsException("Invalid source: " + source);
        } else if (destination >= g.numNodes || destination < 0) {
            throw new IndexOutOfBoundsException("Invalid destination: " + destination);
        }

        time = 0;
        boolean[] visited = new boolean[g.numNodes];
        int[] parent = new int[g.numNodes];
        Arrays.fill(parent, -1);
        int[] entryTime = new int[g.numNodes];
        int[] exitTime = new int[g.numNodes];

        dfs(g, new Graph.Node(source), visited, parent, entryTime, exitTime, false);

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