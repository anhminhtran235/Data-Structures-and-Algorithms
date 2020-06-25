package NonLinearDataStructure.main;

import java.util.*;

class UnionNode {
    public UnionNode(int id) {
        this.parent = id;
        this.size = 1;
        this.rank = 0;
    }
    public int parent;
    public int size;
    public int rank;
}

/**
 * Union find by rank with path compression
 */
public class UnionFind {
    public UnionFind(int n) {
        arr = new UnionNode[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new UnionNode(i);
        }
    }

    public int find(int id) {
        int temp = id;
        while (id != arr[id].parent) {
            id = arr[id].parent;
        }
        compressPath(temp, id);
        return id;
    }

    public boolean connected(int id1, int id2) {
        return find(id1) == find(id2);
    }

    public void union(int id1, int id2) {
        int root1 = find(id1);
        int root2 = find(id2);
        if (root1 == root2) {
            return;
        }
        if (arr[root1].rank <= arr[root2].rank) {
            arr[root1].parent = root2;
            arr[root2].size += arr[root1].size;
            if (arr[root1].rank == arr[root2].rank) {
                arr[root2].rank += 1;
            }
        } else {
            arr[root2].parent = root1;
            arr[root1].size += arr[root2].size;
        }
    }

    public int getRank(int id) {
        return arr[id].rank;
    }

    public int getSize(int id) {
        return arr[id].size;
    }

    public void printAllUnions() {
        int count = 1;
        Set<Integer> hasCount = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            int root = find(i);
            if (!hasCount.contains(root)) {
                System.out.print("Union #" + (count++) + " (root " + root + 
                    ", size " + arr[root].size + ", rank " + arr[root].rank + "): ");
                for (int j = 0; j < arr.length; j++) {
                    if (find(j) == root) {
                        System.out.print(j + " ");
                    }
                }
                hasCount.add(root);
                System.out.println();
            }
        }
        System.out.println();
    }

    private void compressPath(int id, int root) {
        while (id != root) {
            int parent = arr[id].parent;
            arr[id].parent = root;
            id = parent;
        }
    }

    private UnionNode[] arr;
}