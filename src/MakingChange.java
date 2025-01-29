import java.util.Arrays;

/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author Isha Gupta
 */

public class MakingChange {
    public static int[][] tab;
    public static long countWays(int target, int[] coins) {
        tab = new int[coins.length][target];
        Arrays.sort(coins);

        // Set the "zero row" to 0 --> if you're at zero, you have successfully made the target num
        for(int i = 0; i < coins.length; i++){
            tab[i][0] = 1;
        }

        for(int i = 0; i < coins.length; i++){
            for(int j = 1; j < target; j++){
                // Check the case where you include the coin
                if(j - coins[i] >= 0) {
                    tab[i][j] += tab[i][j - coins[i]];
                }

                // Check the case where you exclude the coin
                if(i - 1 >= 0){
                    tab[i][j] += tab[i-1][j];
                }

                // Print to debug
                for(int m = 0; m < coins.length; m++){
                    System.out.println(Arrays.toString(tab[m]));
                }
                System.out.println("----------");
            }
        }

        return tab[coins.length-1][target-1];
    }
}
