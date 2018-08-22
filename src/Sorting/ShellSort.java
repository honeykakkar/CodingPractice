package Sorting;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 10/9/2016
 */
public class ShellSort<T extends Comparable<T>> {

    // method to implement shell sort.
    private void shellSort(ArrayList<T> array) {
        int n = array.size();
        T current;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; ++i) {
                current = array.get(i);
                int j = i;
                while (j >= gap && array.get(j - gap).compareTo(current) > 0) {
                    array.set(j, array.get(j - gap));
                    j -= gap;
                }
                if (i != j)
                    array.set(j, current);
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
        ShellSort<Integer> intSorter = new ShellSort<>();
        System.out.println("Testing integers in reverse order:");
        System.out.println("Before performing shell sort:");
        intSorter.display(intArray);
        startTime = System.nanoTime();
        intSorter.shellSort(intArray);
        endTime = System.nanoTime();
        System.out.println("After performing shell sort:");
        intSorter.display(intArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        // Testing when integers are in no order
        intArray = new ArrayList<>(n);
        int[] randomInts = new Random().ints(1, 31).distinct().limit(30).toArray();
        for (int randomInt : randomInts) intArray.add(randomInt);
        System.out.println("\nTesting integers in random order:");
        System.out.println("Before performing shell sort:");
        intSorter.display(intArray);
        startTime = System.nanoTime();
        intSorter.shellSort(intArray);
        System.out.println("After performing shell sort:");
        endTime = System.nanoTime();
        intSorter.display(intArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        n = 26;
        // Testing when characters are already sorted
        ArrayList<Character> charArray = new ArrayList<>(n);
        for (int i = 0; i < n; ++i)
            charArray.add((char) (65 + i));
        ShellSort<Character> charSorter = new ShellSort<>();
        System.out.println("\nTesting characters in sorted order:");
        System.out.println("Before performing shell sort:");
        charSorter.display(charArray);
        startTime = System.nanoTime();
        charSorter.shellSort(charArray);
        endTime = System.nanoTime();
        System.out.println("After performing shell sort:");
        charSorter.display(charArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        // Testing when doubles are in random order
        // It is advised to use double instead of float
        // as JAVA, by default, uses double to represent its floating-point numerals

        double[] randomArray = new Random().doubles(1, 21).distinct().limit(50).toArray();
        ArrayList<Double> doubleArray = new ArrayList<>(50);
        for (double aRandomArray : randomArray) doubleArray.add(aRandomArray);
        ShellSort<Double> doubleSorter = new ShellSort<>();
        System.out.println("\nTesting doubles in random order:");
        System.out.println("Before performing shell sort:");
        doubleSorter.display(doubleArray);
        startTime = System.nanoTime();
        doubleSorter.shellSort(doubleArray);
        endTime = System.nanoTime();
        System.out.println("After performing shell sort:");
        doubleSorter.display(doubleArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

    }
}
