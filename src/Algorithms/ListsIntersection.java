package Algorithms;

import java.util.*;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 10/19/2016
 */

/*
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
*/

public class ListsIntersection<T extends Comparable<T>> {

    // Method to get the intersection of two unsorted lists
    ArrayList<T> getIntersection_Unsorted(ArrayList<T> list1, ArrayList<T> list2){
        ArrayList<T> intersectionList = new ArrayList<>();
        HashMap<T, Integer> frequencyMap = new HashMap<>(); // store frequencies of occurrences of each number

        // Traverse list1 and increase frequencies of each number found in the list
        for(T item : list1){
            if(frequencyMap.get(item) == null)
                frequencyMap.put(item , 1);
            else
                frequencyMap.put(item, frequencyMap.get(item) + 1);
        }

        // Traverse list2 and decrease the frequencies in the map.
        // Maintain an intersection list in which an element gets added when it has already been encountered in list1
        // (in other words, when frequency map contains an entry for the number)
        // During this traversal, no new entry is added. Frequencies are only decreased.

        for(T item : list2){
            if(frequencyMap.get(item) != null && frequencyMap.get(item) > 0){
                intersectionList.add(item);
                frequencyMap.put(item, frequencyMap.get(item) - 1);
            }
        }
        return intersectionList;
    }

    // Method to get the intersection of two sorted lists, no need of extra space.
    ArrayList<T> getIntersection_Sorted(ArrayList<T> list1, ArrayList<T> list2){
        ArrayList<T> intersectionList = new ArrayList<>();
        int pointer1 = 0, pointer2 = 0;

        while (pointer1 < list1.size() && pointer2 < list2.size()){
            int comparison = list1.get(pointer1).compareTo(list2.get(pointer2));
            if(comparison < 0)
                ++pointer1;
            else {
                if(comparison > 0)
                    ++pointer2;
                else{
                    intersectionList.add(list1.get(pointer1));
                    ++pointer1;
                    ++pointer2;
                }
            }
        }
        return intersectionList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(11);
        list1.add(1);
        list1.add(2);
        list1.add(1);
        list1.add(7);
        list1.add(1);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(13);
        list2.add(19);
        list2.add(2);
        list2.add(14);
        list2.add(11);
        list2.add(1);
        list2.add(17);
        list2.add(2);
        list2.add(1);

        ListsIntersection<Integer> listsIntersection = new ListsIntersection<>();
        ArrayList<Integer> uIntersection = listsIntersection.getIntersection_Unsorted(list1, list2);
        for (Integer item : uIntersection)
            System.out.print(item + " ");
        System.out.println();

        Collections.sort(list1);
        Collections.sort(list2);
        ArrayList<Integer> sIntersection = listsIntersection.getIntersection_Sorted(list1,list2);
        for (Integer item : sIntersection)
            System.out.print(item + " ");
        System.out.println();
    }
}