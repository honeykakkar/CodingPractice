package DynamicProgramming;

/*
 * Author: Honey Kakkar
 * Created on: January 04, 2017
 * Package: DynamicProgramming
 * Project: Coding Practice in JAVA
 */

/*
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed, the only constraint stopping you from robbing
each of them is that adjacent houses have security system connected and
it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house,
determine the maximum amount of money you can rob tonight without alerting the police.
 */

class HouseRobber {

    private int maxRobbery(int[] houses) {
        int[][] money = new int[houses.length + 1][2];

        for (int i = 1; i <= houses.length; ++i) {
            money[i][0] = Math.max(money[i - 1][0], money[i - 1][1]);
            money[i][1] = houses[i - 1] + money[i - 1][0];
        }

        return Math.max(money[houses.length][0], money[houses.length][1]);
    }

    private int maxRobbery1(int[] nums) {
        int ifRobbedPrevious = 0;    // max money can get if rob current house
        int ifDidntRobPrevious = 0; // max money can get if not rob current house

        // We go through all the values, we maintain two counts, 1) if we rob this cell, 2) if we didn't rob this cell
        for (int num : nums) {
            // If we rob current cell, previous cell shouldn't be robbed. So, add the current value to previous one.
            int currRobbed = ifDidntRobPrevious + num;

            // If we don't rob current cell, then the count should be max of the previous cell robbed and not robbed
            int currNotRobbed = Math.max(ifDidntRobPrevious, ifRobbedPrevious);

            // Update values for the next round
            ifDidntRobPrevious = currNotRobbed;
            ifRobbedPrevious = currRobbed;
        }
        return Math.max(ifRobbedPrevious, ifDidntRobPrevious);
    }
}
