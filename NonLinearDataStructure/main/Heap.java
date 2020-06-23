package NonLinearDataStructure.main;

import java.lang.reflect.Array;
import java.util.*;

public class Heap<T extends Comparable<T>>{
    public Heap(Class<T> c, int capacity) {
        if (capacity < 0) {
            capacity = 1;
        }
        @SuppressWarnings("unchecked")
        final T[] a = (T[]) Array.newInstance(c, capacity);
        this.arr = a;
        this.capacity = capacity;
        this.hm = new HashMap<>();
        this.classParameter = c;
    }
    public Heap(Class<T> c) {
        @SuppressWarnings("unchecked")
        final T[] a = (T[]) Array.newInstance(c, 1);
        this.arr = a;
        this.capacity = 1;
        this.hm = new HashMap<>();
        this.classParameter = c;
    }


    public void insert(T data) {
        ensureCapacity(classParameter);
        this.arr[size++] = data;
        heapifyUp(size - 1);
    }

    public T popMin() {
        if (size == 0) {
            return null;
        } 
        T temp = arr[0];
        arr[0] = arr[size - 1];
        arr[size - 1] = temp;
        T result = arr[size - 1];
        size--;
        heapifyDown(0);
        return result;
    }

    public T getKey(T equivalentObject) {
        int index = hm.get(equivalentObject);
        return arr[index];
    }

    private void ensureCapacity(Class<T> c) {
        if (size == capacity) {
            capacity *= 2;
            @SuppressWarnings("unchecked")
            final T[] newArr = (T[]) Array.newInstance(c, capacity);
            for (int i = 0; i < size; i++) {
                newArr[i] = this.arr[i];
            }
            this.arr = newArr;
        }
    }

    private void heapifyUp(int index) {
        while (hasP(index)) {
            int parentIndex = getPIndex(index);
            T parent = arr[parentIndex];
            T curNode = arr[index];
            if (curNode.compareTo(parent) < 0) {
                swap(index, parentIndex);
            } 
            index = parentIndex;
        }
    } 

    private void heapifyDown(int index) {
        while (hasLC(index)) {
            int smaller = getLCIndex(index);
            if (hasRC(index)) {
                // If RC < LC, smaller = RCIndex
                if (arr[smaller].compareTo(arr[getRCIndex(index)]) < 0) {
                    smaller = getRCIndex(index);
                }
            }
            swap(index, smaller);
            index = smaller;
        }
    }

    private void swap(int i, int j) {
        // Update hashMap
        this.hm.put(arr[i], j);
        this.hm.put(arr[j], i);
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private boolean hasLC(int index) {
        return index * 2 + 1 < this.size;
    }
    private boolean hasRC(int index) {
        return index * 2 + 2 < this.size;
    }
    private boolean hasP(int index) {
        return index != 0;
    }

    private int getLCIndex(int index) {
        return index * 2 + 1;
    }
    private int getRCIndex(int index) {
        return index * 2 + 2;
    }
    private int getPIndex(int index) {
        return (index - 1) / 2;
    }
    private T[] arr;
    private HashMap<T, Integer> hm;
    private int size;
    private int capacity;
    private Class<T> classParameter;
}