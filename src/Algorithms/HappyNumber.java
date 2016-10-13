package Algorithms;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 10/12/2016
 */

// A happy number is a number defined by the following process:
// Starting with any positive integer, replace the number by the sum of the squares of its digits,
// and repeat the process until the number equals 1 (where it will stay),
// or it loops endlessly in a cycle which does not include 1.
// Those numbers for which this process ends in 1 are happy numbers.

    /*19 is a happy number

            1 square + 9 square = 82
            8 square + 2 square = 68
            6 square + 8 square = 100
            1 square + 0 square + 0 square = 1
     */

public class HappyNumber {

    // method to calculate the sum of squares of digits in a number
    int digitSquareSum(int n) {
        int sum = 0, tmp;
        while (n != 0) {
            tmp = n % 10;
            sum += tmp * tmp;
            n /= 10;
        }
        return sum;
    }

    // Using Floyd-Warshall algorithm to detect a cycle.
    // An unhappy number would have a cycle and would never converge to 1.
    // Using slow and fast pointers to detect the cycle for the numbers can tell us whether a number is happy or not

    boolean isHappy(int n) {

        // initializing slow and fast pointers
        int slow = n, fast = n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
        } while(slow != fast);  // checking whether the sum of squares converge to 1.

        // If slow or fast is 1, it means that it is a happy number.
        return slow == 1;
    }

    public static void main(String[] args) {
        HappyNumber checker = new HappyNumber();
        int n = 19;
        boolean result = checker.isHappy(n);
        System.out.printf((result ? "Yes, %d is " : "No, %d isn't ") + "a happy number.", n);
        System.out.println();

        n = 4;
        result = checker.isHappy(n);
        System.out.printf((result ? "Yes, %d is " : "No, %d isn't ") + "a happy number.", n);
        System.out.println();

        n = 310;
        result = checker.isHappy(n);
        System.out.printf((result ? "Yes, %d is " : "No, %d isn't ") + "a happy number.", n);
        System.out.println();

        n = 18;
        result = checker.isHappy(n);
        System.out.printf((result ? "Yes, %d is " : "No, %d isn't ") + "a happy number.", n);
        System.out.println();

        n = 998;
        result = checker.isHappy(n);
        System.out.printf((result ? "Yes, %d is " : "No, %d isn't ") + "a happy number.", n);
        System.out.println();
    }
}
