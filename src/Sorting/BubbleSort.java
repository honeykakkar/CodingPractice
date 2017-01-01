package Sorting;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 9/21/2016
 */

    // Program to implement the bubble sort.

    // The idea is to put the maximum element to the last
    // in a given range of elements.

    // Let n be the number of elements in an array.
    // In first iteration, we search for the maximum element in whole array
    // and put it to the last by swapping it.

    // In second, as nth element is the maximum element, we search for the next maximum
    // element and put it to the (n-1)th position; and so on.....

    // Using a flag to check whether any element was swapped
    // can improve performance in best case scenarios where elements
    // are already sorted.

public class BubbleSort {

    // method to implement bubble sort.
    private <T extends Comparable<T>> void bubbleSort(ArrayList<T> array) {
        boolean swapped = true;
        int n = array.size();
        while (swapped) {
            swapped = false;
            for (int i = 0; i < n - 1; ++i) {
                if (array.get(i).compareTo(array.get(i + 1)) > 0) {
                    T temp = array.get(i);
                    array.set(i, array.get(i + 1));
                    array.set(i + 1, temp);
                    swapped = true;
                }
            }
            --n;
        }
    }

    // Method to display the elements in the given list
    public <T> void display(ArrayList<T> array) {
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
        BubbleSort sorter = new BubbleSort();
        System.out.println("Testing integers in reverse order:");
        System.out.println("Before performing bubble sort:");
        sorter.display(intArray);
        startTime = System.nanoTime();
        sorter.bubbleSort(intArray);
        endTime = System.nanoTime();
        System.out.println("After performing bubble sort:");
        sorter.display(intArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        // Testing when integers are in no order
        intArray = new ArrayList<>(n);
        int[] randomInts = new Random().ints(1, 31).distinct().limit(30).toArray();
        for (int randomInt : randomInts) intArray.add(randomInt);
        System.out.println("\nTesting integers in random order:");
        System.out.println("Before performing bubble sort:");
        sorter.display(intArray);
        startTime = System.nanoTime();
        sorter.bubbleSort(intArray);
        System.out.println("After performing bubble sort:");
        endTime = System.nanoTime();
        sorter.display(intArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        n = 26;
        // Testing when characters are already sorted
        ArrayList<Character> charArray = new ArrayList<>(n);
        for (int i = 0; i < n; ++i)
            charArray.add((char) (65 + i));
        System.out.println("\nTesting characters in sorted order:");
        System.out.println("Before performing bubble sort:");
        sorter.display(charArray);
        startTime = System.nanoTime();
        sorter.bubbleSort(charArray);
        endTime = System.nanoTime();
        System.out.println("After performing bubble sort:");
        sorter.display(charArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        // Testing when doubles are in random order
        // It is advised to use double instead of float
        // as JAVA, by default, uses double to represent its floating-point numerals

        double[] randomArray = new Random().doubles(1, 21).distinct().limit(50).toArray();
        ArrayList<Double> doubleArray = new ArrayList<>(50);
        for (double aRandomArray : randomArray) doubleArray.add(aRandomArray);
        System.out.println("\nTesting doubles in random order:");
        System.out.println("Before performing bubble sort:");
        sorter.display(doubleArray);
        startTime = System.nanoTime();
        sorter.bubbleSort(doubleArray);
        endTime = System.nanoTime();
        System.out.println("After performing bubble sort:");
        sorter.display(doubleArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");
    }
}