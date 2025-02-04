import java.util.Arrays;

/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author Isha Gupta
 */

public class MakingChange {
    public static long[][] tab;
    public static long countWays(int target, int[] coins) {
        tab = new long[coins.length][target+1];
        Arrays.sort(coins);

        // Set the "zero row" to 1 --> if you're at zero, you have successfully made the target num
        for(int i = 0; i < coins.length; i++){
            tab[i][0] = 1;
        }

        // Go through the 2D array
        for(int i = 0; i < coins.length; i++){
            for(int j = 1; j < target+1; j++){
                // Check the case where you include the coin
                if(j - coins[i] >= 0) {
                    tab[i][j] += tab[i][j - coins[i]];
                }

                // Check the case where you exclude the coin
                if(i - 1 >= 0){
                    tab[i][j] += tab[i-1][j];
                }
            }
        }

        // Return the last value of 2D array: represents how many ways to make the final target number with all coins
        return tab[coins.length-1][target];
    }
}
