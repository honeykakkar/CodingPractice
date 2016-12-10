package Mathematics;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 12/3/2016
 */

/*
Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.

Algorithm

          numbers with this len
                   ^
                   |
start   len     count   total digits covered
1       1       9       9
10      2       90      180
100     3       900     2700
 */

public class NthDigit {

    public static int findNthDigit(int n) {
        int len = 1;
        long count = 9;
        int start = 1;

        // Find the find the length of the number where the nth digit is from
        while (n > len * count) {
            n -= len * count;
            len += 1;
            count *= 10;
            start *= 10;
        }

        // Find the actual number where the nth digit is from
        start += (n - 1) / len;
        String s = Integer.toString(start);

        // Find the nth digit
        return Character.getNumericValue(s.charAt((n - 1) % len));
    }

    public static void main(String[] args) {
        int n = 100;
        int result = findNthDigit(n);
        System.out.println(result);
    }
}
