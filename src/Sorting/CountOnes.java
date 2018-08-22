package Sorting;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 10/9/2016
 */
public class CountOnes {

    private int countOnesHelper(byte[] binaryArray, int start, int end) {
        if (start > end)
            return 0;

        int middle = start + (end - start) / 2;
        if (binaryArray[middle] == 1 && binaryArray[middle + 1] == 0)
            return middle;

        if (middle + 1 <= end && binaryArray[middle + 1] == 1)
            return countOnesHelper(binaryArray, middle + 1, end);
        return countOnesHelper(binaryArray, start, middle - 1);
    }


    private int getCountofOnes(byte[] binaryArray) {
        if (binaryArray[0] == 0)
            return 0;
        return countOnesHelper(binaryArray, 0, binaryArray.length - 1) + 1;
    }

    public static void main(String[] args) {
        byte[] binaryArray = {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0};
        byte[] binaryArray1 = {0, 0, 0, 0, 0};
        CountOnes counter = new CountOnes();
        int ones = counter.getCountofOnes(binaryArray);
        System.out.println(ones);
        ones = counter.getCountofOnes(binaryArray1);
        System.out.println(ones);
    }
}