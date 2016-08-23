package DataStructures.Heaps;
import java.util.*;
/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 8/17/2016
 */
public class BinaryMaxHeap<T extends Comparable<T>> {

    ArrayList<T> heap;
    int heapSize;

    public BinaryMaxHeap(){
        heap = new ArrayList<>();
        heap.add(null);
        heapSize = 0;
    }

    int getParent(int child){
        if(child == 1)
            return -1;
        return child/2;
    }

    int getLeft(int parent){
        if(2*parent < heap.size())
            return 2*parent;
        return -1;
    }

    int getRight(int parent){
        if(2*parent + 1 < heap.size())
            return 2*parent + 1;
        return -1;
    }

    void maxHeapify(int parent){
        int leftChild = getLeft(parent);
        int rightChild = getRight(parent);

        int largest = parent;
        if(leftChild > 0 && leftChild < heap.size() && (heap.get(leftChild).compareTo(heap.get(parent)) > 0))
            largest = leftChild;
        if(rightChild > 0 && rightChild < heap.size() && (heap.get(rightChild).compareTo(heap.get(largest)) > 0))
            largest = rightChild;

        if(largest != parent){
            T temp = heap.get(largest);
            heap.set(largest, heap.get(parent));
            heap.set(parent, temp);
            maxHeapify(largest);
        }
    }

    void increaseValue(int index, T value){
        if(heap.get(index) == null || heap.get(index).compareTo(value) < 0){
            heap.set(index, value);
            while(index > 1 && heap.get(getParent(index)).compareTo(heap.get(index)) < 0){
                T temp = heap.get(getParent(index));
                heap.set(getParent(index), heap.get(index));
                heap.set(index,temp);
                index = getParent(index);
            }
        }
    }

    void insert(T value){
        heap.add(null);
        ++heapSize;
        increaseValue(heapSize, value);
    }

    void buildMaxHeap(){
        for(int i=heapSize/2; i>=1; ++i)
            maxHeapify(i);
    }

    @Override
    public String toString() {
        String heapString = "";
        for(int i=1; i<=heapSize; ++i) {
            T element = heap.get(i);
            heapString = heapString + element.toString() + " ";
        }
        return heapString;
    }

    public static void main(String[] args) {
        BinaryMaxHeap<Integer> intMaxHeap = new BinaryMaxHeap<>();
        intMaxHeap.insert(1);
        intMaxHeap.insert(2);
        intMaxHeap.insert(3);
        intMaxHeap.insert(5);
        intMaxHeap.insert(8);
        intMaxHeap.insert(13);
        intMaxHeap.insert(21);
        intMaxHeap.insert(34);
        System.out.println("Array representation of the heap before \"max-heapify\"ing: " + intMaxHeap);
        intMaxHeap.buildMaxHeap();
        System.out.println("Array representation of the heap after \"max-heapify\"ing: " + intMaxHeap);

        BinaryMaxHeap<Integer> intMaxHeap1 = new BinaryMaxHeap<>();
        intMaxHeap1.insert(4);
        intMaxHeap1.insert(1);
        intMaxHeap1.insert(3);
        intMaxHeap1.insert(2);
        intMaxHeap1.insert(16);
        intMaxHeap1.insert(9);
        intMaxHeap1.insert(10);
        intMaxHeap1.insert(14);
        intMaxHeap1.insert(8);
        intMaxHeap1.insert(7);
        System.out.println("Array representation of the heap before \"max-heapify\"-ing: " + intMaxHeap1);
        intMaxHeap1.buildMaxHeap();
        System.out.println("Array representation of the heap after \"max-heapify\"ing: " + intMaxHeap1);

    }
}