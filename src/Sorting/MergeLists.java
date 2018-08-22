package Sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/4/2016
 */

//  Given k sorted arrays of size n each, merge them and print the sorted output.

public class MergeLists<T extends Comparable<T>> {

    private static class HeapNode<T extends Comparable<T>> implements Comparable<HeapNode<T>> {
        List<T> list;
        int index;

        HeapNode(List<T> list, int index) {
            this.list = list;
            this.index = index;
        }

        @Override
        public int compareTo(HeapNode<T> o) {
            return this.list.get(this.index).compareTo(o.list.get(index));
        }
    }

    List<T> mergeLists(List<List<T>> lists) {
        List<T> result = new ArrayList<>();
        PriorityQueue<HeapNode<T>> minHeap = new PriorityQueue<>();

        for (List<T> currentList : lists) {
            HeapNode<T> heapNode = new HeapNode<>(currentList, 0);
            minHeap.add(heapNode);
        }

        while (!minHeap.isEmpty()) {
            HeapNode<T> minNode = minHeap.poll();
            result.add(minNode.list.get(minNode.index));

            if (minNode.index + 1 < minNode.list.size()) {
                HeapNode<T> newNode = new HeapNode<>(minNode.list, minNode.index + 1);
                minHeap.add(newNode);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new ArrayList<>();
        int totalLists = 10;
        int listSize = 20;

        for (int i = 1; i <= totalLists; ++i) {
            List<Integer> newList = new ArrayList<>();
            for (int j = 1; j <= listSize; ++j)
                newList.add(j * i);
            lists.add(newList);
        }

        MergeLists<Integer> listMerger = new MergeLists<>();
        List<Integer> result = listMerger.mergeLists(lists);
        for (Integer item : result)
            System.out.print(item + " ");
    }
}
