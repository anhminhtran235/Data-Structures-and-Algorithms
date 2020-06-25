package Graph.main.weighted;

import java.util.*;

import NonLinearDataStructure.main.Heap;

public class DijkstraBinaryHeap {
    public static void printShortestPath(Graph g, int source, int destination) {
        if (hasNegativeWeight(g)) {
            System.out.println("Dijkstra Algorithm doesn't work on graph with negative weight");
            return;
        }
        int[] distance = new int[g.numNodes];
        int[] parent = new int[g.numNodes];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        Heap<NodeWrapper> minHeap = new Heap<>(NodeWrapper.class);
        minHeap.insert(new NodeWrapper(new Graph.Node(source), 0, -1));
        for (Graph.Node adjNode : g.adjacencies.get(source)) {
            minHeap.insert(new NodeWrapper(adjNode, adjNode.weight, source));
        }

        while (!minHeap.isEmpty()) {
            NodeWrapper picked = minHeap.popMin();
            distance[picked.node.id] = picked.distance;
            parent[picked.node.id] = picked.parent;
            for (Graph.Node adjNode : g.adjacencies.get(picked.node.id)) {
                if (distance[adjNode.id] != Integer.MAX_VALUE) { // We have picked this node
                    continue;
                }
                Integer index = minHeap.getIndex(new NodeWrapper(adjNode, 0, -1));
                if (index == null) {
                    minHeap.insert(new NodeWrapper(adjNode, picked.distance + adjNode.weight, picked.node.id));
                } else {
                    if (minHeap.arr[index].distance > picked.distance + adjNode.weight) {
                        minHeap.arr[index].distance = picked.distance + adjNode.weight;
                        minHeap.arr[index].parent = picked.node.id;
                        minHeap.bubbleUp(index);
                        if (minHeap.arr[index].equals(new NodeWrapper(adjNode, 0, -1))) {
                            minHeap.bubbleDown(index);
                        }
                    }
                }
            }
        }

        if (distance[destination] == Integer.MAX_VALUE) {
            System.out.println("There are no path from " + source + " to " + destination);
        } else {
            System.out.print("The shortest path from " + source + " to " + destination +
                                 " has length " + distance[destination] + ": ");
            printPath(source, destination, parent);
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