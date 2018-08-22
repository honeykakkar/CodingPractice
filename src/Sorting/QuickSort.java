package Sorting;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 8/7/2016
 */
public class QuickSort<T extends Comparable<T>> {

    void quickSort(ArrayList<T> array, int l, int r) {
        if (l < r) {
            int pivot = randomPartition(array, l, r);
            quickSort(array, l, pivot - 1);
            quickSort(array, pivot + 1, r);
        }
    }

    // using Lomuto partition scheme
    private int partition(ArrayList<T> array, int l, int r) {
        T pivot = array.get(r);
        int dest = l;
        for (int src = l; src < r; ++src) {
            if (array.get(src).compareTo(pivot) <= 0) {
                swap(array, dest, src);
                ++dest;
            }
        }
        swap(array, dest, r);
        return dest;
    }

    // Picks a random pivot element between l and r and
    // partitions array list [l..r] around the randomly picked element using partition()
    private int randomPartition(ArrayList<T> array, int l, int r) {
        int n = r - l + 1;
        int pivot = (int) (Math.random() * n);  // generates a random number between 0.0 and 1.0 which is multiplied by n for this partition
        swap(array, l + pivot, r);
        return partition(array, l, r);
    }

    private void swap(ArrayList<T> array, int first, int second) {
        T temp = array.get(first);
        array.set(first, array.get(second));
        array.set(second, temp);
    }

    void display(ArrayList<T> array) {
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
        QuickSort<Integer> intSorter = new QuickSort<>();
        System.out.println("Testing integers in reverse order:");
        System.out.println("Before performing quick sort:");
        intSorter.display(intArray);
        startTime = System.nanoTime();
        intSorter.quickSort(intArray, 0, intArray.size() - 1);
        endTime = System.nanoTime();
        System.out.println("After performing quick sort:");
        intSorter.display(intArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        // Testing when integers are in no order
        intArray = new ArrayList<>(n);
        int[] randomInts = new Random().ints(1, 31).distinct().limit(30).toArray();
        for (int randomInt : randomInts) intArray.add(randomInt);
        System.out.println("\nTesting integers in random order:");
        System.out.println("Before performing quick sort:");
        intSorter.display(intArray);
        startTime = System.nanoTime();
        intSorter.quickSort(intArray, 0, intArray.size() - 1);
        System.out.println("After performing quick sort:");
        endTime = System.nanoTime();
        intSorter.display(intArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        n = 26;
        // Testing when characters are already sorted
        ArrayList<Character> charArray = new ArrayList<>(n);
        for (int i = 0; i < n; ++i)
            charArray.add((char) (65 + i));
        QuickSort<Character> charSorter = new QuickSort<>();
        System.out.println("\nTesting characters in sorted order:");
        System.out.println("Before performing quick sort:");
        charSorter.display(charArray);
        startTime = System.nanoTime();
        charSorter.quickSort(charArray, 0, charArray.size() - 1);
        endTime = System.nanoTime();
        System.out.println("After performing quick sort:");
        charSorter.display(charArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        // Testing when doubles are in random order
        // It is advised to use double instead of float
        // as JAVA, by default, uses double to represent its floating-point numerals

        double[] randomArray = new Random().doubles(1, 21).distinct().limit(50).toArray();
        ArrayList<Double> doubleArray = new ArrayList<>(50);
        for (double aRandomArray : randomArray) doubleArray.add(aRandomArray);
        QuickSort<Double> doubleSorter = new QuickSort<>();
        System.out.println("\nTesting doubles in random order:");
        System.out.println("Before performing quick sort:");
        doubleSorter.display(doubleArray);
        startTime = System.nanoTime();
        doubleSorter.quickSort(doubleArray, 0, doubleArray.size() - 1);
        endTime = System.nanoTime();
        System.out.println("After performing quick sort:");
        doubleSorter.display(doubleArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");
    }
}
