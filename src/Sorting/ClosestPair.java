package Sorting;

import java.util.ArrayList;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 10/10/2016
 */

// Given two sorted arrays and a number x,
// find the pair whose sum is closest to x and the pair has an element from each array.

public class ClosestPair {

    void getClosestPair(ArrayList<Integer> array1, ArrayList<Integer> array2, int value){
        // exit from function if any of the array has size 0 as one element from the resulting pair must be from each array
        if(array1.size() == 0 || array2.size() == 0)
            return;

        int pairX =0, pairY =0, minDiff = Integer.MAX_VALUE;
        int leftArray1 = 0, rightArray2 = array2.size() - 1;

        // Start scanning one array from left and the other from right as both are sorted
        while(leftArray1 < array1.size() && rightArray2 >= 0){
            // Compute the difference between two values from arrays and the value given. If less, update minimum difference
            int currDiff = Math.abs(array1.get(leftArray1) + array2.get(rightArray2) - value);
            if(currDiff < minDiff) {
                minDiff = currDiff;
                pairX = leftArray1;
                pairY = rightArray2;
            }

            // If the sum of two values from arrays is greater than value, we need to reduce the sum
            // by moving the pointer in array2 to the left to make it point to a lesser value
            if(array1.get(leftArray1) + array2.get(rightArray2) > value)
                --rightArray2;
            else
                // If the sum of two values from arrays is lesser than value, we need to increase the sum
                // by moving the pointer in array1 to the right to make it point to a greater value
                ++leftArray1;

        }

        System.out.println("\n" + array1.get(pairX) + " (from array1) and " + array2.get(pairY) + " (from array2) sums closest to " + value);
    }

    public static void main(String[] args) {
        ClosestPair pairFinder = new ClosestPair();
        ArrayList<Integer> array1 = new ArrayList<>();
        ArrayList<Integer> array2 = new ArrayList<>();
        for(int i=1; i<=20; ++i)
            array1.add( 3*i );
        for(int i=1; i<=30; ++i)
            array2.add( 2*(i+2) );
        pairFinder.getClosestPair(array1, array2, 0);
        pairFinder.getClosestPair(array1, array2, 57);
        pairFinder.getClosestPair(array1, array2, 91);
        pairFinder.getClosestPair(array1, array2, 120);

        for(int i=1; i<=20; ++i)
            array1.set(20-i, -3*i);
        for(int i=1; i<=30; ++i)
            array2.set(30-i, -2*(i+2));
        pairFinder.getClosestPair(array1, array2, 0);
        pairFinder.getClosestPair(array1, array2, -20);
        pairFinder.getClosestPair(array1, array2, -75);
        pairFinder.getClosestPair(array1, array2, -111);
    }
}
