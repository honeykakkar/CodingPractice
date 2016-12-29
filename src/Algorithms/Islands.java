package Algorithms;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 12/1/2016
 */

/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

Example 1:
11110
11010
11000
00000
Answer: 1

Example 2:
11000
11000
00100
00011
Answer: 3
 */
public class Islands {

    // Method to count the number of separate islands in the given map.
    int countIslands(int[][] map) {
        int islands = 0, count = 0;
        for (int row = 0; row < map.length; ++row) {
            for (int col = 0; col < map[0].length; ++col) {
                if (map[row][col] == 1) {
                    System.out.println("Cells in island "+ (islands + 1) +": " + exploreArea(map, row, col));
                    ++islands;
                }
            }
        }
        return islands;
    }

    // Method to traverse the cells in the map. It also keeps the count of cells in the current island.
    int exploreArea(int[][] map, int row, int col) {
        if (row < 0 || row >= map.length || col < 0 || col >= map[0].length)
            return 0;

        if (map[row][col] == 0)
            return 0;

        int count = 1;
        map[row][col] = 0;  // Disappear this cell to avoid infinite loop
        count += exploreArea(map, row + 1, col);    // Check the cell below the current cell.
        count += exploreArea(map, row - 1, col);    // Check the cell above the current cell.
        count += exploreArea(map, row, col + 1);    // Check the cell to the right of the current cell.
        count += exploreArea(map, row, col - 1);    // Check the cell to the left of the current cell.
        return count;
    }

    // Testing the solution of the problem.
    public static void main(String[] args) {
        Islands counter = new Islands();
        int[][] map = {{1,0,0,0,0}, {1,1,0,0,0}, {0,0,1,0,0},{0,0,0,1,1}};
        int totalIslands = counter.countIslands(map);
        System.out.println("Total islands: " + totalIslands);
        System.out.println();

        map = new int[][]{{0, 1, 0, 1, 0},{0, 0, 1, 1, 1},{1, 0, 0, 1, 0},{0, 1, 1, 0, 0},{1, 0, 1, 0, 1}};
        totalIslands = counter.countIslands(map);
        System.out.println("Total islands: " + totalIslands);
    }
}