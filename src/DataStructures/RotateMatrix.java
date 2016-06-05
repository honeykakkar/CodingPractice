package DataStructures;

import java.util.Scanner;

/**
 * Created by honey on 6/4/2016.
 */

// Rotate a matrix by 90 degrees clockwise and anti-clockwise

public class RotateMatrix {

    private void displayMatrix(int[][] matrix, int N)
    {
        for(int i=0; i< N; ++i)
        {
            for(int j=0; j<N; ++j)
                System.out.print(matrix[i][j] + "\t");
            System.out.println();
        }
    }

    private void RotateClockwise(int[][] matrix, int N)
    {
        for(int i=0; i<N/2; ++i)
        {
            for(int j=i; j<N-i-1; ++j)
            {
                int top = matrix[i][j];
                matrix[i][j] = matrix[N-1-j][i];
                matrix[N-1-j][i] = matrix[N-1-i][N-1-j];
                matrix[N-1-i][N-1-j] = matrix[j][N-1-i];
                matrix[j][N-1-i] = top;
            }
        }
    }

    private void RotateAntiClockwise(int[][] matrix, int N)
    {
        for(int i=0; i<N/2; ++i)
        {
            for(int j=i; j<N-i-1; ++j)
            {
                int top = matrix[i][N-1-j];
                matrix[i][N-1-j] = matrix[N-1-j][N-1-i];
                matrix[N-1-j][N-1-i] = matrix[N -1-i][j];
                matrix[N -1-i][j] = matrix[j][i];
                matrix[j][i] = top;
            }
        }
    }

    public static void main(String[] args) {
        RotateMatrix currentObject = new RotateMatrix();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the dimension of square matrix:");
        int N = scanner.nextInt();
        int[][] matrix = new int[N][N];
        scanner.nextLine(); int E = 1;
        for(int i=0; i< N; ++i)
        {
            for(int j=0; j<N; ++j)
            {
                System.out.println("Enter element " + E + ":");
                matrix[i][j] = scanner.nextInt();
                scanner.nextLine();
                ++E;
            }
        }
        System.out.println("Matrix before the rotation:");
        currentObject.displayMatrix(matrix, N);
        long startTime = System.nanoTime();
        currentObject.RotateClockwise(matrix,N);
        long timeTaken = System.nanoTime() - startTime;
        System.out.println("Matrix after rotating it clockwise:");
        currentObject.displayMatrix(matrix, N);
        System.out.println("\nClockwise rotation of current matrix took " + timeTaken + " nanoseconds.");
        startTime = System.nanoTime();
        currentObject.RotateAntiClockwise(matrix,N);
        timeTaken = System.nanoTime() - startTime;
        System.out.println("Matrix after rotating it anti-clockwise:");
        currentObject.displayMatrix(matrix, N);
        System.out.println("\nAnti-clockwise rotation of current matrix took " + timeTaken + " nanoseconds.");
    }
}
