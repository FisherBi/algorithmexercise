package algorithms.c4dynamicprogramming.mincoins;

import java.util.Map;

/**
 * Created by fisbii on 17-8-25.
 */
public class Coins {
    /**
     * 二维，取多次
     * @param arr
     * @param aim
     * @return
     */
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

    /**
     * 压缩为一维，取多次
     * @param arr
     * @param aim
     * @return
     */
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

    /**
     * 二维，取一次
     * @param arr
     * @param aim
     * @return
     */
    public static int minCoins3(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return -1;
        }
        int n = arr.length;
        int dp[][] = new int[n][aim+1];
        int max = Integer.MAX_VALUE;
        for(int j = 1; j <= aim; j++){
            dp[0][j] = max;
        }
        if(arr[0] <= aim){
            dp[0][arr[0]] = 1;
        }
        int leftup = 0;
        for(int i = 1; i < n; i++){
            for(int j = 1; j <= aim; j++){
                leftup = max;
                if(j - arr[i] >= 0 && dp[i-1][j-arr[i]] != max){
                    leftup = dp[i-1][j-arr[i]] + 1;
                }
                dp[i][j] = Math.min(leftup, dp[i-1][j]);
            }
        }
        return dp[n-1][aim] != max ? dp[n-1][aim] : -1;
    }

    /**
     * 压缩为一维，取一次
     * @param arr
     * @param aim
     * @return
     */
    public static int minCoins4(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return -1;
        }
        int n = arr.length;
        int dp[] = new int[aim+1];
        int max = Integer.MAX_VALUE;
        for(int j = 1; j <= aim; j++){
            dp[j] = max;
        }
        if(arr[0] <= aim){
            dp[arr[0]] = 1;
        }
        int leftup = 0;
        for(int i = 1; i < n; i++){
            for(int j = 1; j <= aim; j++){
                leftup = max;
                if(j - arr[i] >= 0 && dp[j-arr[i]] != max){
                    leftup = dp[j-arr[i]] + 1;
                }
                dp[j] = Math.min(leftup, dp[j]);
            }
        }
        return dp[aim] != max ? max : -1;
    }

    /**
     * 暴力递归求换钱方法数
     * O(aim^N)
     * @param arr
     * @param aim
     * @return
     */
    public int coin1(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        return process1(arr, 0, aim);
    }

    private int process1(int[] arr, int index, int aim) {
        int res = 0;
        if(index == arr.length){
            res = aim == 0 ? 1 : 0;
        } else{
            for(int i = 0; arr[index] * i <= aim; i++){
                res += process1(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }

    /**
     * 记忆化搜索，使用map记录递归过的情况
     * map[i][j] == 0,表示递归过程p(i,j)没有计算过，
     * map[i][j] == -1,表示递归过程p(i,j)计算过，但是结果为0
     * 否测，map[i][j]的值就是递归过程p(i,j)的返回值a.
     * 复杂度为O(N*aim^2)
     * @param arr
     * @param aim
     * @return
     */
    public int coin2(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int[][] map = new int[arr.length + 1][aim + 1];
        return process2(arr, 0, aim, map);
    }

    private int process2(int[] arr, int index, int aim, int[][] map) {
        int res = 0;
        if(index == arr.length){
            res = aim == 0 ? 1 : 0;
        } else {
            int mapValue = 0;
            for(int i = 0; arr[index] * i <= aim; i++){
                mapValue = map[index+1][aim - arr[index] * i];
                if(mapValue != 0){
                    res += mapValue == -1 ? 0 : mapValue;
                }else{
                    res += process2(arr, index + 1, aim - arr[index] * i, map);
                }

            }
        }
        map[index][aim] = res == 0 ? -1 : res;
        return res;
    }

    /**
     * 动态规划
     * dp[i][j],i表示使用0...i种钱币，组成钱数j的方法总数
     * O(N*aim^2)
     * @param arr
     * @param aim
     * @return
     */
    public int coin3(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int n = arr.length;
        int max = Integer.MAX_VALUE;
        int dp[][] = new int[n][aim+1];
        for(int i = 0; i < n; i++){
            dp[i][0] = 1;
        }
        for(int k = 0; k*arr[0] <= aim; k++){
            dp[0][k*arr[0]] = 1;
        }
        int num = 0;
        for(int i = 1; i < n; i++){
            for(int j = 1; j <= aim; j++){
                num = 0;
                for(int k = 0; k*arr[i] <= j; k++){
                    num += dp[i-1][j-k*arr[i]];
                }
                dp[i][j] = num;
            }
        }
        return dp[n-1][aim];
    }

    /**
     * O(N*aim)
     * @param arr
     * @param aim
     * @return
     */
    public int coin4(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int[][] dp = new int[arr.length][aim+1];
        for(int i = 0; i < arr.length; i++){
            dp[i][0] = 1;
        }
        for(int k = 0; k * arr[0] <= aim; k++){
            dp[0][k*arr[0]] = 1;
        }
        for(int i = 1; i < arr.length; i++){
            for(int j = 1; j <= aim; j++){
                dp[i][j] = dp[i-1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j-arr[i]] : 0;
            }
        }
        return dp[arr.length-1][aim];
    }

    /**
     * 压缩路径
     * @param arr
     * @param aim
     * @return
     */
    public int coin5(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int[] dp = new int[aim+1];
        for(int k = 0; k * arr[0] <= aim; k++){
            dp[k*arr[0]] = 1;
        }
        for(int i = 1; i < arr.length; i++){
            for(int j = 1; j <= aim; j++){
                dp[j] += j - arr[i] >= 0 ? dp[j-arr[i]] : 0;
            }
        }
        return dp[aim];
    }
}
