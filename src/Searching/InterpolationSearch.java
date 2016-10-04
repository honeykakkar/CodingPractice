package Searching;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 10/2/2016
 */

// Program to implement interpolation search

/* Interpolation search is an algorithm for searching for a given key value
  in an indexed array that has been ordered by the values of the key.

  On average the interpolation search makes about log(log(n)) comparisons (if the elements are uniformly distributed),
   where n is the number of elements to be searched.
   In the worst case (for instance where the numerical values of the keys increase exponentially) it can make up to O(n) comparisons.

   Formula used is y - y1 = m (x - x1) where we y is the index of x (the value being searched).
   and, x1 is at y1 index in the array. As we know that elements are uniformly distributed,
   we use the plot of values vs their indices to determine the index of the value being searched.

   m can be deduced to (y2 - y1)/(x2 - x1)   */

    // It is similar to binary search except that the mechanism to calculate the middle element is different.

public class InterpolationSearch {

    public int findPosition(Integer element, ArrayList<Integer> arrayList){
        int left = 0, middle, right = arrayList.size()-1;

        while(!Objects.equals(arrayList.get(right), arrayList.get(left)) &&
                element >= arrayList.get(left) && element <= arrayList.get(right)){
            middle = ((right - left)/(arrayList.get(right) - arrayList.get(left)))*(element - arrayList.get(left)) + left;
            Integer midElement = arrayList.get(middle);
            if(midElement.equals(element))
                return middle;
            else {
                if (element > midElement)
                    left = middle + 1;
                else
                    right = middle - 1;
            }
        }

        if(element.equals(arrayList.get(left)))
            return left;
        return -1;
    }

    public static void main(String[] args) {
        InterpolationSearch intSearch = new InterpolationSearch();
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
