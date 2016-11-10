package DynamicProgramming;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/6/2016
 */

/*
A child is running up a staircase with n steps, and can hop either 1 step, 2 steps, or
3 steps at a time. Implement a method to count how many possible ways the child
can run up the stairs.
 */

public class ClimbStairs {

    // Method to solve the problem using recursive approach
    public int getTotalWays(int n){
        if (n < 0)
            return 0;

        if (n==0)
            return 1;

        return getTotalWays(n - 1) + getTotalWays(n - 2) + getTotalWays(n - 3);
    }

    // Method to solve the problem using dynamic programming approach
    public int countWaysDP(int n, int[] map) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (map[n] != 0) {
            return map[n];
        } else {
            map[n] = countWaysDP(n - 1, map) + countWaysDP(n - 2, map) + countWaysDP(n - 3, map);
            // Total ways are sum of ways to
                // climb remaining n-1 steps when first step is 1.
                // climb remaining n-2 steps when first step is 2.
                // climb remaining n-3 steps when first step is 3.
            return map[n];
        }
    }

    public static void main(String[] args) {
        ClimbStairs stairsClimber = new ClimbStairs();
        int result = stairsClimber.getTotalWays(10);
        System.out.println(result);

        result = stairsClimber.countWaysDP(10, new int[11]);
        System.out.println(result);
    }

}
