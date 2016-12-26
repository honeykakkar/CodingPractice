package Mathematics;

/*
 * Author: Honey Kakkar
 * Created on: December 24, 2016
 * Package: Mathematics
 * Project: Coding Practice in JAVA
 */

// Class which contains methods for basic math operations like square root, power, add etc.

public class MathOperations {

    // Complexity : log(n)
    public static double power(double x, int y) {
        if (y == 0)
            return 1;

        double temp;
        temp = power(x, y / 2);

        if (y % 2 == 0)
            return temp * temp;
        else {
            if (y > 0)
                return x * temp * temp;
            else
                return (temp * temp) / x;
        }
    }

    // Method to calculate the square root of a number using Newton's method.
    public static double squareRoot(int number) {
        double sqrt = number; // An initial guess;

        while (sqrt * sqrt > number){
            sqrt = (sqrt + number/sqrt)/2;
        }
        return sqrt;
    }

    // Method to calculate the square root of a number using divide and conquer.
    public static double squareRoot_DQ(int number) {
        double low = 1; // An initial guess;
        double high = number;
        double mid = 1;
        while (low <= high){
            mid = low + (high - low)/2;
            if (mid * mid == number)
                return mid;
            else if (mid * mid < number)
                low = mid;
            else
                high = mid;
        }
        return mid;
    }

    /*
    If n is even, it can be written as
    n = 2*x
    n^2 = (2*x)^2 = 4*x^2

    If n is odd, it can be written as
    n = 2*x + 1
    n^2 = (2*x + 1)^2 = 4*x^2 + 4*x + 1

    To multiply any  number with a power of 2, shift the bits of the number to the left by n
    where n is exponent of 2.
    For example,
    5 * 8
    101 << 3 = 101000
    */
    public static int square(int n) {
        // Base case
        if (n == 0)
            return 0;

        // Handle negative number
        if (n < 0)
            n = -n;

        // Get floor(n/2) using right shift
        int x = n >> 1;

        // If n is odd
        if ((n & 1) == 1)
            return ((square(x) << 2) + (x << 2) + 1);   // 4*x^2 + 4*x + 1
        else // If n is even
            return (square(x) << 2);    // 4*x^2
    }

    // Sum of two numbers can be calculated using XOR.
    // Example: 117 = 1110101
    //          +19 = 0010011
    //         136 = 10001000

    // While adding bits we know that
    //    0 + 1 = 1
    //    1 + 0 = 1
    //    1 + 1 = 0 with 1 carry
    //    0 + 0 = 0

    // This is exactly what XOR does. To calculate carry, we can use AND.
    //    0 ^ 1 = 1
    //    1 ^ 0 = 1
    //    1 ^ 1 = 0
    //    0 ^ 0 = 0

    // So we can say that a + b = (a ^ b) + (a & b);
    int sumOfTwo(int a, int b) {

        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(squareRoot(164));
        System.out.println(squareRoot_DQ(164));
    }
}