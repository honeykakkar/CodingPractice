package Searching;

import java.util.ArrayList;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 10/2/2016
 */

// Program to implement binary search on generic items.

    // Note: In order to perform the binary search, the list of items must be sorted.

public class BinarySearch<T extends Comparable<T>> {

    public int findPosition(T element, ArrayList<T> arrayList){
        int left = 0, middle, right = arrayList.size()-1;

        while(left <= right){
            middle = left + (right - left)/2;
            T midElement = arrayList.get(middle);
            if(midElement.equals(element))
                return middle;
            else {
                if (element.compareTo(midElement) > 0)
                    left = middle + 1;
                else
                    right = middle - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch<Integer> intSearch = new BinarySearch<>();
        ArrayList<Integer> intArray = new ArrayList<>(1000);
        for (int i=1; i<=1000; ++i)
            intArray.add((int)Math.pow(i, 2));
        int element = 30;
        int position = intSearch.findPosition(element, intArray);
        System.out.println(position == -1 ? "Element " + element + " not found." : "Element " + element + " found at index: " + position + ".");
        element = 22500;
        position = intSearch.findPosition(element, intArray);
        System.out.println(position == -1 ? "Element " + element + " not found." : "Element " + element + " found at index: " + position + ".");
        element = 810000;
        position = intSearch.findPosition(element, intArray);
        System.out.println(position == -1 ? "Element " + element + " not found." : "Element " + element + " found at index: " + position + ".");
        element = 64000000;
        position = intSearch.findPosition(element, intArray);
        System.out.println(position == -1 ? "Element " + element + " not found." : "Element " + element + " found at index: " + position + ".");

    }
}
