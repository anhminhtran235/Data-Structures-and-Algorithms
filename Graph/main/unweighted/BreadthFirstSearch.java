package Graph.main.unweighted;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {
    public static void printBFS(Graph g, int start) {
        if (start >= g.numNodes || start < 0) {
            throw new IndexOutOfBoundsException("Invalid starting point: " + start);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[g.numNodes];
        int[] parent = new int[g.numNodes];
        Arrays.fill(parent, -1);
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