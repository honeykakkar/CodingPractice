package DynamicProgramming;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/6/2016
 */
public class GridPath {

    // Total ways to reach from top left to bottom right of matrix of given size
    // If we can only mover right or down, total ways are always equal to
        //  factorial(destX + destY)/factorial(destX)*factorial(destY)

    private int getTotalWays(int destX, int destY) {
        int[][] totalWays = new int[destX][destX];

        // Count of paths to reach any cell in first column is 1
        for (int row = 0; row < destX; row++)
            totalWays[row][0] = 1;

        // Count of paths to reach any cell in first column is 1
        for (int col = 0; col < destY; col++)
            totalWays[0][col] = 1;

        // Using sub problems' solutions
        for (int row=1; row < destX; ++row){
            for (int col=1; col < destY; ++col){
                totalWays[row][col] = totalWays[row - 1][col] + totalWays[row][col - 1];
            }
        }
        return totalWays[destX - 1][destY - 1];
    }

    public static void main(String[] args) {
        GridPath pathSolver = new GridPath();
        System.out.println(pathSolver.getTotalWays(3, 3));
    }
}
