package Graph.main.weighted;

import java.util.*;

public class DijkstraNaive {
    // Time complexity: O(mn) where m is #edges and n is #nodes
    public static void printShortestPath(Graph g, int source, int destination) {
        if (hasNegativeWeight(g)) {
            System.out.println("Dijkstra Algorithm doesn't work on graph with negative weight");
            return;
        }
        int[] distance = new int[g.numNodes];
        int[] parent = new int[g.numNodes];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        distance[source] = 0;

        while (true) {
            int minDistance = Integer.MAX_VALUE;
            int candidate = -1;
            for (int i = 0; i < g.numNodes; i++) {
                if (distance[i] != Integer.MAX_VALUE) { // We have picked this node
                    for (Graph.Node node : g.adjacencies.get(i)) {
                        if (distance[node.id] == Integer.MAX_VALUE) {
                            if (minDistance > distance[i] + node.weight) {
                                minDistance = distance[i] + node.weight;
                                candidate = node.id;
                                parent[node.id] = i;
                            }
                        }
                    }
                }
            }
            if (candidate == -1) {
                break;
            }

            distance[candidate] = minDistance;
            
            if (candidate == destination) {
                break;
            }
        }

        if (distance[destination] != Integer.MAX_VALUE) {
            System.out.print("The shortest path from " + source + " to " + destination +
                               " has length " + distance[destination] + ": ");
            printPath(source, destination, parent);
        } else {
            System.out.println("There are no path from " + source + " to " + destination);
        }

    }

    private static void printPath(int source, int destination, int[] parent) {
        Stack<Integer> stack = new Stack<>();
        int curNode = destination;
        while (curNode != source) {
            stack.push(curNode);
            curNode = parent[curNode];
        }

        System.out.print(source);
        while (!stack.isEmpty()) {
            System.out.print("->" + stack.pop());
        }
        System.out.println();
    }

    private static boolean hasNegativeWeight(Graph g) {
        for (int i = 0 ; i < g.numNodes; i++) {
            LinkedList<Graph.Node> adjList = g.adjacencies.get(i);
            for (Graph.Node adjNodes : adjList) {
                if (adjNodes.weight < 0) {
                    return true;
                }
            }
        }
        return false;
    }
}