package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/17/2016
 */
public class Permutations {

    <T> List<List<T>> getAllPermutations (List<T> inputSet){
        List<List<T>> resultSet = new ArrayList<>();
        allPermutations(resultSet, new ArrayList<>(), inputSet);
        return resultSet;
    }

    <T> void allPermutations (List<List<T>> resultSet, List<T> currSet, List<T> inputSet){
        if (currSet.size() == inputSet.size())
            resultSet.add(new ArrayList<>(currSet));
        else{
            for (int i=0; i<inputSet.size(); ++i){
                if (currSet.contains(inputSet.get(i)))
                    continue;
                currSet.add(inputSet.get(i));
                allPermutations(resultSet, currSet, inputSet);
                currSet.remove(currSet.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Permutations perms = new Permutations();
        List<Integer> intSet = new ArrayList<>(Arrays.asList(5,4,3,2,1));
        List<List<Integer>> intResult = perms.getAllPermutations(intSet);
        System.out.println("Total " + intResult.size() + " permutations found.");
        System.out.println(intResult);

        List<Character> charSet = new ArrayList<>();
        for (char curr : "honey".toCharArray())
            charSet.add(curr);
        List<List<Character>> charResult = perms.getAllPermutations(charSet);
        System.out.println("\nTotal " + charResult.size() + " permutations found.");
        System.out.println(charResult);
    }
}
