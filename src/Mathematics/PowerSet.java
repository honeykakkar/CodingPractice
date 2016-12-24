package Mathematics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/1/2016
 */

/*
0) 0 0 0  -> Don't take 3 , Don't take 2 , Don't take 1 = { }
1) 0 0 1  -> Don't take 3 , Don't take 2 ,   take 1       =  {1 }
2) 0 1 0  -> Don't take 3 ,    take 2    , Don't take 1 = { 2 }
3) 0 1 1  -> Don't take 3 ,    take 2       ,      take 1    = { 1 , 2 }
4) 1 0 0  ->    take 3      , Don't take 2  , Don't take 1 = { 3 }
5) 1 0 1  ->    take 3      , Don't take 2  ,     take 1     = { 1 , 3 }
6) 1 1 0  ->    take 3      ,    take 2       , Don't take 1 = { 2 , 3 }
7) 1 1 1  ->    take 3     ,      take 2     ,      take 1     = { 1 , 2 , 3 }
 */

public class PowerSet {

    // Iterative approach - doesn't work with duplicate elements
    List<List<Integer>> getAllSubSets (List<Integer> array){
        Collections.sort(array); // to get sorted or reverse sorted subsets
        int totalSubsets = (int)Math.pow(2, array.size());
        List<List<Integer>> subsets = new ArrayList<>(totalSubsets);

        // traversing from 0 to 2 to the power of n.
        for(int i=0; i<totalSubsets; ++i){
            List<Integer> currentSet = new ArrayList<>();
            // traversing each bit of i
            for (int j=0; j<array.size(); ++j){
                // checking if the bit is set. If yes, include it in the current set.
                if((i & (1 << j)) != 0)
                    currentSet.add(array.get(j));
            }
            subsets.add(currentSet);
        }
        return subsets;
    }

    // Backtrack approach -- works well with duplicates, returns unique subsets
    public List<List<Integer>> getAllSubsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1])
                continue; // skips duplicates. Works only if nums sequence is sorted
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>(Arrays.asList(2,7,2,7,2));
        PowerSet powerSet = new PowerSet();
        List<List<Integer>> subsets = powerSet.getAllSubSets(array);
        System.out.println(subsets);
        System.out.println(subsets.size());

        int[] intArray = new int[array.size()];
        for(int i=0; i<array.size(); ++i)
            intArray[i] = array.get(i);
        subsets = powerSet.getAllSubsets(intArray);
        System.out.println(subsets);
        System.out.println(subsets.size());

    }
}
