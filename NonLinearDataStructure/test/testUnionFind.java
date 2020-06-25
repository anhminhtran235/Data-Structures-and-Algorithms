package NonLinearDataStructure.test;

import NonLinearDataStructure.main.UnionFind;

public class testUnionFind {
    public static void main(String[] args) {
        int n = 10;
        UnionFind unionFind = new UnionFind(n);
        unionFind.union(1, 3);
        unionFind.union(1, 3);
        unionFind.union(1, 3);
        unionFind.union(2, 3);
        unionFind.union(4, 5);
        unionFind.printAllUnions();
        unionFind.union(6, 9);
        unionFind.union(0, 7);
        unionFind.printAllUnions();
        unionFind.union(1, 6);
        unionFind.printAllUnions();
        unionFind.union(1, 0);
        unionFind.printAllUnions();
        unionFind.union(1, 4);
        unionFind.printAllUnions();
        unionFind.union(8, 6);
        unionFind.printAllUnions();
    }
}