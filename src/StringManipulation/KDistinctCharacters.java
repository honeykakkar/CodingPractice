package StringManipulation;

import java.util.HashMap;

/*
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/5/2016
 */

// Calculate longest Substring with At Most K Distinct Characters in the input

// I am using a sliding window to check whether characters are distinct or not.
    // HashMap is used to maintain the frequencies of character encountered till now...It prevents the searching in the string

    // The idea is to keep count of characters from index 0 to last character in hashmap
    // Maintain a counter of unique elements so far.
    // If counter is greater than K, it means the window needs to be slided.

    // Keep sliding the window until the count is back to K again.
    // Get the length of each window {fast - slow} and compare with max.

public class KDistinctCharacters {

    private int longestSubstringTwoDistinct(String input, int K) {
        HashMap<Character, Integer> map = new HashMap<>();
        int counter = 0, slow = 0, fast = 0, d = 0;
        String result = "";
        while (fast < input.length()) {
            char end = input.charAt(fast);
            if (map.get(end) == null || map.get(end) == 0) {
                map.put(end, 1);
                counter++;
            } else
                map.put(end, map.get(end) + 1);

            ++fast;

            while (counter > K) {
                char start = input.charAt(slow);
                if (map.get(start) == 1)    // decrease counter only when it is this character's last occurrence
                    --counter;
                map.put(start, map.get(start) - 1);
                ++slow;
            }

            if (fast - slow > d) {
                d = fast - slow;
                result = input.substring(slow, fast);
            }
        }
        System.out.println(result);
        return d;
    }

    public static void main(String[] args) {
        KDistinctCharacters sequenceFinder = new KDistinctCharacters();
        int K = 2;
        int result = sequenceFinder.longestSubstringTwoDistinct("ababcdefahijklbaaab", K);
        System.out.println(result);

        result = sequenceFinder.longestSubstringTwoDistinct("bbbbbbbbbb", K);
        System.out.println(result);

        result = sequenceFinder.longestSubstringTwoDistinct("ababcdefahijklab", K);
        System.out.println(result);

        K = 3;
        result = sequenceFinder.longestSubstringTwoDistinct("aabacbebebe", K);
        System.out.println(result);
    }
}