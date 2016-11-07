package Algorithms;

import java.util.ArrayList;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 10/19/2016
 */

/*
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction
(ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 */

// The idea is to calculate profit at each step and compare with the max profit earned so far
    // Maintain a variable to store the minimum stock price SO FAR during the traversal

public class MaximumProfit {

    private int getMaxProfit (ArrayList<Integer> prices){
        if(prices.size() == 0)
            return 0;

        int maxProfit = 0;
        int currProfit;
        int lastBoughtAt = prices.get(0);
        for(int i=1; i<prices.size(); ++i){
            currProfit = prices.get(i) - lastBoughtAt;
            maxProfit = Math.max(currProfit, maxProfit);
            lastBoughtAt = Math.min(lastBoughtAt, prices.get(i));
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        MaximumProfit maximumProfit = new MaximumProfit();
        ArrayList<Integer> prices = new ArrayList<>();
        prices.add(5);
        prices.add(6);
        prices.add(2);
        prices.add(4);
        prices.add(8);
        prices.add(9);
        prices.add(5);
        prices.add(1);
        prices.add(5);
        int maxProfit = maximumProfit.getMaxProfit(prices);
        System.out.println(maxProfit);
    }
}
