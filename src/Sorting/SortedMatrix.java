package Sorting;

import java.util.PriorityQueue;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/14/2016
 */

/*
Given a n row n matrix where each of the rows and columns are sorted in ascending order,
 find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 */
class SortedMatrix {

    class Tuple implements Comparable<Tuple> {
        int row, col, val;

        Tuple(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }

        @Override
        public int compareTo(Tuple that) {
            return this.val - that.val;
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> minHeap = new PriorityQueue<>(n);

        for (int j = 0; j <= n - 1; j++)
            minHeap.offer(new Tuple(0, j, matrix[0][j]));

        for (int i = 0; i < k - 1; i++) {
            Tuple t = minHeap.poll();
            if (t.row == n - 1)
                continue;
            minHeap.offer(new Tuple(t.row + 1, t.col, matrix[t.row + 1][t.col]));
        }
        return minHeap.poll().val;
    }
}

