package Graph.main.weighted;

import java.util.*;

import NonLinearDataStructure.main.UnionFind;

public class Kruskal {
    public static void printMST(Graph g) {
        if (g.isDirected) {
            System.out.println("Minimum Spanning Tree is for undirected graph only");
            return;
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < g.numNodes; i++) {
            for (Graph.Node adjNode : g.adjacencies.get(i)) {
                edges.add(new Edge(i, adjNode.id, adjNode.weight));
            }
        }

        Collections.sort(edges);

        UnionFind unionFind = new UnionFind(g.numNodes);
        List<Edge> result = new ArrayList<>();
        int totalWeight = 0;

        for (int i = 0; i < edges.size(); i++) {
            Edge e = edges.get(i);
            if (!unionFind.connected(e.node1, e.node2)) {
                totalWeight += e.weight;
                unionFind.union(e.node1, e.node2);
                result.add(e);
            }
        }

        if (result.size() == g.numNodes - 1) {
            System.out.println("The minimum spanning tree has weight: " + totalWeight);
            System.out.print("Edges list: ");
            for (Edge e : result) {
                System.out.print(e + " ");
            }
        } else {
            System.out.println("The graph is not connected. There are no spanning tree!");
        }
    }
}