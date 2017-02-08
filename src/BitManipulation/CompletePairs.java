package BitManipulation;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/6/2016
 */

/*
Credits : GeeksforGeeks.org
Two strings are said to be complete if on concatenation, they contain all the 26 English alphabets.
 For example, “abcdefghi” and “jklmnopqrstuvwxyz” are complete as they together have all characters from ‘a’ to ‘z’.
We are given two sets of sizes n and m respectively and we need to find
the number of pairs that are complete on concatenating each String from set 1 to each String from set 2.
 Input : set1[] = {"abcdefgh", "lmnopqrst", "abc"}
        set2[] = {"ijklmnopqrstuvwxyz",
                 "abcdefghijklmnopqrstuvwxyz",
                 "defghijklmnopqrstuvwxyz"}
Output : 7
The total complete pairs that are forming are:
"abcdefghijklmnopqrstuvwxyz"
"abcdefghabcdefghijklmnopqrstuvwxyz"
"abcdefghdefghijklmnopqrstuvwxyz"
"geeksforgeeksabcdefghijklmnopqrstuvwxyz"
"lmnopqrstabcdefghijklmnopqrstuvwxyz"
"abcabcdefghijklmnopqrstuvwxyz"
"abcdefghijklmnopqrstuvwxyz"
*/

public class CompletePairs {

    private List<List<String>> countCompletePairs(String[] stringList1, String[] stringList2) {
        int size1 = stringList1.length;
        int size2 = stringList2.length;

        int result = 0;
        int[] freqInts1 = new int[size1];
        int[] freqInts2 = new int[size2];

        // Process all strings in stringList1[]
        for (int i = 0; i < size1; i++) {
            // initializing all bits to 0
            freqInts1[i] = 0;
            for (int j = 0; j < stringList1[i].length(); j++) {
                // Setting the ascii code of char s[i][j]
                // to 1 in the compressed integer.
                freqInts1[i] = freqInts1[i] | (1 << (stringList1[i].charAt(j) - 'a'));
            }
        }

        // Process all strings in set2[]
        for (int i = 0; i < size2; i++) {
            // initializing all bits to 0
            freqInts2[i] = 0;
            for (int j = 0; j < stringList2[i].length(); j++) {
                // setting the ascii code of char s[i][j]
                // to 1 in the compressed integer.
                freqInts2[i] = freqInts2[i] | (1 << (stringList2[i].charAt(j) - 'a'));
            }
        }

        // assigning a variable whose all 26 (0..25)
        // bits are set to 1
        long complete = (1 << 26) - 1;

        // Now consider every pair of integer in freqInts1[]
        // and freqInts2[] and check if the pair is complete.

        List<List<String>> pairs = new ArrayList<>();
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                // if all bits are set, the strings are
                // complete!
                if ((freqInts1[i] | freqInts2[j]) == complete) {
                    List<String> newPair = new ArrayList<>();
                    newPair.add(stringList1[i]);
                    newPair.add(stringList2[j]);
                    pairs.add(newPair);
                }
            }
        }
        return pairs;
    }
    
    public static void main(String[] args) {
        String stringList1[] = {"abcdefgh", "lmnopqrst", "abc"};
        String stringList2[] = {"ijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz", "defghijklmnopqrstuvwxyz"};
        CompletePairs completePairs = new CompletePairs();
        List<List<String>> pairs = completePairs.countCompletePairs(stringList1, stringList2);
        System.out.println(pairs);
    }
}
