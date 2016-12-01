package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/17/2016
 */

/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]
 */
public class PalindromePartitioning {

    public List<List<String>> getPalindromePartitions(String s) {
        List<List<String>> list = new ArrayList<>();
        palindromePartitions(list, new ArrayList<>(), s, 0);
        return list;
    }

    public void palindromePartitions(List<List<String>> list, List<String> tempList, String s, int start) {
        if (start == s.length()) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < s.length(); i++) {
                if (isPalindrome(s, start, i)) {
                    tempList.add(s.substring(start, i + 1));
                    palindromePartitions(list, tempList, s, i + 1);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }

    public boolean isPalindrome(String s, int low, int high) {
        while (low < high)
            if (s.charAt(low++) != s.charAt(high--))
                return false;
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning partitioner = new PalindromePartitioning();
        String input = "nipinkakka";
        List<List<String>> partitions = partitioner.getPalindromePartitions(input);
        System.out.println(partitions);
    }
}
