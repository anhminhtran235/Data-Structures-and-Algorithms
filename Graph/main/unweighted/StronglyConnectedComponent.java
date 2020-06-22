package Graph.main.unweighted;

import java.util.*;

public class StronglyConnectedComponent {

    private static int id;
    private static int sccCount;
    public static void printSCC(Graph g) {
        sccCount = 0;
        id = 1;
        if (!g.isDirected) {
            boolean[] visited = new boolean[g.numNodes];
            for (int i = 0; i < g.numNodes; i++) {
                if (!visited[i]) {
                    sccCount++;
                    printSCCUndirected(g, i, visited);
                }
            }
        } else {
            int[] ids = new int[g.numNodes];
            int[] low = new int[g.numNodes];
            Stack<Integer> stack = new Stack<>();
            boolean[] onStack = new boolean[g.numNodes];
            Arrays.fill(ids, -1);
            Arrays.fill(low, -1);
            for (int i = 0; i < g.numNodes; i++) {
                if (ids[i] == -1) {
                    printSCCDirected(g, i, ids, low, onStack, stack);
                }
            }
        }
    }

    // A simple BFS (flood)
    private static void printSCCUndirected(Graph g, int start, boolean[] visited) {
        Queue<Graph.Node> queue = new LinkedList<>();
        queue.add(new Graph.Node(start));
        visited[start] = true;
        System.out.print("SCC #" + sccCount + ": ");
        while (!queue.isEmpty()) {
            Graph.Node node = queue.poll();
            System.out.print(node.id + " ");
            for (Graph.Node adjNode: g.adjacencies.get(node.id)) {
                if (!visited[adjNode.id]) {
                    queue.add(adjNode);
                    visited[adjNode.id] = true;
                }
            }
        }
        System.out.println();
    }

    private static void printSCCDirected(Graph g, int at, int[] ids, int[] low, boolean[] onStack, Stack<Integer> stack) {
        stack.push(at);
        onStack[at] = true;
        ids[at] = low[at] = StronglyConnectedComponent.id++;
        for (Graph.Node toNode: g.adjacencies.get(at)) {
            if (ids[toNode.id] == -1) {
                printSCCDirected(g, toNode.id, ids, low, onStack, stack);
            }
            if (onStack[toNode.id]) {
                low[at] = Math.min(low[at], low[toNode.id]);
            }
        }

        if (ids[at] == low[at]) {
            System.out.print("SCC #" + (++sccCount) + ": ");
            while (!stack.isEmpty()) {
                int node = stack.pop();
                System.out.print(node + " ");
                onStack[node] = false;
                low[node] = ids[at];
                if (node == at) {
                    break;
                }
            }
            System.out.println();
        }
    }
}