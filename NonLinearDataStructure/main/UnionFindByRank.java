package NonLinearDataStructure.main;

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

public class UnionFindByRank {
    public UnionFindByRank(int n) {
        arr = new UnionNode[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new UnionNode(i);
        }
    }

    public int find(int id) {
        while (id != arr[id].parent) {
            id = arr[id].parent;
        }
        return id;
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

    private UnionNode[] arr;
}