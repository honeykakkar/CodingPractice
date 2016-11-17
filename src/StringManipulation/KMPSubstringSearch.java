package StringManipulation;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/16/2016
 */
public class KMPSubstringSearch {

    void KMPSearch(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0;  // index for pat[]

        // Pre-process the pattern (calculate lps[]
        // array)
        computeLPSArray(pat, M, lps);

        int i = 0;  // index for txt[]
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Found pattern " +
                        "at index " + (i - j));
                j = lps[j - 1];
            }

            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
    }

    void computeLPSArray(String pat, int M, int lps[]) {
        // length of the previous longest prefix suffix
        int index = 0;
        int i = 1;
        lps[0] = 0;  // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(index)) {
                lps[i] = index + 1;
                index++;
                i++;
            } else  // (pat[i] != pat[index])
            {
                if (index != 0) {
                    index = lps[index - 1];
                    // Also, note that we do not increment
                    // i here
                } else  // if (index == 0)
                {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }
}
