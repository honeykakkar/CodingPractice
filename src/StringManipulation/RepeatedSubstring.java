package StringManipulation;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/16/2016
 */

/*
Given a non-empty string check if it can be constructed by taking a substring of it
and appending multiple copies of the substring together.

You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
 */
public class RepeatedSubstring {
    /*
    1. The length of the repeating substring must be a divisor of the length of the input string
    2. Search for all possible divisor of str.length, starting for length/2
    3. If i is a divisor of length, repeat the substring from 0 to i the number of times i is contained in s.length
    4. If the repeated substring is equals to the input str return true
     */
    public boolean repeatedSubstringPattern(String str) {
        int len = str.length();
        for (int i = len / 2; i >= 1; i--) {
            if (len % i == 0) {
                int m = len / i;
                String subS = str.substring(0, i);
                int j;
                for (j = 1; j < m; j++) {
                    if (!subS.equals(str.substring(j * i, i + j * i)))
                        break;
                }
                if (j == m)
                    return true;
            }
        }
        return false;
    }

    // Using KMP Substring Search approach to compute prefix suffix array
    //
    public boolean repeatedSubstringPattern_KMP(String str) {
        //This is the kmp issue
        int[] lps = new int[str.length()];
        KMPSubstringSearch KMPSearch = new KMPSubstringSearch();
        KMPSearch.computeLPSArray(str, str.length(), lps);

        // if the complete string can be constructed with a substring,
        // the longest prefix which is also a suffix can answer this question as
        // (strLength - subPrefix) indicates the length of the substring which is repeated
        // And, it must be a divisor of the length of the input string

        int subPrefix = lps[str.length() - 1];
        int strLength = str.length();
        return (subPrefix > 0 && strLength % (strLength - subPrefix) == 0);
    }

    public static void main(String[] args) {
        RepeatedSubstring repeatedSubstring = new RepeatedSubstring();
        String input = "abcabcabcabcabc";
        boolean result = repeatedSubstring.repeatedSubstringPattern_KMP(input);
        System.out.println(result);
    }
}
