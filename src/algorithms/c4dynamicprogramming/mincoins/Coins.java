package algorithms.c4dynamicprogramming.mincoins;

/**
 * Created by fisbii on 17-8-25.
 */
public class Coins {
    public static int minCoins1(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return -1;
        }
        int[][] dp = new int[arr.length][aim+1];
        int max = Integer.MAX_VALUE;
        int n = arr.length;
        for(int j = 1; j < aim+1; j++){
            dp[0][j] = max;
            if(j - arr[0] >= 0 && dp[0][j-arr[0]] != max){
                dp[0][j] = dp[0][j-arr[0]] + 1;
            }
        }
        int left = 0;
        for(int i = 1; i < n; i++){
            for(int j = 1; j < aim+1; j++){
                left = max;
                if(j - arr[i] >= 0 && dp[i][j-arr[i]] != max){
                    left = dp[i][j-arr[i]] + 1;
                }
                dp[i][j] = Math.min(dp[i-1][j], left);
            }
        }
        return dp[n-1][aim] != max ? dp[n-1][aim] : -1;
    }

    public static void main(String args[]){
        int[] arr = {5,2,3};
        int aim = 1;
        System.out.println(minCoins2(arr, aim));
    }

    public static int minCoins2(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return -1;
        }
        int n = arr.length;
        int dp[] = new int[aim+1];
        int max = Integer.MAX_VALUE;
        for(int j = 1; j <= aim; j++){
            dp[j] = max;
            if(j - arr[0] >= 0 && dp[j-arr[0]] != max){
                dp[j] = dp[j-arr[0]] + 1;
            }
        }
        int left = 0;
        for(int i = 1; i < n; i++){
            for(int j = 1; j <= aim; j++){
                left = max;
                if(j - arr[i] >= 0 && dp[j-arr[i]] != max){
                    left = dp[j-arr[i]] + 1;
                }
                dp[j] = Math.min(dp[j], left);
            }
        }
        return dp[aim] != max ? dp[aim] : -1;
    }
}
