package Sorting;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/14/2016
 */
public class RotatedArray {

    private <T extends Comparable<T>> T getMinimum(T[] rotatedArray) {
        int start = 0;
        int end = rotatedArray.length - 1;

        while (start < end) {

            if (rotatedArray[start].compareTo(rotatedArray[end]) < 0)
                return rotatedArray[start];

            int middle = (start + end) / 2;

            if (rotatedArray[start].compareTo(rotatedArray[middle]) < 0) // It means the left side is sorted
                start = middle + 1;     // So the rotation point must be in the right side, thus the minimum.
            else if (rotatedArray[start].compareTo(rotatedArray[middle]) > 0)
                end = middle;
            else
                start++;
        }
        return rotatedArray[start];
    }

    private int search(Integer sortedArray[], int key) {
        int start = 0, end = sortedArray.length - 1;
        while (start <= end) {
            int middle = start + (end - start) / 2;
            if (sortedArray[middle] == key)
                return middle;

            if (sortedArray[start] < sortedArray[middle]) { // left half is sorted
                if (sortedArray[start] <= key && sortedArray[middle] > key)
                    end = middle - 1;
                else
                    start = middle + 1;
            } else if (sortedArray[start] > sortedArray[middle]) { // right half is sorted
                if (sortedArray[middle] < key && sortedArray[end] >= key)
                    start = middle + 1;
                else
                    end = middle - 1;
            } else
                start++;
        }
        return -1;
    }

    public static void main(String[] args) {
        Integer[] array = {4, 5, 6, 7, 8, 9, 17, -7, -2, 1, 2, 3};
        RotatedArray minFinder = new RotatedArray();
        Integer minElement = minFinder.getMinimum(array);
        int keyIndex = minFinder.search(array, minElement);
        System.out.println(minElement + " at index " + keyIndex);
    }
}
