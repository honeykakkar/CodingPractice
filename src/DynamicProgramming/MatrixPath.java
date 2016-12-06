package DynamicProgramming;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 10/31/2016
 */
public class MatrixPath {

    int [][] costMatrix;
    int destX = 0, destY = 0;

    MatrixPath(int[][] matrix, int x, int y){
        costMatrix = matrix;
        destX = x;
        destY = y;
    }

    MatrixPath(int[][] matrix){
        costMatrix = matrix;
    }

    public void setDestination(int x, int y){
        destX = x;
        destY = y;
    }

    // naive recursive method to get the minimum cost of traversal from (destX,destY) to (0,0)
    public int getMinCostPath (int row, int col){
        if(row < 0 || col < 0)
            return Integer.MAX_VALUE;
        else
        if(row == 0 && col == 0)
            return costMatrix[row][col];

        return costMatrix[row][col] + Math.min(getMinCostPath(row - 1, col), Math.min(
                getMinCostPath(row, col - 1), getMinCostPath(row - 1, col - 1)));
    }

    public int getMinCostPath (){
        int[][] totalCosts = new int[destX + 1][destY + 1];
        int i, j;

        totalCosts[0][0] = costMatrix[0][0];

        /* Initialize first column of total cost array */
        for (i = 1; i <= destX; i++)
            totalCosts[i][0] = totalCosts[i-1][0] + costMatrix[i][0];

        /* Initialize first row of total cost array */
        for (j = 1; j <= destY; j++)
            totalCosts[0][j] = totalCosts[0][j-1] + costMatrix[0][j];

        /* Construct rest of the total cost array */
        for (i = 1; i <= destX; i++)
            for (j = 1; j <= destY; j++)
                totalCosts[i][j] = costMatrix[i][j] + Math.min(totalCosts[i-1][j-1], Math.min(
                                                            totalCosts[i-1][j],
                                                            totalCosts[i][j-1]));

        return totalCosts[destX][destY];
    }
}
