package Sorting;

/*
 * Author: Honey Kakkar
 * Created on: December 30, 2016
 * Package: Sorting
 * Project: Coding Practice in JAVA
 */

import java.util.*;
import java.util.Map.Entry;

/*

Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

 */
public class TopKFrequent {

    // Using Map and buckets (for each frequency)
    private <T extends Comparable<T>> List<T> getTopFrequent_1(List<T> list, int K) {
        List<T> topK = new ArrayList<>(K);
        if (list == null || list.size() == 0 || K == 0)
            return topK;

        Map<T, Integer> freqMap = new HashMap<>(list.size());
        for (T elem : list)
            freqMap.put(elem, freqMap.getOrDefault(elem, 0) + 1);

        @SuppressWarnings("unchecked")
        List<T>[] buckets = new List[list.size() + 1];
        for (T key : freqMap.keySet()) {
            Integer freq = freqMap.get(key);
            if (buckets[freq] == null)
                buckets[freq] = new LinkedList<>();
            buckets[freq].add(key);
        }

        for (int i = buckets.length - 1; i >= 0 && K > 0; --i) {
            if (buckets[i] == null)
                continue;
            for (int j = 0; j < buckets[i].size() && K > 0; ++j, --K)
                topK.add(buckets[i].get(j));
        }
        return topK;
    }

    // Using Map and PriorityQueue
    private <T extends Comparable<T>> List<T> getTopFrequent_2(List<T> list, int K) {
        List<T> topK = new ArrayList<>(K);
        if (list == null || list.size() == 0 || K == 0)
            return topK;

        Map<T, Integer> freqMap = new HashMap<>(list.size());
        for (T elem : list)
            freqMap.put(elem, freqMap.getOrDefault(elem, 0) + 1);

        PriorityQueue<Entry<T, Integer>> maxHeap = new PriorityQueue<>(K, (E1, E2) -> E2.getValue() - E1.getValue());
        maxHeap.addAll(freqMap.entrySet());

        for (int i = 1; i <= K && maxHeap.size() > 0; ++i)
            topK.add(maxHeap.poll().getKey());
        return topK;
    }

    // Using Map and TreeMap
    private <T extends Comparable<T>> List<T> getTopFrequent_3(List<T> list, int K) {
        List<T> topK = new ArrayList<>(K);
        if (list == null || list.size() == 0 || K == 0)
            return topK;

        Map<T, Integer> freqMap = new HashMap<>(list.size());
        for (T elem : list)
            freqMap.put(elem, freqMap.getOrDefault(elem, 0) + 1);

        TreeMap<Integer, List<T>> sortedFreq = new TreeMap<>();
        for (Entry<T, Integer> entry : freqMap.entrySet()) {
            Integer freq = entry.getValue();
            T element = entry.getKey();
            if (!sortedFreq.containsKey(freq))
                sortedFreq.put(freq, new LinkedList<>());
            sortedFreq.get(freq).add(element);
        }

        while (topK.size() < K) {
            List<T> mostFrequent = sortedFreq.pollLastEntry().getValue();

            for (int i = 0; i < mostFrequent.size() && topK.size() < K && mostFrequent.size() > 0; ++i)
                topK.add(mostFrequent.get(i));
        }
        return topK;
    }

    public static void main(String[] args) {
        TopKFrequent finder = new TopKFrequent();
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 2, 2, 1));

        List<Integer> topKElements = finder.getTopFrequent_1(list, 3);
        if (topKElements != null)
            System.out.println(topKElements);

        topKElements = finder.getTopFrequent_2(list, 2);
        if (topKElements != null)
            System.out.println(topKElements);

        topKElements = finder.getTopFrequent_3(list, 4);
        if (topKElements != null)
            System.out.println(topKElements);
    }
}
