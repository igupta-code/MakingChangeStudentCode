import java.util.Arrays;

/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author Isha Gupta
 */

// Memoization Approach:
public class MakingChange{
    public static long[][] table;

    public static long countWays(int target, int[] coins){
        table = new long[coins.length][target+1];
        Arrays.sort(coins);

        return Memoization(target, coins.length-1, coins);
    }

    public static long Memoization(int target, int index, int[] coins){
        // Base: if target is a negative number, you can't make target with given coins
        if(target < 0)
            return 0;
        // Base: if target is zero, you've successfully made the number
        if(target == 0)
            return 1;
        // Base: with one coin left, use % to see if you can make target or not
        if(index == 0){
            if(target % coins[0] == 0)
                return 1;
            else
                return 0;
        }

        // If we can lookup using the table do so
        if(table[index][target] != 0){
            return table[index][target];
        }
        // Otherwise, calculate how many ways to make the target and update the table for future uses
        else {
            // Return the sum of accepting or rejecting the coin you are currently on
            long sum = Memoization(target - coins[index], index, coins) + Memoization(target, index - 1, coins);
            table[index][target] = sum;
            return sum;
        }
    }
}

// Tabulation Approach:
//public class MakingChange {
//    public static long[][] tab;
//    public static long countWays(int target, int[] coins) {
//        tab = new long[coins.length][target+1];
//        Arrays.sort(coins);
//
//        // Set the "zero row" to 1 --> if you're at zero, you have successfully made the target num
//        for(int i = 0; i < coins.length; i++){
//            tab[i][0] = 1;
//        }
//
//        // Go through the 2D array
//        for(int i = 0; i < coins.length; i++){
//            for(int j = 1; j < target+1; j++){
//                // Check the case where you include the coin
//                if(j - coins[i] >= 0) {
//                    tab[i][j] += tab[i][j - coins[i]];
//                }
//
//                // Check the case where you exclude the coin
//                if(i - 1 >= 0){
//                    tab[i][j] += tab[i-1][j];
//                }
//            }
//        }
//
//        // Return the last value of 2D array: represents how many ways to make the final target number with all coins
//        return tab[coins.length-1][target];
//    }
//}
