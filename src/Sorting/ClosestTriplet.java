package Sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 10/10/2016
 */

/*
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.
    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

 */

public class ClosestTriplet {

    private List<Integer> getClosestTriplet(ArrayList<Integer> array, int targetSum) {
        // exit from function if any of the array has size 0
        List<Integer> closestTriplet = new ArrayList<>();
        if (array.size() == 0)
            return closestTriplet;

        int low, high = array.size() - 1;
        int currentSum, closestSum = array.get(0) + array.get(1) + array.get(2);
        closestTriplet.add(array.get(0));
        closestTriplet.add(array.get(1));
        closestTriplet.add(array.get(2));
        for (int i = 0; i < array.size() - 2; ++i) {
            low = i + 1;

            while (low < high) {
                currentSum = array.get(i) + array.get(low) + array.get(high);

                if (Math.abs(targetSum - currentSum) < Math.abs(targetSum - closestSum)) {
                    closestSum = currentSum;
                    closestTriplet.set(0, array.get(i));
                    closestTriplet.set(1, array.get(low));
                    closestTriplet.set(2, array.get(high));

                    if (closestSum == targetSum)
                        return closestTriplet;
                }

                if (currentSum > targetSum)
                    --high;
                else
                    ++low;
            }
        }
        return closestTriplet;
    }

    int getSum(List<Integer> list) {
        int sum = 0;
        for (int element : list)
            sum += element;
        return sum;
    }

    private <T> void display(List<T> array) {
        for (T element : array)
            System.out.print(element + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        ClosestTriplet tripletFinder = new ClosestTriplet();
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 1; i <= 20; ++i)
            array.add(3 * i);
        tripletFinder.display(array);
        int targetSum = 155;
        List<Integer> triplet = tripletFinder.getClosestTriplet(array, targetSum);
        tripletFinder.display(triplet);

        array = new ArrayList<>();
        for (int i = 1; i <= 30; ++i)
            array.add(2 * (i + 2));
        tripletFinder.display(array);
        triplet = tripletFinder.getClosestTriplet(array, targetSum);
        tripletFinder.display(triplet);
    }
}
