package Graph.main.weighted;

import java.util.*;

import NonLinearDataStructure.main.Heap;

class Edge {
    public Edge(int node1, int node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }
    public int node1;
    public int node2;
    public int weight;
    public String toString() {
        return node1 + "-" + node2 + "(weight:" + weight + ")";
    }
}

public class Prim {
    public static void printMinimumSpanningTree(Graph g) {
        if (g.isDirected) {
            System.out.println("Minimum Spanning Tree is for undirected graph only");
            return;
        }
        int totalWeight = 0;
        List<Edge> edges = new ArrayList<>();
        Heap<NodeWrapper> minHeap = new Heap<>(NodeWrapper.class);
        minHeap.insert(new NodeWrapper(new Graph.Node(0), 0, -1));
        for (int i = 1; i < g.numNodes; i++) {
            minHeap.insert(new NodeWrapper(new Graph.Node(i), 100000, -1));
        }
        while (!minHeap.isEmpty()) {
            NodeWrapper picked = minHeap.popMin();
            if (picked.distance == Integer.MAX_VALUE) {
                break;
            }
            for (Graph.Node adjNode : g.adjacencies.get(picked.node.id)) {
                Integer index = minHeap.getIndex(new NodeWrapper(adjNode, 0, -1));
                if (index == null) {
                    continue;
                }
                if (minHeap.arr[index].distance > adjNode.weight) {
                    minHeap.arr[index].distance = adjNode.weight;
                    minHeap.arr[index].parent = picked.node.id;
                    minHeap.bubbleUp(index);
                    if (minHeap.arr[index].equals(new NodeWrapper(adjNode, 0, -1))) {
                        minHeap.bubbleDown(index);
                    }
                }
            }
            if (picked.node.id != 0) {
                edges.add(new Edge(picked.parent, picked.node.id, picked.distance));
                totalWeight += picked.distance;
            }
        }

        if (edges.size() == g.numNodes - 1) {
            System.out.println("The minimum spanning tree has weight: " + totalWeight);
            System.out.print("Edges list: ");
            for (Edge e : edges) {
                System.out.print(e + " ");
            }
        } else {
            System.out.println("The graph is not connected. There are no spanning tree!");
        }
    }
}