package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/17/2016
 */

/*
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7,
A solution set is:
[
  [7],
  [2, 2, 3]
]
 */

public class CombinationSum {

    // Method to get all combinations of elements to target sum
    List<List<Integer>> getSumCombinations (List<Integer> inputSet, int target){
        List<List<Integer>> resultSet = new ArrayList<>();
        sumCombination(resultSet, new ArrayList<>(), inputSet, target, 0);
        return resultSet;
    }


    private void sumCombination (List<List<Integer>> resultSet, List<Integer> currSet, List<Integer> inputSet, int remaining, int start){
        if (remaining < 0)  // If remaining is less than 0, means the higher value element was picked last time
            return;

        if (remaining == 0) // Add this combination to the result set
            resultSet.add(new ArrayList<>(currSet));
        else {
            for (int i = start; i < inputSet.size(); ++i) {
                currSet.add(inputSet.get(i));
                sumCombination(resultSet, currSet, inputSet, remaining - inputSet.get(i), i);   // Start is again i as the result set can include the same element. Performing recursion on the rest of the problem (sum).
                currSet.remove(currSet.size()- 1);  // Back-tracking here
            }
        }
    }

    // Method to get all unique combinations of elements to target sum
    List<List<Integer>> getUniqueSumCombinations (List<Integer> inputSet, int target){
        List<List<Integer>> resultSet = new ArrayList<>();
        Collections.sort(inputSet);     // Sorted the input set to get unique sum combinations
        uniqueSumCombination(resultSet, new ArrayList<>(), inputSet, target, 0);
        return resultSet;
    }

    private void uniqueSumCombination (List<List<Integer>> resultSet, List<Integer> currSet, List<Integer> inputSet, int remaining, int start) {
        if (remaining < 0)  // If remaining is less than 0, means the higher value element was picked last time
            return;

        if (remaining == 0) // Add this combination to the result set
            resultSet.add(new ArrayList<>(currSet));
        else {
            for (int i = start; i < inputSet.size(); ++i) {
                if (i > start && inputSet.get(i).equals(inputSet.get(i - 1)))
                    continue;   // As the input set is sorted, so all duplicate elements are together. If equal with last, skip.
                currSet.add(inputSet.get(i));
                uniqueSumCombination(resultSet, currSet, inputSet, remaining - inputSet.get(i), i + 1); // Start is i+1 as the result set cannot include the same element.
                currSet.remove(currSet.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        CombinationSum combinations = new CombinationSum();
        List<Integer> inputSet = new ArrayList<>(Arrays.asList(2,2,3,4,6,7,8,10));
        int targetSum = 13;
        System.out.println("Showing all combinations of elements in the array which sum up to " + targetSum);
        List<List<Integer>> resultSet = combinations.getSumCombinations(inputSet, targetSum);
        System.out.println(resultSet);

        System.out.println("\nShowing all UNIQUE combinations of elements in the array which sum up to " + targetSum);
        resultSet = combinations.getUniqueSumCombinations(inputSet, targetSum);
        System.out.println(resultSet);
    }
}
