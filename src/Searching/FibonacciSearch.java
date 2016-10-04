package Searching;

import java.util.ArrayList;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 10/2/2016
 */

// Inspired from code available on Geeksforgeeks.org

// Program to implement fibonacci search on generic items.

    // Note: In order to perform the fibonacci search, the list of items must be sorted.

public class FibonacciSearch<T extends Comparable<T>> {

    public int findPosition(T element, ArrayList<T> arrayList){
        int arraySize = arrayList.size();
        int fibCurrent_2 = 0;   // (m-2)'th Fibonacci No.
        int fibCurrent_1 = 1;   // (m-1)'th Fibonacci No.
        int fibCurrent = fibCurrent_2 + fibCurrent_1;  // m'th Fibonacci

    /* fibCurrent is going to store the smallest Fibonacci
       Number greater than or equal to arraySize */
        while (fibCurrent < arraySize) {
            fibCurrent_2 = fibCurrent_1;
            fibCurrent_1 = fibCurrent;
            fibCurrent  = fibCurrent_2 + fibCurrent_1;
        }

        // Marks the eliminated range from front
        int offset = -1;
        while (fibCurrent > 1) {
            // Check if fibCurrent_2 + offset is a valid location
            int i = Math.min(offset + fibCurrent_2, arraySize - 1);

            if (arrayList.get(i).compareTo(element) < 0) {
                fibCurrent  = fibCurrent_1;
                fibCurrent_1 = fibCurrent_2;
                fibCurrent_2 = fibCurrent - fibCurrent_1;
                offset = i;
            }
            else
                if (arrayList.get(i).compareTo(element) > 0) {
                fibCurrent  = fibCurrent_2;
                fibCurrent_1 = fibCurrent_1 - fibCurrent_2;
                fibCurrent_2 = fibCurrent - fibCurrent_1;
            }
            else
                return i;
        }
 
    /* comparing the last element with element */
        if(fibCurrent_1 == 1 && offset + 1 < arraySize && arrayList.get(offset+1) == element)
            return offset+1;

        return -1;
    }

    public static void main(String[] args) {
        FibonacciSearch<Integer> intSearch = new FibonacciSearch<>();
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
