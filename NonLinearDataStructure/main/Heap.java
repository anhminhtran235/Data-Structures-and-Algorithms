package NonLinearDataStructure.main;

import java.lang.reflect.Array;
import java.util.*;

public class Heap<T extends Comparable<T>>{
    public Heap(Class<T> c, int capacity) {
        if (capacity <= 0) {
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
        hm.put(data, size - 1);
        bubbleUp(size - 1);
    }

    public T popMin() {
        if (size == 0) {
            return null;
        } 
        T result = arr[0];
        swap(0, size - 1);
        size--;
        bubbleDown(0);
        this.hm.remove(result);
        return result;
    }

    public Integer getIndex(T equivalentObject) {
        Integer index = hm.get(equivalentObject);
        return index;
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

    public void bubbleUp(int index) {
        while (hasP(index)) {
            int parentIndex = getPIndex(index);
            T parent = arr[parentIndex];
            T curNode = arr[index];
            if (curNode.compareTo(parent) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    } 

    public void bubbleDown(int index) {
        while (hasLC(index)) {
            int smaller = getLCIndex(index);
            if (hasRC(index)) {
                // If LC > RC, smaller = RCIndex
                if (arr[smaller].compareTo(arr[getRCIndex(index)]) > 0) {
                    smaller = getRCIndex(index);
                }
            }
            if (arr[index].compareTo(arr[smaller]) > 0) {
                swap(index, smaller);
                index = smaller;
            } else {
                break;
            }
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

    public boolean isEmpty() {
        return size == 0;
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

    public T[] arr;
    private HashMap<T, Integer> hm;
    private int size;
    private int capacity;
    private Class<T> classParameter;
}