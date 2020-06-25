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
        for (int i = 0; i < g.numNodes; i++) {
            if (i == source) {
                minHeap.insert(new NodeWrapper(new Graph.Node(i), 0, -1));
            } else {
                minHeap.insert(new NodeWrapper(new Graph.Node(i), Integer.MAX_VALUE, -1));
            }            
        }
        

        while (!minHeap.isEmpty()) {
            NodeWrapper picked = minHeap.popMin();
            if (picked.distance == Integer.MAX_VALUE) {
                break;
            }
            distance[picked.node.id] = picked.distance;
            parent[picked.node.id] = picked.parent;
            for (Graph.Node node : g.adjacencies.get(picked.node.id)) {
                Integer index = minHeap.getIndex(new NodeWrapper(node, 0, -1));
                if (index == null) continue;
                if (minHeap.arr[index].distance > picked.distance + node.weight) {
                    minHeap.arr[index].distance = picked.distance + node.weight;
                    minHeap.arr[index].parent = picked.node.id;
                    minHeap.bubbleUp(index);
                    if (minHeap.arr[index].equals(new NodeWrapper(node, 0, -1))) {
                        minHeap.bubbleDown(index);
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