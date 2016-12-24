package Mathematics;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 10/27/2016
 */

// The idea is to get the reverse of second half and compare it with the first half.

public class PalindromeNumber {

    boolean isAPalindrome(int input){
        if ((input!=0 && input%10==0))
            return false;

        input = Math.abs(input);
        int reverseNumber = 0;
        while (input > reverseNumber){
            reverseNumber = reverseNumber*10;     // Multiplying with 10 to accommodate a new digit
            reverseNumber = reverseNumber + input%10;   // Getting the last digit of the number
            input = input/10;
        }
        return reverseNumber == input || // for even length palindromic number
                reverseNumber/10 == input;  // for odd length palindromic number
    }

    public static void main(String[] args) {
        PalindromeNumber palindromeChecker = new PalindromeNumber();
        int input = 543212345;
        boolean result = palindromeChecker.isAPalindrome(input);
        System.out.println(input + " is" + (result ? "" : " not") + " a palindrome.");

        input = 54322345;
        result = palindromeChecker.isAPalindrome(input);
        System.out.println(input + " is" + (result ? "" : " not") + " a palindrome.");

        input = 54321;
        result = palindromeChecker.isAPalindrome(input);
        System.out.println(input + " is" + (result ? "" : " not") + " a palindrome.");

        input = 43212345;
        result = palindromeChecker.isAPalindrome(input);
        System.out.println(input + " is" + (result ? "" : " not") + " a palindrome.");

        input = 10001000;
        result = palindromeChecker.isAPalindrome(input);
        System.out.println(input + " is" + (result ? "" : " not") + " a palindrome.");

        input = 100010001;
        result = palindromeChecker.isAPalindrome(input);
        System.out.println(input + " is" + (result ? "" : " not") + " a palindrome.");

        input = -100010001;
        result = palindromeChecker.isAPalindrome(input);
        System.out.println(input + " is" + (result ? "" : " not") + " a palindrome.");

        input = 0;
        result = palindromeChecker.isAPalindrome(input);
        System.out.println(input + " is" + (result ? "" : " not") + " a palindrome.");

        input = 9;
        result = palindromeChecker.isAPalindrome(input);
        System.out.println(input + " is" + (result ? "" : " not") + " a palindrome.");

        input = -14051;
        result = palindromeChecker.isAPalindrome(input);
        System.out.println(input + " is" + (result ? "" : " not") + " a palindrome.");
    }
}
