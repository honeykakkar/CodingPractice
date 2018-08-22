package Sorting;

import DataStructures.Heaps.BinaryMaxHeap;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 9/29/2016
 */

// Program to implement the heap sort.

// Note: Depends on heap data structure implemented in DataStructures package

// The buildMaxHeap() operation is run once, and is O(n) in performance.
// The maxHeapify() function is O(log(n)), and is called n times.
// Therefore, the performance of this algorithm is O(n + n * log(n)) which evaluates to O(n log n).

public class HeapSort<T extends Comparable<T>> {

    // method to implement heap sort.
    private void heapSort(ArrayList<T> array) {
        int arraySize = array.size();
        BinaryMaxHeap<T> heap = new BinaryMaxHeap<>(array);
        heap.buildMaxHeap();
        for (int i = arraySize; i >= 2; --i) {
            heap.swapElements(1, heap.getHeapSize());
            heap.setHeapSize(heap.getHeapSize() - 1);
            heap.maxHeapify(1);
        }
        array.remove(0);
    }

    // method to displayMSTEdges the list of elements
    private void display(ArrayList<T> array) {
        for (T element : array)
            System.out.print(element + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 30;
        long startTime, endTime;
        // Testing when integers are in reverse order
        ArrayList<Integer> intArray = new ArrayList<>(n);
        for (int i = 0; i < n; ++i)
            intArray.add(n - i);
        HeapSort<Integer> intSorter = new HeapSort<>();
        System.out.println("Testing integers in reverse order:");
        System.out.println("Before performing heap sort:");
        intSorter.display(intArray);
        startTime = System.nanoTime();
        intSorter.heapSort(intArray);
        endTime = System.nanoTime();
        System.out.println("After performing heap sort:");
        intSorter.display(intArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        // Testing when integers are in no order
        intArray = new ArrayList<>(n);
        int[] randomInts = new Random().ints(1, 31).distinct().limit(30).toArray();
        for (int randomInt : randomInts) intArray.add(randomInt);
        System.out.println("\nTesting integers in random order:");
        System.out.println("Before performing heap sort:");
        intSorter.display(intArray);
        startTime = System.nanoTime();
        intSorter.heapSort(intArray);
        System.out.println("After performing heap sort:");
        endTime = System.nanoTime();
        intSorter.display(intArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        n = 26;
        // Testing when characters are already sorted
        ArrayList<Character> charArray = new ArrayList<>(n);
        for (int i = 0; i < n; ++i)
            charArray.add((char) (65 + i));
        HeapSort<Character> charSorter = new HeapSort<>();
        System.out.println("\nTesting characters in sorted order:");
        System.out.println("Before performing heap sort:");
        charSorter.display(charArray);
        startTime = System.nanoTime();
        charSorter.heapSort(charArray);
        endTime = System.nanoTime();
        System.out.println("After performing heap sort:");
        charSorter.display(charArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        // Testing when doubles are in random order
        // It is advised to use double instead of float
        // as JAVA, by default, uses double to represent its floating-point numerals

        double[] randomArray = new Random().doubles(1, 21).distinct().limit(50).toArray();
        ArrayList<Double> doubleArray = new ArrayList<>(50);
        for (double aRandomArray : randomArray) doubleArray.add(aRandomArray);
        HeapSort<Double> doubleSorter = new HeapSort<>();
        System.out.println("\nTesting doubles in random order:");
        System.out.println("Before performing heap sort:");
        doubleSorter.display(doubleArray);
        startTime = System.nanoTime();
        doubleSorter.heapSort(doubleArray);
        endTime = System.nanoTime();
        System.out.println("After performing heap sort:");
        doubleSorter.display(doubleArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");
    }
}
