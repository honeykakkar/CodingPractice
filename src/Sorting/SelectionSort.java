package Sorting;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 9/29/2016
 */

// Program to implement the selection sort.

// The idea is to divide the array in two parts: sorted and unsorted.

// Find the minimum element in the unsorted sub-array and
// put it to the leftmost element in unsorted sub-array.
// Increment the boundary of sorted sub-array by 1.

// Check whether minimum and the left most element are at the same index
// to minimize the swaps, especially when the array is already sorted.

public class SelectionSort<T extends Comparable<T>> {

    // method to implement selection sort.
    private void selectionSort(ArrayList<T> array) {
        int minIndex, n = array.size();
        T temp;
        for (int i = 0; i < n; ++i) {
            minIndex = i;
            for (int j = i + 1; j < n; ++j) {
                if (array.get(j).compareTo(array.get(minIndex)) < 0)
                    minIndex = j;
            }
            if (minIndex != i) {
                temp = array.get(i);
                array.set(i, array.get(minIndex));
                array.set(minIndex, temp);
            }
        }
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
        SelectionSort<Integer> intSorter = new SelectionSort<>();
        System.out.println("Testing integers in reverse order:");
        System.out.println("Before performing selection sort:");
        intSorter.display(intArray);
        startTime = System.nanoTime();
        intSorter.selectionSort(intArray);
        endTime = System.nanoTime();
        System.out.println("After performing selection sort:");
        intSorter.display(intArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        // Testing when integers are in no order
        intArray = new ArrayList<>(n);
        int[] randomInts = new Random().ints(1, 31).distinct().limit(30).toArray();
        for (int randomInt : randomInts) intArray.add(randomInt);
        System.out.println("\nTesting integers in random order:");
        System.out.println("Before performing selection sort:");
        intSorter.display(intArray);
        startTime = System.nanoTime();
        intSorter.selectionSort(intArray);
        System.out.println("After performing selection sort:");
        endTime = System.nanoTime();
        intSorter.display(intArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        n = 26;
        // Testing when characters are already sorted
        ArrayList<Character> charArray = new ArrayList<>(n);
        for (int i = 0; i < n; ++i)
            charArray.add((char) (65 + i));
        SelectionSort<Character> charSorter = new SelectionSort<>();
        System.out.println("\nTesting characters in sorted order:");
        System.out.println("Before performing selection sort:");
        charSorter.display(charArray);
        startTime = System.nanoTime();
        charSorter.selectionSort(charArray);
        endTime = System.nanoTime();
        System.out.println("After performing selection sort:");
        charSorter.display(charArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        // Testing when doubles are in random order
        // It is advised to use double instead of float
        // as JAVA, by default, uses double to represent its floating-point numerals

        double[] randomArray = new Random().doubles(1, 21).distinct().limit(50).toArray();
        ArrayList<Double> doubleArray = new ArrayList<>(50);
        for (double aRandomArray : randomArray) doubleArray.add(aRandomArray);
        SelectionSort<Double> doubleSorter = new SelectionSort<>();
        System.out.println("\nTesting doubles in random order:");
        System.out.println("Before performing selection sort:");
        doubleSorter.display(doubleArray);
        startTime = System.nanoTime();
        doubleSorter.selectionSort(doubleArray);
        endTime = System.nanoTime();
        System.out.println("After performing selection sort:");
        doubleSorter.display(doubleArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

    }
}
