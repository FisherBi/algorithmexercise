package algorithms.c4dynamicprogramming.longestincreasingsubsequence;

/**
 * Created by fisbii on 17-8-30.
 */
public class Subsequence {

    public int[] getdp1(int[] arr){
        int[] dp = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        return dp;
    }

    public int[] generateLIS(int[] arr, int[] dp){
        int len = 0;
        int index = 0;
        for(int i = 0; i < dp.length; i++){
            if(len < dp[i]){
                len = dp[i];
                index = i;
            }
        }
        int[] lis = new int[len];
        lis[--len] = arr[index];
        for(int i = index; i >= 0; i--){
            if(arr[i] < arr[index] && dp[i]+1 == dp[index]){
                lis[--len] = arr[i];
                index = i;
            }
        }
        return lis;
    }

    public int[] lis1(int[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        int[] dp = getdp1(arr);
        return generateLIS(arr, dp);
    }

    public int[] getdp2(int[] arr){
        int[] ends = new int[arr.length];
        int right = 0;
        int dp[] = new int[arr.length];
        ends[0] = arr[0];
        dp[0] = 1;
        int l = 0;
        int r = 0;
        int m = 0;
        for(int i = 1; i< arr.length; i++){
            l = 0;
            r = right;
            while (l <= r){
                m = (l + r) / 2;
                if(arr[m] < arr[i]){
                    l = m + 1;
                }else{
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i];
            dp[i] = l + 1;
        }
        return dp;
    }
}
