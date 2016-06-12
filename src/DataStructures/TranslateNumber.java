package DataStructures;

import java.util.Scanner;

/**
 * Created by honey on 6/8/2016.
 */

// Given any integer, print an English phrase that describes the integer (e.g., "One
// Thousand Two Hundred Thirty Four").

public class TranslateNumber {

    private String[] ones = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
    private String[] teens = new String[]{"", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private String[] tens = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] bigs = new String[]{"", "Thousand", "Million", "Billion"};

    // method to convert a number less than 1000
    private String lessThan1000(long input){
        String translation;
        if((input%100)<20 && (input%100)>10)
        {
            translation = teens[(int)input%100 - 10];
            input /= 100;
        }
        else {
            if (input % 100 < 10) {
                translation = ones[(int) input % 100];
                input /= 100;
            } else {
                translation = ones[(int)input%10];
                input /= 10;
                translation = tens[(int)input%10] + " " + translation;
                input /= 10;
            }
        }
        if(input == 0)
            return translation;
        return ones[(int)input%10] + " Hundred " + translation;
    }

    private String translateNumber(long input){
        if(input == 0)
            return "Zero";
        else if (input<0)
            return "Negative " + translateNumber(-1 * input);

        int count = 0;
        String result = "";
        while(input>0){
            if(input%1000 != 0)
                result = lessThan1000(input%1000) + " " + bigs[count] + " " + result;
            input /= 1000;
            count++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Enter the number:");
        Scanner scanner = new Scanner(System.in);
        long input = scanner.nextLong();
        TranslateNumber currentObject = new TranslateNumber();
        long startTime = System.nanoTime();
        String result = currentObject.translateNumber(input);
        long timeTaken = System.nanoTime() - startTime;
        System.out.println("Input number is : " + result);
        System.out.println("\nThe algorithm took " + timeTaken + " nanoseconds.");
    }
}
