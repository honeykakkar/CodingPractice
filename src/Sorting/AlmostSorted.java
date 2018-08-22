package Sorting;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 10/8/2016
 */

// Given an unsorted array array.get([0..n-1] of size n, find the minimum length sub-array array.get([s..e]
//  such that sorting this sub-array makes the whole array sorted.


// 1. Find the first element from left with its value greater than next : s
// 2. Find the first element from right with its value lesser than previous : e
// 3. Include more elements in candidate sub-array [s - e] if necessary
// a. Find min and max of the sub-array
// b. Find the first element in array[0 - s-1] which has its value greater than min in sub-array. Change s to its index
// c. Find the first element in array[s+1 - n-1] which has its value lesser than max in sub-array. Change e to its index

public class AlmostSorted {

    private <T extends Comparable<T>> void sortedAlmost(ArrayList<T> array) {
        int n = array.size();
        int startSub, endSub;
        T maxElement, minElement;

        // Finding start position of possible sub-array
        for (startSub = 0; startSub < n - 1; startSub++) {
            if (array.get(startSub).compareTo(array.get(startSub + 1)) > 0)
                break;
        }

        if (startSub == n - 1) {
            System.out.println("The complete array is sorted");
            return;
        }

        // Finding end position of possible sub-array
        for (endSub = n - 1; endSub > 0; endSub--) {
            if (array.get(endSub).compareTo(array.get(endSub - 1)) < 0)
                break;
        }

        maxElement = array.get(startSub);
        minElement = array.get(startSub);

        // Finding the minimum and the maximum element in sub-array
        for (int i = startSub + 1; i <= endSub; i++) {
            if (array.get(i).compareTo(maxElement) > 0)
                maxElement = array.get(i);
            if (array.get(i).compareTo(minElement) < 0)
                minElement = array.get(i);
        }

        // Including more elements in sub-array if any element from start of the original is
        // found to be greater than the minimum in sub-array
        for (int i = 0; i < startSub; i++) {
            if (array.get(i).compareTo(minElement) > 0) {
                startSub = i;
                break;
            }
        }

        // Including more elements in sub-array if any element from end of the original is
        // found to be lesser than the maximum in sub-array
        for (int i = n - 1; i >= endSub + 1; i--) {
            if (array.get(i).compareTo(maxElement) < 0) {
                endSub = i;
                break;
            }
        }
        System.out.printf("Sort array (0-indexed) from index %d till %d (both inclusive) to get the whole array sorted.", startSub, endSub);
    }

    // method to display the elements in the list
    private <T> void display(ArrayList<T> array) {
        for (T element : array)
            System.out.print(element + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayList<Integer> intArray = new ArrayList<>(15);
        int[] randomInts = new Random().ints(1, 16).distinct().limit(15).toArray();
        for (int randomInt : randomInts) intArray.add(randomInt);
        AlmostSorted sorter = new AlmostSorted();
        sorter.display(intArray);
        sorter.sortedAlmost(intArray);
        System.out.println("\n");

        intArray = new ArrayList<>();
        int[] ints = {10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60};
        for (int integer : ints) intArray.add(integer);
        sorter.display(intArray);
        sorter.sortedAlmost(intArray);
    }
}
