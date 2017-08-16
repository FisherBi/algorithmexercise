package algorithms.c4dynamicprogramming.Fibonacci;

/**
 * Created by fisbii on 17-8-15.
 */
public class Fibonacci {

    /**
     * get N fibonacci number
     * O(2^n)
     * @param n
     * @return
     */
    public int f1(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        return f1(n-1) + f1(n-2);
    }

    /**
     * O(n)
     * @param n
     * @return
     */
    public int f2(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        int res = 1;
        int pre = 1;
        int tmp = 0;
        for(int i = 3; i <= n; i++){
            tmp = res;
            res = pre + res;
            pre = tmp;
        }
        return res;
    }

    /**
     * O(logN)
     * @param n
     * @return
     */
    public static int f3(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        int [][] base = {{1,1},{1,0}};
        int [][] res = matrixPower(base, n-2);
        return res[0][0] + res[1][0];
    }

    private static int[][] matrixPower(int[][] m, int p) {
        int res[][] = new int[m.length][m[0].length];
        for(int i = 0; i < res.length; i++){
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for(; p != 0; p >>= 1){
            if((p & 1) != 0){
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
        }
        return res;
    }

    private static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for(int i = 0; i < m1.length; i++){
            for(int j = 0; j < m2[0].length; j++){
                for(int k = 0; k < m2.length; k++){
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static void main(String args[]){
        System.out.println(f3(7));
    }
}
