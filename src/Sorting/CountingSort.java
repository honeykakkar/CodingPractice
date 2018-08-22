package Sorting;

import java.util.Random;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 10/6/2016
 */

public class CountingSort {
    /**
     * Counting Sort function
     **/
    private void intSort_Unstable(int[] array) {
        int N = array.length;
        if (N == 0)
            return;
        /* find max and min values */
        int max = array[0], min = array[0];
        for (int i = 1; i < N; i++) {
            if (array[i] > max)
                max = array[i];
            if (array[i] < min)
                min = array[i];
        }
        int range = max - min + 1;

        int[] count = new int[range];
        /* make count/frequency array for each element */
        for (int elem : array)
            count[elem - min]++;
        /* modify count so that positions in final array is obtained */
        for (int i = 1; i < range; i++)
            count[i] += count[i - 1];
        /* modify original array **/
        int j = 0;
        for (int i = 0; i < range; i++)
            while (j < count[i])
                array[j++] = i + min; // <-- NOT stable since it generates the numbers instead of accessing from the original array
    }

    // Stable sorting
    private int[] intSort(int[] array) {
        int N = array.length;
        if (N == 0)
            return new int[0];
        /* find max and min values */
        int max = array[0], min = array[0];
        int sorted[] = new int[N];
        for (int i = 1; i < N; i++) {
            if (array[i] > max)
                max = array[i];
            if (array[i] < min)
                min = array[i];
        }
        int range = max - min + 1;

        int[] count = new int[range];
        /* make count/frequency array for each element */
        for (int elem : array)
            count[elem - min]++;
        /* modify count so that positions in final array is obtained */
        for (int i = 1; i < range; i++)
            count[i] += count[i - 1];
        /* Populate new "sorted" array
        *
        * Sorting is stable ONLY when run in the reverse order.
        * Example: array[] = {0,3a,1,3b,2}, count[] would be [1, 1, 1, 2]
        * Since 3b would be counted after 3a. When traversing the array
        * from last, it helps to place 3b first at HIGHER index.
        * */
        for (int i = N-1; i >= 0; --i){
            int finalPos = count[array[i] - min] - 1;
            sorted[finalPos] = array[i];
            --count[array[i] - min];
        }
        return sorted;
    }

    // Method to display the list of elements
    private void display(int[] array) {
        for (int element : array)
            System.out.print(element + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 30;
        long startTime, endTime;
        // Testing when integers are in reverse order
        int[] intArray = new int[n];
        for (int i = 0; i < 30; ++i)
            intArray[i] = n - i;
        CountingSort intSorter = new CountingSort();
        System.out.println("Testing integers in reverse order:");
        System.out.println("Before performing the counting sort:");
        intSorter.display(intArray);
        startTime = System.nanoTime();
        intSorter.intSort_Unstable(intArray);
        endTime = System.nanoTime();
        System.out.println("After performing the counting sort:");
        intSorter.display(intArray);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");

        // Testing when integers are in no order
        int[] randomInts = new Random().ints(1, 31).distinct().limit(30).toArray();
        System.out.println("\nTesting integers in random order:");
        System.out.println("Before performing the counting sort:");
        intSorter.display(randomInts);
        startTime = System.nanoTime();
        int[] sorted = intSorter.intSort(randomInts);
        System.out.println("After performing the counting sort:");
        endTime = System.nanoTime();
        intSorter.display(sorted);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");


        // Testing when integers are in reverse order
        intArray = new int[n];
        for (int i = 1; i <= 30; ++i)
            intArray[i - 1] = -i * 3;
        System.out.println("\nTesting negative integers in reverse order:");
        System.out.println("Before performing the counting sort:");
        intSorter.display(intArray);
        startTime = System.nanoTime();
        sorted = intSorter.intSort(intArray);
        endTime = System.nanoTime();
        System.out.println("After performing the counting sort:");
        intSorter.display(sorted);
        System.out.println("It took " + (endTime - startTime) + " ns to perform the sort.");
    }
}