package Sorting;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 10/5/2016
 */
public class RunningMedian {
    public static void main(String args[] ) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // Priority Queue is an implementation of min heap by default. Creating max heap out of it by reversing the comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(n, Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        double median = 0.0;
        int maxSize = 0;
        int minSize = 0;

        for(int i=0; i<n; ++i){
            int input = sc.nextInt();
            int diffSize = maxSize - minSize;
            switch(diffSize){
                case 0: // If both heaps are equal, compare the new value with median
                    if(input < median)
                        maxHeap.offer(input);   // If less than old median, new values goes to max heap, else to min heap
                    else
                        minHeap.offer(input);
                    break;
                case 1:// If max heap has one element more than min heap
                    if(input < median){ // If new value is less than old median, pop the root and insert to min heap and insert new value to max heap
                        minHeap.offer(maxHeap.poll()); // Done so to have difference in sizes of heaps utmost to 1
                        maxHeap.offer(input);
                    }
                    else
                        minHeap.offer(input);
                    break;
                case -1:
                    if(input < median)
                        maxHeap.offer(input);
                    else {
                        maxHeap.offer(minHeap.poll());  // If new value is less than old median, pop the root and insert to max heap and insert new value to min heap
                        minHeap.offer(input);
                    }
                    break;
            }

            // Can be optimized by updating value to the median in the switch case.
            // I have done this way to increase the readability of the code.

            maxSize = maxHeap.size();
            minSize = minHeap.size();

            // If both heaps are equal, take average of both roots,
            // or median is the value of root having greater size

            if(maxSize == minSize) {
                int maxTop = maxHeap.peek();
                int minTop = minHeap.peek();
                median = (double)(maxTop + minTop) / 2;
            }
            else{
                if(maxSize > minSize)
                    median = maxHeap.peek();
                else
                    median = minHeap.peek();
            }
            System.out.println(median);
        }
    }
}