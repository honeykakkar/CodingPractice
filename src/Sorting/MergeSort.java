package Sorting;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 9/29/2016
 */

    // Program to implement the merge sort. Merge sort performs in O(nlogn) time irrespective of ordering of the elements
    
public class MergeSort<T extends Comparable<T>> {

    // method to implement merge sort.

    private void mergeArrays(ArrayList<T> array, int start, int middle, int end){

        // Calculate left array size and copy elements in a new array
        int leftSize = middle - start + 1;
        ArrayList<T> leftArray = new ArrayList<>(leftSize);
        for (int i=start; i<=middle; ++i)
            leftArray.add(array.get(i));

        // Calculate right array size and copy elements in a new array
        int rightSize = end - middle;
        ArrayList<T> rightArray = new ArrayList<>(rightSize);
        for (int i=middle + 1; i<=end; ++i)
            rightArray.add(array.get(i));

        // Initialize pointers to indices of values being compared to 0 is left and right array
        // Initialize pointer to start index in original array. As we have left and right arrays, we can overwrite values directly in the original array
        int leftIndex = 0, rightIndex = 0, arrayIndex = start;

        // compare values of left array and right array until the indices are valid
        while(leftIndex < leftSize && rightIndex < rightSize) {

            // Copy the value from left array if it is smaller than right array's value at current pointers
            if (leftArray.get(leftIndex).compareTo(rightArray.get(rightIndex)) <= 0) {
                array.set(arrayIndex, leftArray.get(leftIndex));
                ++leftIndex;
            } else {
                array.set(arrayIndex, rightArray.get(rightIndex));
                ++rightIndex;
            }
            // Increment index in the original array for the next targeted position
            ++arrayIndex;
        }

        // As either left or right array would have leftover elements which don't get copied,
        // so only one of the following two loops would run.

        //Copy the leftover values from left array if any
        while(leftIndex < leftSize){
            array.set(arrayIndex, leftArray.get(leftIndex));
            ++leftIndex;
            ++arrayIndex;
        }

        //Copy the leftover values from right array if any
        while(rightIndex < rightSize){
            array.set(arrayIndex, rightArray.get(rightIndex));
            ++rightIndex;
            ++arrayIndex;
        }
    }

    private void mergeSort(ArrayList<T> array, int start, int end) {
        // Enter only if there are at least two elements
        if (start < end) {
            int middleIndex = (start + end) / 2;

            // Sort left array
            mergeSort(array, start, middleIndex);

            // Sort right array
            mergeSort(array, middleIndex + 1, end);

            // Merge left and right sorted arrays into the original
            mergeArrays(array, start, middleIndex, end);
        }
    }

    // method to display the list of elements
    private void display(ArrayList<T> array){
        for (T element : array)
            System.out.print(element + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 30;
        long startTime, endTime;
        // Testing when integers are in reverse order
        ArrayList<Integer> intArray = new ArrayList<>(n);
        for(int i=0; i<n; ++i)
            intArray.add(n-i);
        MergeSort<Integer> intSorter = new MergeSort<>();
        System.out.println("Testing integers in reverse order:");
        System.out.println("Before performing merge sort:");
        intSorter.display(intArray);
        startTime = System.nanoTime();
        intSorter.mergeSort(intArray, 0, intArray.size()-1);
        endTime = System.nanoTime();
        System.out.println("After performing merge sort:");
        intSorter.display(intArray);
        System.out.println("It took " + (endTime-startTime) + " ns to perform the sort.");

        // Testing when integers are in no order
        intArray = new ArrayList<>(n);
        int[] randomInts = new Random().ints(1,31).distinct().limit(30).toArray();
        for (int randomInt : randomInts) intArray.add(randomInt);
        System.out.println("\nTesting integers in random order:");
        System.out.println("Before performing merge sort:");
        intSorter.display(intArray);
        startTime = System.nanoTime();
        intSorter.mergeSort(intArray, 0, intArray.size()-1);
        System.out.println("After performing merge sort:");
        endTime = System.nanoTime();
        intSorter.display(intArray);
        System.out.println("It took " + (endTime-startTime) + " ns to perform the sort.");

        n = 26;
        // Testing when characters are already sorted
        ArrayList<Character> charArray = new ArrayList<>(n);
        for(int i=0; i<n; ++i)
            charArray.add((char)(65+i));
        MergeSort<Character> charSorter = new MergeSort<>();
        System.out.println("\nTesting characters in sorted order:");
        System.out.println("Before performing merge sort:");
        charSorter.display(charArray);
        startTime = System.nanoTime();
        charSorter.mergeSort(charArray, 0, charArray.size()-1);
        endTime = System.nanoTime();
        System.out.println("After performing merge sort:");
        charSorter.display(charArray);
        System.out.println("It took " + (endTime-startTime) + " ns to perform the sort.");

        // Testing when doubles are in random order
        // It is advised to use double instead of float
        // as JAVA, by default, uses double to represent its floating-point numerals

        double[] randomArray = new Random().doubles(1,21).distinct().limit(50).toArray();
        ArrayList<Double> doubleArray = new ArrayList<>(50);
        for (double aRandomArray : randomArray) doubleArray.add(aRandomArray);
        MergeSort<Double> doubleSorter = new MergeSort<>();
        System.out.println("\nTesting doubles in random order:");
        System.out.println("Before performing merge sort:");
        doubleSorter.display(doubleArray);
        startTime = System.nanoTime();
        doubleSorter.mergeSort(doubleArray, 0, doubleArray.size()-1);
        endTime = System.nanoTime();
        System.out.println("After performing merge sort:");
        doubleSorter.display(doubleArray);
        System.out.println("It took " + (endTime-startTime) + " ns to perform the sort.");
    }
}
