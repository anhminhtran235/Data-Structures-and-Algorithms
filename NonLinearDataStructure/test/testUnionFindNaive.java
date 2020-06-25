package NonLinearDataStructure.test;

import NonLinearDataStructure.main.UnionFindByRank;

public class testUnionFindNaive {
    public static void main(String[] args) {
        int n = 10;
        UnionFindByRank unionFind = new UnionFindByRank(n);
        unionFind.union(1, 3);
        unionFind.union(1, 3);
        unionFind.union(1, 3);
        unionFind.union(2, 3);
        unionFind.union(4, 5);
        System.out.println("Find 3: " + unionFind.find(3) + ", rank of root: " + unionFind.getRank(unionFind.find(3)));
        System.out.println("Find 2: " + unionFind.find(2) + ", rank of root: " + unionFind.getRank(unionFind.find(2)));
        System.out.println("Find 4: " + unionFind.find(4) + ", rank of root: " + unionFind.getRank(unionFind.find(4)));
        unionFind.union(6, 9);
        unionFind.union(0, 7);
        System.out.println("Find 6: " + unionFind.find(6) + ", rank of root: " + unionFind.getRank(unionFind.find(6)));
        System.out.println("Find 5: " + unionFind.find(5) + ", rank of root: " + unionFind.getRank(unionFind.find(5)));
        unionFind.union(1, 6);
        System.out.println("Find 6: " + unionFind.find(6) + ", rank of root: " + unionFind.getRank(unionFind.find(6)));
    }
}