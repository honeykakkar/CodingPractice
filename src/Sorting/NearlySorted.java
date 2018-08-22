package Sorting;

/*
 * Author: Honey Kakkar
 * Created on: January 03, 2017
 * Package: Sorting
 * Project: Coding Practice in JAVA
 */

import java.util.PriorityQueue;

/*
Given an array of n elements, where each element is at most k away from its target position,
devise an algorithm that sorts in less than O(n log n) time.
 */

public class NearlySorted {

    private void display(int[] array) {
        for (int a : array)
            System.out.print(a + " ");
        System.out.println();
    }

    // Overall complexity is O(n*k) when each element is at most k positions away from its target position
    private void insertionSort(int[] array) {

        for (int i = 0; i < array.length; ++i) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                --j;
            }
            array[j + 1] = current;
        }
    }

    /*
    Create a Min Heap of size k+1 with first k+1 elements. This will take O(k) time.
    One by one remove min element from heap, put it in result array, and
    add a new element to heap from remaining elements.

    Removing an element and adding a new element to min heap will take log k time.
    So overall complexity will be O(K) + O((n-K)*logK)
     */
    private void minHeap(int[] array, int K) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(K);
        for (int i = 0; i <= K; ++i)
            minHeap.offer(array[i]);

        int start = 0;
        for (int i = K + 1; start < array.length; ++i, ++start) {
            int min = minHeap.poll();
            if (i < array.length)
                minHeap.offer(array[i]);
            array[start] = min;
        }
    }

    public static void main(String[] args) {
        NearlySorted sorter = new NearlySorted();
        int[] array1 = {3, 2, 1, 4, 6, 7, 5, 10, 8, 9};
        sorter.display(array1);
        sorter.insertionSort(array1);
        sorter.display(array1);
        System.out.println();

        int[] array2 = {3, 2, 1, 4, 6, 7, 5, 10, 8, 9};
        sorter.display(array2);
        sorter.minHeap(array2, 2);
        sorter.display(array2);
    }
}
