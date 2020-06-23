package NonLinearDataStructure.test;

import NonLinearDataStructure.main.Heap;

public class testHeap {
    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<Integer>(Integer.class, 10);
        int numNodes = 100;
        for (int i = 0; i < numNodes; i++) {
            int randNum = (int)(Math.random() * 1000) - 500;
            heap.insert(randNum);
        }

        while (!heap.isEmpty()) {
            System.out.print(heap.popMin() + " ");
        }
    }
}