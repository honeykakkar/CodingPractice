package Sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/14/2016
 */

/*
Given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element
from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 */

// Similar to Sorted Matrix problem
/*Visualize the input as an m×n matrix of sums, for example for nums1=[1,7,11], and nums2=[2,4,6]:

                2   4   6
            +------------
            1 |  3   5   7
            7 |  9  11  13
            11| 13  15  17
*/

public class KSmallestPairs {

    class Pair implements Comparable<Pair> {
        int[] pair;
        int index; // current index to nums2
        long sum;

        Pair(int idx, int n1, int n2) {
            this.index = idx;
            pair = new int[]{n1, n2};
            sum = (long) n1 + (long) n2;
        }

        @Override
        public int compareTo(Pair o) {
            return Long.compare(this.sum, o.sum);
        }
    }

    /*
    To get the idea clear, you can think that this is the problem to merge k sorted arrays.
    array1 = (0,0),(0,1),(0,2),....
    array2 = (1,0),(1,1),(1,2),....
    arrayk = (k-1,0),(k-1,1),(k-1,2),....

    So, each time when an array is chosen having the smallest sum,
    you only move its index to next one of this array.
     */
    private List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> resultPairs = new ArrayList<>();
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0)
            return resultPairs;
        int len2 = nums2.length;

        PriorityQueue<Pair> minHeap = new PriorityQueue<>(k);

        for (int i = 0; i < nums1.length && i < k; i++) { // only need first k number in nums1 to start
            minHeap.offer(new Pair(0, nums1[i], nums2[0]));
        }

        for (int i = 1; i <= k && !minHeap.isEmpty(); i++) { // get the first k sums
            Pair polled = minHeap.poll();
            resultPairs.add(polled.pair);
            if (polled.index < len2 - 1) { // get to next value in nums2
                int next = polled.index + 1;
                minHeap.offer(new Pair(next, polled.pair[0], nums2[next]));
            }
        }
        return resultPairs;
    }

    public static void main(String[] args) {
        KSmallestPairs kSmallestPairs = new KSmallestPairs();
        int[] sortedArray1 = {1, 3, 5, 7, 8};
        int[] sortedArray2 = {1, 2, 3, 4, 5, 6};
        int K = 4;
        List<int[]> pairs = kSmallestPairs.kSmallestPairs(sortedArray1, sortedArray2, 6);
        for (int[] array : pairs)
            System.out.format("[%d, %d] ", array[0], array[1]);
    }
}
