package DynamicProgramming;

import java.util.Scanner;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 8/11/2016
 */

/*On a positive integer, you can perform any one of the following 3 steps.
 1.)    Subtract 1 from it. ( n = n - 1 )
 2.)    If its divisible by 2, divide by 2. ( if n % 2 == 0 , then n = n / 2  )
 3.)    If its divisible by 3, divide by 3. ( if n % 3 == 0 , then n = n / 3  )

Now the question is, given a positive integer n, find the minimum number of steps that takes n to 1*/

public class MinStepsToOne {

    int[] steps;

    MinStepsToOne(int n){
        steps = new int[n+1];
    }

    // Method to solve the problem using memoization
    int minStepsMemo(int number){
        if (number == 1)
            return 0;

        if (steps[number] != 0)
            return steps[number];

        int minSteps = 1 + minStepsMemo(number - 1);
        if(number % 2 == 0)
            minSteps = Math.min(minSteps, 1 + minStepsMemo(number/2));
        if(number % 3 == 0)
            minSteps = Math.min(minSteps, 1 + minStepsMemo(number/3));

        steps[number] = minSteps;
        return minSteps;
    }

    // Method to solve the problem using greedy approach
    int minStepsGreedy(int number){
        if(number == 1)
            return 0;

        if(number%3 == 0)
            return 1 + minStepsGreedy(number/3);
        if(number%2 == 0)
            return 1 + minStepsGreedy(number/2);
        return 1 + minStepsGreedy(number - 1);
    }

    // Method to solve the problem using dynamic programming approach
    int minStepsDP(int number){
        steps = new int[number + 1];
        for (int i=2; i<=number; ++i){
            steps[i] = 1 + steps[i-1];
            if(i%2 == 0)
                steps[i] = Math.min(steps[i], 1 + steps[i/2]);
            if(i%3 == 0)
                steps[i] = Math.min(steps[i], 1 + steps[i/3]);
        }
        return steps[number];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number to calculate the min steps required to take it to 1 : ");
        int number = sc.nextInt();
        MinStepsToOne currentObj = new MinStepsToOne(number);
        System.out.println("\nMinimum required steps (using greedy approach) are : " + currentObj.minStepsGreedy(number));
        System.out.println("Minimum required steps (using memoization approach) are : " + currentObj.minStepsMemo(number));
        System.out.println("Minimum required steps (using DP approach) are : " + currentObj.minStepsDP(number));
    }
}