package Sorting;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 9/29/2016
 */

// Program to implement the insertion sort.
// The idea is to divide the array in two parts: sorted and unsorted

// It takes one element from unsorted sub-array and puts it to its right place in the sorted subarray
// Starts comparing current element to each element in sorted sub-array starting from the right
// Instead of swapping, this program copies the value to greater index if it is greater than current element

public class InsertionSort<T extends Comparable<T>> {

    // method to implement insertion sort.
    private void insertionSort(ArrayList<T> array) {
        int n = array.size();
        T current;
        for (int i = 1; i < n; ++i) {
            current = array.get(i);
            int j = i - 1;
            while (j >= 0 && array.get(j).compareTo(current) > 0) {
                array.set(j + 1, array.get(j));
                --j;
            }
            array.set(j + 1, current);
        }
    }

    // method to display the list of elements
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
        InsertionSort<Integer> intSorter = new InsertionSort<>();
        System.out.println("Testing integers in reverse order:");
        System.out.println("Before performing insertion sort:");
        intSorter.display(intArray);
        startTime = System.nanoTime();
        intSorter.insertionSort(intArray);
        endTime = System.nanoTime();
        System.out.println("After performing insertion sort:");
        intSorter.display(intArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        // Testing when integers are in no order
        intArray = new ArrayList<>(n);
        int[] randomInts = new Random().ints(1, 31).distinct().limit(30).toArray();
        for (int randomInt : randomInts)
            intArray.add(randomInt);
        System.out.println("\nTesting integers in random order:");
        System.out.println("Before performing insertion sort:");
        intSorter.display(intArray);
        startTime = System.nanoTime();
        intSorter.insertionSort(intArray);
        System.out.println("After performing insertion sort:");
        endTime = System.nanoTime();
        intSorter.display(intArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        n = 26;
        // Testing when characters are already sorted
        ArrayList<Character> charArray = new ArrayList<>(n);
        for (int i = 0; i < n; ++i)
            charArray.add((char) (65 + i));
        InsertionSort<Character> charSorter = new InsertionSort<>();
        System.out.println("\nTesting characters in sorted order:");
        System.out.println("Before performing insertion sort:");
        charSorter.display(charArray);
        startTime = System.nanoTime();
        charSorter.insertionSort(charArray);
        endTime = System.nanoTime();
        System.out.println("After performing insertion sort:");
        charSorter.display(charArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        // Testing when doubles are in random order
        // It is advised to use double instead of float
        // as JAVA, by default, uses double to represent its floating-point numerals

        double[] randomArray = new Random().doubles(1, 21).distinct().limit(50).toArray();
        ArrayList<Double> doubleArray = new ArrayList<>(50);
        for (double aRandomArray : randomArray) doubleArray.add(aRandomArray);
        InsertionSort<Double> doubleSorter = new InsertionSort<>();
        System.out.println("\nTesting doubles in random order:");
        System.out.println("Before performing insertion sort:");
        doubleSorter.display(doubleArray);
        startTime = System.nanoTime();
        doubleSorter.insertionSort(doubleArray);
        endTime = System.nanoTime();
        System.out.println("After performing insertion sort:");
        doubleSorter.display(doubleArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

    }
}
