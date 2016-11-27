package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/20/2016
 */

// Prefix sum array maintains the sum of elements encountered till a given index.
  //  For array 1,2,-1,-1,2, the prefix sum array will be: 1, 3, 2, 1, 3

// Given an unsorted array of integers, find a subarray which adds to a given number.
// If there are more than one sub-arrays with sum as the given number, print any of them.

    // Also, Equilibrium index of an array is an index such that
// the sum of elements at lower indexes is equal to the sum of elements at higher indexes.
    // Find equilibrium index of array.

public class PrefixSum {

    ArrayList<Integer> prefixSum;

    PrefixSum(ArrayList<Integer> array) {
        if (array == null || array.size() == 0)
            throw new IllegalArgumentException("Array must not be null or empty.");
        prefixSum = new ArrayList<>();
        prefixSum.add(array.get(0));
        for (int i = 1; i < array.size(); ++i)
            prefixSum.add(prefixSum.get(i - 1) + array.get(i));
    }

    // Method to find the sub-array with the given sum.
    // The idea is to calculate the prefix sum array and then find pair of difference with target sum in prefix sum array.
    // The sub array lies between the pair indices.

    void subArraySum(int targetSum) {
        HashMap<Integer, Integer> preSumMap = new HashMap<>();

        for (int i = 0; i < prefixSum.size(); i++) {
            if (prefixSum.get(i) == targetSum) {
                System.out.println("Sum found between indexes " + 0 + " to " + i);
                //return;
            }

            if (preSumMap.get(prefixSum.get(i) - targetSum) != null) {
                System.out.println("Sum found between indexes " + (preSumMap.get(prefixSum.get(i) - targetSum) + 1)
                        + " to " + i);
                //return;
            }
            preSumMap.put(prefixSum.get(i), i);
        }
    }

    // Equilibrium index of an array is an index such that
    // the sum of elements at lower indexes is equal to the sum of elements at higher indexes.
    int findEquilibrium() {
        int n = prefixSum.size();
        int totalSum = prefixSum.get(n - 1);
        for (int i = 1; i < n; ++i) {
            if (prefixSum.get(i - 1) == (totalSum - prefixSum.get(i)))
                return i;
        }
        return -1;
    }

    void displayPrefixSum() {
        System.out.println();
        for (Integer elem : prefixSum)
            System.out.print(elem + " ");
        System.out.println();

    }


    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(-5, 1, 5, 2, -4, 3, 0));
        PrefixSum prefixSum = new PrefixSum(array);
        System.out.println("Displaying the prefix sum array for the input array:");
        prefixSum.displayPrefixSum();
        prefixSum.subArraySum(1);
        System.out.println();
        int equilibrium = prefixSum.findEquilibrium();
        System.out.println("The equilibrium index in the array is " + equilibrium);
    }
}