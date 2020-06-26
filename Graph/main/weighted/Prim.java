package Graph.main.weighted;

import java.util.*;

import NonLinearDataStructure.main.Heap;

public class Prim {
    public static void printMST(Graph g) {
        if (g.isDirected) {
            System.out.println("Minimum Spanning Tree is for undirected graph only");
            return;
        }
        long totalWeight = 0;
        List<Edge> edges = new ArrayList<>();
        Heap<NodeWrapper> minHeap = new Heap<>(NodeWrapper.class);
        boolean[] hasPicked = new boolean[g.numNodes];

        hasPicked[0] = true;
        for (Graph.Node adj : g.adjacencies.get(0)) {
            minHeap.insert(new NodeWrapper(adj, adj.weight, 0));
        }

        while (!minHeap.isEmpty()) {
            NodeWrapper picked = minHeap.popMin();
            hasPicked[picked.node.id] = true;
            for (Graph.Node adjNode : g.adjacencies.get(picked.node.id)) {
                if (hasPicked[adjNode.id]) {
                    continue;
                }
                Integer index = minHeap.getIndex(new NodeWrapper(adjNode, 0, -1));
                if (index == null) {
                    minHeap.insert(new NodeWrapper(adjNode, adjNode.weight, picked.node.id));
                } else {
                    if (minHeap.arr[index].distance > adjNode.weight) {
                        minHeap.arr[index].distance = adjNode.weight;
                        minHeap.arr[index].parent = picked.node.id;
                        minHeap.bubbleUp(index);
                        if (minHeap.arr[index].equals(new NodeWrapper(adjNode, 0, -1))) {
                            minHeap.bubbleDown(index);
                        }
                    }
                }
            }
            edges.add(new Edge(picked.parent, picked.node.id, picked.distance));
            totalWeight += picked.distance;
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