package StringManipulation;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 10/27/2016
 */

/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows (3 in this case) like this:
P   A   H   N
A P L S I I G
Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */
public class ZigZagPattern {

    String getZigzagPattern(String input, int rows){
        if(rows == 1 || input.length() == 0)
            return input;

        StringBuilder result = new StringBuilder("");

        // To store each row of zigzag pattern separately
        StringBuilder[] matrix = new StringBuilder[rows];

        //Initializing the matrix
        for(int i=0; i<rows; ++i)
            matrix[i] = new StringBuilder("");

        // Traversing the input string from index 0 till its end
        for(int i=0; i<input.length();){

            // Moving down in zigzag pattern : traversing string and appending characters
            for (int currRow = 0; currRow < rows && i<input.length(); ++currRow){
                matrix[currRow].append(input.charAt(i));
                ++i;
            }

            // Moving diagonally up in zigzag pattern : traversing string and appending characters
            for (int currRow = rows - 2; currRow >= 1 && i<input.length(); --currRow){
                matrix[currRow].append(input.charAt(i));
                ++i;
            }
        }

        // Merging each row of zigzag pattern into one output string
        for(int i=0; i<rows; ++i)
            result.append(matrix[i]);

        return result.toString();
    }

    public static void main(String[] args) {
        ZigZagPattern patternFinder = new ZigZagPattern();
        String input = "BECOMEAGOODDEVELOPER";
        int rows = 1;
        String result = patternFinder.getZigzagPattern(input, rows);
        System.out.println("Zigzag pattern of " + input + " with "+ rows +" row is: " + result);

        rows = 2;
        result = patternFinder.getZigzagPattern(input, rows);
        System.out.println("Zigzag pattern of " + input + " with "+ rows +" rows is: " + result);

        rows = 4;
        result = patternFinder.getZigzagPattern(input, rows);
        System.out.println("Zigzag pattern of " + input + " with "+ rows +" rows is: " + result);

        rows = 6;
        result = patternFinder.getZigzagPattern(input, rows);
        System.out.println("Zigzag pattern of " + input + " with "+ rows +" rows is: " + result);

        rows = 10;
        result = patternFinder.getZigzagPattern(input, rows);
        System.out.println("Zigzag pattern of " + input + " with "+ rows +" rows is: " + result);
    }
}
