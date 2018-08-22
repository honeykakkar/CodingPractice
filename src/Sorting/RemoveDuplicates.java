package Sorting;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/14/2016
 */

/*Given a sorted array, remove the duplicates in place such that
each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

FOLLOW UP: What if duplicates are allowed for at most 2 times?*/

public class RemoveDuplicates {

    private int removeDuplicates(int[] sortedArray) {
        if (sortedArray.length <= 1)
            return 1;

        int count = 1;
        for (int i = 1; i < sortedArray.length; ++i) {
            if (sortedArray[i] != sortedArray[i - 1]) {
                sortedArray[count] = sortedArray[i];
                ++count;
            }
        }
        return count;
    }

    private int removeDuplicates_K(int[] sortedArray) {
        if (sortedArray.length <= 2)
            return 2;

        int count = 0;
        int[] output;
        for (int i = 0; i < sortedArray.length; ++i) {
            if (i < 2 || sortedArray[i] > sortedArray[i - 2]) {
                sortedArray[count] = sortedArray[i];
                ++count;
            }
        }
        return count;
    }

    private void display(int[] array, int count) {
        for (int i = 0; i < count; ++i)
            System.out.print(array[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] sortedArray = {1, 1, 1, 1, 2, 2, 2, 3, 4, 5, 5, 5, 6, 7, 7, 7, 7, 8};
        RemoveDuplicates duplicateRemover = new RemoveDuplicates();
        int count = duplicateRemover.removeDuplicates(sortedArray);
        duplicateRemover.display(sortedArray, count);
        sortedArray = new int[]{1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 5, 5, 5, 6, 7, 7, 7, 7, 8, 8, 8, 8, 8, 9, 10, 11, 12, 13, 13, 13, 13};
        count = duplicateRemover.removeDuplicates_K(sortedArray);
        duplicateRemover.display(sortedArray, count);
    }
}
