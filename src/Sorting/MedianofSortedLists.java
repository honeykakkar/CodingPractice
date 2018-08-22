package Sorting;

import java.util.ArrayList;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 10/22/2016
 */
public class MedianofSortedLists {

    // brute force method to merge two sorted lists and calculate the median - inefficient
    private double getMedianofLists_bruteForce(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        double resultMedian;
        int size1 = list1.size(), size2 = list2.size();
        ArrayList<Integer> mergedList = new ArrayList<>(size1 + size2);
        int pointer1 = 0, pointer2 = 0;
        while (pointer1 < size1 && pointer2 < size2) {
            if (list1.get(pointer1).compareTo(list2.get(pointer2)) < 0) {
                mergedList.add(list1.get(pointer1));
                ++pointer1;
            } else {
                mergedList.add(list2.get(pointer2));
                ++pointer2;
            }
        }

        while (pointer1 < size1) {
            mergedList.add(list1.get(pointer1));
            ++pointer1;
        }

        while (pointer2 < size2) {
            mergedList.add(list2.get(pointer2));
            ++pointer2;
        }
        int n = mergedList.size();
        resultMedian = (mergedList.get((n - 1) / 2) + mergedList.get(n / 2)) / 2.0;
        return resultMedian;
    }

    // efficient method to calculate the median
    private double getMedianofLists_efficient(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        int size1 = list1.size(), size2 = list2.size();
        if (size1 > size2)
            return getMedianofLists_efficient(list2, list1);

        int iMinIndex = 0, iMaxIndex = size1;
        int i = 0, j = 0, midElement = (size1 + size2 + 1) / 2;

        int med1 = 0, med2;

        /*Binary-searching i in  list A [0, m], to find an object i such that:
        B[j−1] ≤ A[i]  and A[i−1] ≤ B[j],  where j = {m + n + 1}/{2} - i*/
        while (iMinIndex <= iMaxIndex) {
            i = (iMinIndex + iMaxIndex) / 2;
            j = midElement - i;

            if (i < size1 && j > 0 && list1.get(i) < list2.get(j - 1))
                iMinIndex = i + 1;
            else {
                if (i > 0 && j < size2 && list2.get(j) < list1.get(i - 1))
                    iMaxIndex = i - 1;
                else {
                    if (i == 0)
                        med1 = list2.get(j - 1);
                    else {
                        if (j == 0)
                            med1 = list1.get(i - 1);
                        else
                            med1 = Math.max(list1.get(i - 1), list2.get(j - 1));
                    }
                    break;
                }
            }
        }

        if ((size1 + size2) % 2 == 1)
            return med1;

        if (i == size1)
            med2 = list2.get(j);
        else {
            if (j == size2)
                med2 = list1.get(i);
            else
                med2 = Math.min(list1.get(i), list2.get(j));
        }
        return (med1 + med2) / 2.0;
    }

    /*
    if (aMid < bMid)
        Keep [aRight + bLeft]
    else
        Keep [bRight + aLeft]
     */
    private double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int l = (m + n + 1) / 2;
        int r = (m + n + 2) / 2;
        return (getkth(A, 0, B, 0, l) + getkth(A, 0, B, 0, r)) / 2.0;
    }

    private double getkth(int[] A, int aStart, int[] B, int bStart, int k) {
        if (aStart > A.length - 1)
            return B[bStart + k - 1];
        if (bStart > B.length - 1)
            return A[aStart + k - 1];
        if (k == 1)
            return Math.min(A[aStart], B[bStart]);

        int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
        if (aStart + k / 2 - 1 < A.length)
            aMid = A[aStart + k / 2 - 1];
        if (bStart + k / 2 - 1 < B.length)
            bMid = B[bStart + k / 2 - 1];

        if (aMid < bMid)
            return getkth(A, aStart + k / 2, B, bStart, k - k / 2);// Check: aRight + bLeft
        else
            return getkth(A, aStart, B, bStart + k / 2, k - k / 2);// Check: bRight + aLeft
    }

    private void display(ArrayList<Integer> array) {
        for (Integer element : array)
            System.out.print(element + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        MedianofSortedLists medianFinder = new MedianofSortedLists();
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int i = 1; i <= 10; ++i)
            list1.add(10 * i);
        medianFinder.display(list1);
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int j = 5; j < 25; ++j)
            list2.add(7 * j);
        medianFinder.display(list2);
        Double median = medianFinder.getMedianofLists_bruteForce(list1, list2);
        System.out.println(median);
        median = medianFinder.getMedianofLists_efficient(list1, list2);
        System.out.println(median);

        int[] l1 = new int[list1.size()];
        for (int i = 0; i < list1.size(); ++i)
            l1[i] = list1.get(i);

        int[] l2 = new int[list2.size()];
        for (int i = 0; i < list2.size(); ++i)
            l2[i] = list2.get(i);
        median = medianFinder.findMedianSortedArrays(l1, l2);
        System.out.println(median);

        list1 = new ArrayList<>();
        for (int i = 1; i <= 40; ++i)
            list1.add(i * i);
        medianFinder.display(list1);
        list2 = new ArrayList<>();
        for (int j = 1; j < 25; ++j)
            list2.add(j * j + 10 * j);
        medianFinder.display(list2);
        median = medianFinder.getMedianofLists_bruteForce(list1, list2);
        System.out.println(median);
        median = medianFinder.getMedianofLists_efficient(list1, list2);
        System.out.println(median);

        l1 = new int[list1.size()];
        for (int i = 0; i < list1.size(); ++i)
            l1[i] = list1.get(i);

        l2 = new int[list2.size()];
        for (int i = 0; i < list2.size(); ++i)
            l2[i] = list2.get(i);
        median = medianFinder.findMedianSortedArrays(l1, l2);
        System.out.println(median);
    }
}
