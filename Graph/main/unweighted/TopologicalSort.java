package Graph.main.unweighted;

import java.security.InvalidParameterException;
import java.util.*;

public class TopologicalSort {
    public static void topoSortDFS(Graph g) {
        if (g == null) {
            throw new InvalidParameterException("null graph");
        } else if (!g.isDirected) {
            throw new InvalidParameterException("Topo sort only works for directed graph");
        }

        // We can also check for cycle DURING topoDFS
        if (CycleDetection.hasCycleDFS(g)) {
            System.out.println("Graph contains a cycle. Topo ordering does not exist");
            return;
        } 

        boolean[] visited = new boolean[g.numNodes];
        Stack<Integer> topoOrdering = new Stack<Integer>();
        for (int i = 0; i < g.numNodes; i++) {
            if (!visited[i]) {
                topoDFS(g, new Graph.Node(i), visited, topoOrdering);
            }
        }

        while (!topoOrdering.isEmpty()) {
            System.out.print(topoOrdering.pop() + " ");
        }
    }

    private static void topoDFS(Graph g, Graph.Node source, boolean[] visited, Stack<Integer> topoOrdering) {
        visited[source.id] = true;
        for (Graph.Node adjNode: g.nodes.get(source.id)) {
            if (!visited[adjNode.id]) {
                topoDFS(g, adjNode, visited, topoOrdering);
            }
        }
        // When we push 'source' to the stack, all nodes that depend on 'source', either directly or indirectly
        // has been pushed in the stack
        topoOrdering.push(source.id);
    }

    public static void topoSortBFS(Graph g) {
        int[] inDegree = new int[g.numNodes];
        Queue<Integer> sourceVertex = new LinkedList<>();
        List<Integer> topoOrder = new ArrayList<>();

        // Init inDegree arr
        for (int i = 0; i < inDegree.length; i++) {
            for (Graph.Node adjNode: g.nodes.get(i)) {
                inDegree[adjNode.id]++;
            }
        }

        // Find source nodes
        for (int i = 0; i < g.numNodes; i++) {
            if (inDegree[i] == 0) {
                sourceVertex.add(i);
            }
        }

        // Take each source out
        while (!sourceVertex.isEmpty()) {
            int source = sourceVertex.poll();
            topoOrder.add(source);
            for (Graph.Node adjNode: g.nodes.get(source)) {
                inDegree[adjNode.id]--;
                if (inDegree[adjNode.id] == 0) {
                    sourceVertex.add(adjNode.id);
                }
            }
        }

        if (topoOrder.size() != g.numNodes) {
            System.out.println("Graph contains a cycle. Topo ordering does not exist");
        } else {
            for (int i = 0; i < topoOrder.size(); i++) {
                System.out.print(topoOrder.get(i) + " "); 
            }
        }
    }
}