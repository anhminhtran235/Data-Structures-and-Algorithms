package NonLinearDataStructure.test;

import java.util.*;

import Graph.main.weighted.NodeWrapper;
import Graph.main.weighted.Graph;
import NonLinearDataStructure.main.Heap;

public class testHeap {
    public static void main(String[] args) {
        Heap<NodeWrapper> heap = new Heap<>(NodeWrapper.class, 10);
        List<NodeWrapper> list = new ArrayList<>();
        int numNodes = 100;
        for (int i = 0; i < numNodes; i++) {
            int randNum = (int) (Math.random() * 1000) - 500;
            heap.insert(new NodeWrapper(new Graph.Node(i), randNum, -1));
            list.add(new NodeWrapper(new Graph.Node(i), randNum, -1));
        }
        Collections.sort(list);
        List<NodeWrapper> heapsortList = new ArrayList<>();
        while (!heap.isEmpty()) {
            heapsortList.add(heap.popMin());
        }
        System.out.println(list);
        System.out.println(heapsortList);
        boolean heapIsCorrect = true;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).compareTo(heapsortList.get(i)) != 0) {
                System.out.println("WRONG! " + list.get(i) + " != " + heapsortList.get(i));
                heapIsCorrect = false;
                break;
            }
        }

        if (heapIsCorrect) {
            System.out.println("The heap works correctly");
        }
    }
}