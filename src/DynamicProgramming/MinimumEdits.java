package DynamicProgramming;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 10/31/2016
 */

/*Given two strings str1 and str2 and below operations that can performed on str1.
Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.
        Insert
        Remove
        Replace
       All of the above operations are of equal cost.*/

public class MinimumEdits {

    int getMinEdits_recursive(String input, String output, int r1, int r2){
        if (r1 == 0)
            return r2;

        if (r2 == 0)
            return r1;

        if (input.charAt(r1) == output.charAt(r2))
            return getMinEdits_recursive(input, output, r1 - 1, r2 - 1);

        // insert, remove, replace
        return 1 + Math.min(getMinEdits_recursive(input, output, r1, r2 - 1), Math.min(
                getMinEdits_recursive(input, output, r1 - 1, r2),
                getMinEdits_recursive(input, output, r1 - 1, r2 - 1)));
    }

    int getMinEdits_tabulation(String input, String output, int inpLength, int outLength){
        if (inpLength == 0)
            return outLength;

        if (outLength == 0)
            return inpLength;

        int[][] minEdits = new int[inpLength + 1][outLength + 1];

        for(int i = 0; i<= inpLength; ++i){
            for(int j = 0; j<= outLength; ++j) {

                if (i == 0)
                    minEdits[i][j] = j;
                else if (j == 0)
                    minEdits[i][j] = i;
                else if (input.charAt(i - 1) == output.charAt(j - 1))
                    minEdits[i][j] = minEdits[i - 1][j - 1];
                else
                    minEdits[i][j] = 1 + Math.min(minEdits[i][j - 1], Math.min(
                            minEdits[i - 1][j], minEdits[i - 1][j - 1]));
            }
        }

        return minEdits[inpLength][outLength];
    }

    public static void main(String[] args) {
        String input = "honeykakkar";
        String output = "kakkarhoney";
        int inpLength = input.length();
        int outLength = output.length();
        MinimumEdits editFinder = new MinimumEdits();
        int minEdits = editFinder.getMinEdits_recursive(input, output, inpLength - 1, outLength - 1);
        System.out.println(minEdits);
        minEdits = editFinder.getMinEdits_tabulation(input, output, inpLength, outLength);
        System.out.println(minEdits);
    }
}
