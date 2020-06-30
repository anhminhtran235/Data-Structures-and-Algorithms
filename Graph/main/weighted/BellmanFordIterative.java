package Graph.main.weighted;

import java.util.*;

public class BellmanFordIterative {
    public static void printShortestPaths(Graph g, int source) {
        // Init distance arr
        long distance[] = new long[g.numNodes];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        // Find shortest paths from source to the rest
        for (int t = 0; t < g.numNodes - 1; t++) {
            for (int i = 0; i < g.numNodes; i++) {
                for (Graph.Node adj : g.adjacencies.get(i)) {
                    if (distance[i] != Integer.MAX_VALUE && distance[adj.id] > distance[i] + adj.weight) {
                        distance[adj.id] = distance[i] + adj.weight;
                    }
                }
            }
        }
        
        // Find out which nodes are in a negative cycles
        for (int t = 0; t < g.numNodes - 1; t++) {
            for (int i = 0; i < g.numNodes; i++) {
                for (Graph.Node adj : g.adjacencies.get(i)) {
                    if (distance[i] != Integer.MAX_VALUE && distance[adj.id] > distance[i] + adj.weight) {
                        distance[adj.id] = Integer.MIN_VALUE;
                    }
                }
            }
        }

        System.out.print("Shortest paths from " + source + " to the rest: ");
        for (long i : distance) {
            String info; 
            if (i == Integer.MAX_VALUE) info = "Infinity";
            else if (i == -Integer.MIN_VALUE) info = "-Infinity";
            else info = i + "";
            System.out.print(info + " ");
        }
    }

    public static void correctShorestPaths(Graph g, int start) {
        int V = g.numNodes;
        double[] dist = new double[V];
        java.util.Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[start] = 0;

        // Create edges list
        int numEdges = 0;
        for (int i = 0; i < g.numNodes; i++) {
            numEdges += g.adjacencies.get(i).size();
        }
        Edge[] edges = new Edge[numEdges];
        int index = 0;
        for (int i = 0; i < g.numNodes; i++) {
            for (Graph.Node adj : g.adjacencies.get(i)) {
                edges[index++] = new Edge(i, adj.id, adj.weight);
            }
        }


        // Only in the worst case does it take V-1 iterations for the Bellman-Ford
        // algorithm to complete. Another stopping condition is when we're unable to
        // relax an edge, this means we have reached the optimal solution early.
        boolean relaxedAnEdge = true;

        // For each vertex, apply relaxation for all the edges
        for (int v = 0; v < V - 1 && relaxedAnEdge; v++) {
            relaxedAnEdge = false;
            for (Edge edge : edges) {
                if (dist[edge.node1] + edge.weight < dist[edge.node2]) {
                    dist[edge.node2] = dist[edge.node1] + edge.weight;
                    relaxedAnEdge = true;
                }
            }
        }

        // Run algorithm a second time to detect which nodes are part
        // of a negative cycle. A negative cycle has occurred if we
        // can find a better path beyond the optimal solution.
        relaxedAnEdge = true;
        for (int v = 0; v < V - 1 && relaxedAnEdge; v++) {
            relaxedAnEdge = false;
            for (Edge edge : edges) {
                if (dist[edge.node1] + edge.weight < dist[edge.node2]) {
                dist[edge.node2] = Double.NEGATIVE_INFINITY;
                relaxedAnEdge = true;
                }
            }
        }

        // Print the array containing the shortest distance to every node
        System.out.println("Correct shortest paths from " + start + " to the rest: ");
        for (double i : dist) {
            System.out.print(i + " ");
        }
    }
}