package Sorting;

import java.util.ArrayList;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 10/9/2016
 */

// Given three arrays sorted in non-decreasing order, print all common elements in these arrays.

public class CommonInThree {

    private <T extends Comparable<T>> ArrayList<T> getCommonElements(ArrayList<T> array1, ArrayList<T> array2, ArrayList<T> array3) {
        ArrayList<T> commonElements = new ArrayList<>();
        if (array1.size() == 0 || array2.size() == 0 || array3.size() == 0)
            return commonElements;

        int currArray1 = 0, currArray2 = 0, currArray3 = 0;

        while (currArray1 != array1.size() - 1 && currArray2 != array2.size() - 1 && currArray3 != array3.size() - 1) {

            if (array1.get(currArray1).compareTo(array2.get(currArray2)) == 0 &&
                    array2.get(currArray2).compareTo(array3.get(currArray3)) == 0) {
                commonElements.add(array1.get(currArray1));
                ++currArray1;
                ++currArray2;
                ++currArray3;
                continue;
            }

            if (array1.get(currArray1).compareTo(array2.get(currArray2)) < 0)
                ++currArray1;
            else {
                if (array2.get(currArray2).compareTo(array3.get(currArray3)) < 0)
                    ++currArray2;
                else
                    ++currArray3;
            }
        }
        return commonElements;
    }

    public static void main(String[] args) {
        CommonInThree commonFinder = new CommonInThree();
        ArrayList<Integer> array1 = new ArrayList<>();
        ArrayList<Integer> array2 = new ArrayList<>();
        ArrayList<Integer> array3 = new ArrayList<>();

        for (int i = 1; i <= 30; ++i)
            array1.add(3 * i);
        for (int i = 1; i <= 20; ++i)
            array2.add(2 * (i + 2));
        for (int i = 1; i <= 50; ++i)
            array3.add(4 * (i + 1));

        ArrayList<Integer> result = commonFinder.getCommonElements(array1, array2, array3);
        for (Integer aResult : result)
            System.out.print(aResult + " ");
    }
}
