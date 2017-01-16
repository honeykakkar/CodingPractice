package StringManipulation;

import java.util.*;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/4/2016
 */

// Given two strings, write a method to decide if one is a permutation of the other.

public class Anagrams
{
    private boolean isAnagram(CharSequence input1, CharSequence input2)
    {
        if(input1.length() != input2.length())
            return false;
        int[] char_flags = new int[256];
        for(int i=0; i<input1.length(); ++i)
            ++char_flags[input1.charAt(i)];

        for (int i=0; i<input2.length(); ++i)
        {
            if(--char_flags[input2.charAt(i)] < 0)
                return false;
        }
        return true;
    }

    // method to group anagrams together given a list of strings
    private List<List<String>> groupAnagramsTogether (List<String> strings){
        HashMap<String, List<String>> anagrams = new HashMap<>();

        for(String string : strings){
            char[] chars = string.toCharArray();
            Arrays.sort(chars);
            String sortedString = String.valueOf(chars);

            if (!anagrams.containsKey(sortedString))
                anagrams.put(sortedString, new ArrayList<>());
            anagrams.get(sortedString).add(string);
        }
        return new ArrayList<>(anagrams.values());
    }

    // Inefficient Approach
    private List<Integer> findAnagrams_inefficient(String s, String p){
        List<Integer> startIndices = new ArrayList<>();
        char[] chars = p.toCharArray();
        Arrays.sort(chars);
        String sortedP = String.valueOf(chars);
        int j = p.length();
        for(int i = 0; i + j <= s.length(); ++i){
            String sub = s.substring(i, i+j);
            char[] chars1 = sub.toCharArray();
            Arrays.sort(chars1);
            String sortedSub = String.valueOf(chars1);
            if(sortedSub.equals(sortedP))
                startIndices.add(i);
        }
        return startIndices;
    }

    // Efficient Approach

    // Store frequencies of each character in p
    // Traverse s from 0 and check if current character is in the the map
    // Maintain the count of remaining characters required for the substring to be an anagram of p
        // If current character is in map, decrement the counter

    // Right pointer of window increases the frequency of every character.
    // Left pointer decreases it when window size = p.length has obtained.


    // method to find starting indices of anagrams of string p in string s.
    private List<Integer> findAnagrams_efficient(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0)
            return list;

        HashMap<Character,Integer> map = new HashMap<>();
        for(char c : s.toCharArray())
            map.put(c,0);

        for(char c : p.toCharArray()) {
            if(map.containsKey(c))
                map.put(c,map.get(c)+1);
            else
                return list;
        }

        //two points, initialize count to p's length
        int left = 0, right = 0, count = p.length();

        while (right < s.length()) {
            //move right every time, if the character exists in p's hash, decrease the count
            //current hash value >= 1 means the character is existing in p
            char rightChar = s.charAt(right);
            if (map.get(rightChar) >= 1) {
                count--;
            }
            map.put(rightChar, map.get(rightChar) - 1);
            right++;

            //when the count is down to 0, means we found the right anagram
            //then add window's left to result list
            if (count == 0) {
                list.add(left);
            }
            //if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
            //++ to reset the hash because we kicked out the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
            if (right - left == p.length()) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) + 1);

                if (map.get(leftChar) > 0) {
                    count++;
                }
                left++;
            }
        }
        return list;
    }

    public static void main(String[] args)
    {
        Anagrams anagramFinder = new Anagrams();
        String input1 = "KakHonkayer";
        String input2 = "HoneyKakkar";
        long startTime = System.nanoTime();
        boolean result = anagramFinder.isAnagram(input1, input2);
        long timeTaken = System.nanoTime() - startTime;
        if(result)
            System.out.println("Both strings are anagrams to each other.");
        else
            System.out.println("Both strings are not anagrams to each other.");
        System.out.println("\nThe algorithm took " + timeTaken + " nanoseconds.");


        String[] strings = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<String> stringList = new ArrayList<>(strings.length);
        Collections.addAll(stringList, strings);
        List<List<String>> groupedAnagrams = anagramFinder.groupAnagramsTogether(stringList);
        System.out.println(groupedAnagrams);

        input1 = "cdabdaebabaddddcddab";
        input2 = "abc";
        List<Integer> indices = anagramFinder.findAnagrams_efficient(input1, input2);
        System.out.println(indices);
    }
}
