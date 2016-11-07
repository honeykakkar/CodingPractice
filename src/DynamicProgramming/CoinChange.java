package DynamicProgramming;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 10/31/2016
 */
public class CoinChange {

    int[] coinDenoms;
    int totalCoins;

    CoinChange(int[] coins) {
        this.coinDenoms = coins;
        totalCoins = coins.length;
    }

    // Complexity = O(sum * number of coins)
    // Method to get the minimum number of coins to make a given sum
    public int getMinimumCoins(int sum) {

        int[] minimumCoins = new int[sum + 1];
        int[] selectedCoins = new int[sum + 1];
        for(int i=1; i<= sum; ++i)
            minimumCoins[i] = Integer.MAX_VALUE;

        // Coins : 1,3,5,10
        // C(0) = 0;
        // C(1) = Min( C(1 - 1) ) + 1;
        // C(2) = Min( C(2 - 1) ) + 1;
        // C(3) = Min( C(3 - 1), C(3 - 3) ) + 1
        // C(4) = Min( C(4 - 1), C(4 - 3) ) + 1
        // C(5) = Min( C(5 - 1), C(5 - 3), C(5 - 5) ) + 1
        for (int currentSum = 1; currentSum <= sum; ++currentSum) {
            for (int n = 0; n < totalCoins && coinDenoms[n] <= currentSum; ++n) {
                int nthCoin = coinDenoms[n];
                if (minimumCoins[currentSum - nthCoin] + 1 < minimumCoins[currentSum]) {
                    minimumCoins[currentSum] = minimumCoins[currentSum - nthCoin] + 1;
                    selectedCoins[currentSum] = nthCoin;
                }
            }
        }

        System.out.println("Selected coins are: ");
        int temp = sum;
        while(temp > 0){
            System.out.print(selectedCoins[temp] + " ");
            temp = temp - selectedCoins[temp];
        }
        System.out.println();

        return minimumCoins[sum];
    }

    // method to calculate ways to make change for a given sum
    public int getTotalWays(int sum) {

        int[][] ways = new int[totalCoins + 1][sum + 1];

        for (int row = 0; row < ways.length; ++row)
            ways[row][0] = 1;

        for (int col = 0; col < ways[0].length; ++col)
            ways[0][col] = 0;

        for (int coin = 1; coin <= totalCoins; coin++) {
            for (int amount = 1; amount <= sum; amount++) {

                // check if the coin value is less than the amount needed
                if (coinDenoms[coin - 1] <= amount) {
                    // reduce the amount by coin value and
                    // use the sub problem solution (amount-v[coin]) and
                    // add the solution from the top to it
                    ways[coin][amount] = ways[coin - 1][amount]
                            + ways[coin][amount - coinDenoms[coin - 1]];
                } else {
                    // just copy the value from the top
                    ways[coin][amount] = ways[coin - 1][amount];
                }
            }
        }
        return ways[totalCoins][sum];
    }

    public static void main(String[] args) {
        int[] coinDenoms = {1, 2, 5, 10, 20, 50, 100};
        int sumRequired = 1000;
        CoinChange coinChange = new CoinChange(coinDenoms);
        int minCoins = coinChange.getMinimumCoins(sumRequired);
        System.out.println(minCoins);

        int totalWays = coinChange.getTotalWays(200);
        System.out.println(totalWays);
    }
}