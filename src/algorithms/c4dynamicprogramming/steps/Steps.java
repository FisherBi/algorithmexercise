package algorithms.c4dynamicprogramming.steps;

/**
 * Created by fisbii on 17-8-15.
 */
public class Steps {

    public int s1(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return n;
        }
        return s1(n-1) + s1(n-2);
    }

    public int s2(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return n;
        }
        int tmp = 0;
        int pre = 1;
        int res = 2;
        for(int i = 3; i <= n; i++){
            tmp = res;
            res = pre + res;
            pre = tmp;
        }
        return res;
    }

    public int s3(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        int [][] base = {{1,1},{1,0}};
        int [][] res = matrixPower(base, n-2);
        return 2*res[0][0] + res[1][0];
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
}
