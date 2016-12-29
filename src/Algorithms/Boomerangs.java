package Algorithms;

/*
 * Author: Honey Kakkar
 * Created on: December 18, 2016
 * Package: Algorithms
 * Project: Coding Practice in JAVA
 */

/*
Given n points in the plane that are all pairwise distinct,
a "boomerang" is a tuple of points (i, j, k) such that the distance
between i and j equals the distance between i and k (the order of the tuple matters).

Find the number of boomerangs.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Boomerangs {

    // Defining a class to represent a point in the plane
    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        long getDistanceFrom(Point dest){
            int diffX = this.x - dest.x;
            int diffY = this.y - dest.y;

            return diffX*diffX + diffY*diffY;
        }
    }

    // Method to get the count of boomerangs
    private int getBoomerangCount(List<Point> points){
        HashMap<Long, Integer> distances = new HashMap<>();
        for(int i=0; i<points.size(); ++i){
            Point source = points.get(i);
            for (int j=i + 1; j<points.size(); ++j){
                Point dest = points.get(j);
                long distance = source.getDistanceFrom(dest);
                distances.put(distance, distances.getOrDefault(distance, 0) + 1);
            }
        }

        int result = 0;
        // A tuple of points represent a boomerang
        // when 2 points in the tuple are equidistant to the third one.

        // So for n equidistant point, there are n * (n-1) combinations as the third one is fixed.
        for(int value : distances.values())
            result += value * (value - 1);
        return result;
    }

    // Testing the solution of the problem.
    public static void main(String[] args) {
        Boomerangs boomerangs = new Boomerangs();
        List<Point> points = new ArrayList<>();
        points.add(new Point(0,0));
        points.add(new Point(1,0));
        points.add(new Point(2,0));

        int boomerangCount = boomerangs.getBoomerangCount(points);
        System.out.println(boomerangCount);
    }
}