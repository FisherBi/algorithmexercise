package algorithms.c8arrayandmatrix.printmatrix;

/**
 * Created by fisbii on 17-8-17.
 */
public class Matrix {

    public void spiralOrderPrint(int[][] matrix){
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (tR <= dR && tC <= dC){
            printEdge(matrix, tR++, tC ++, dR--, dC--);
        }
    }

    private void printEdge(int[][] m, int tR, int tC, int dR, int dC) {
        if(tR == dR){
            for(int i = tC; i <= dC; i++){
                System.out.println(m[tR][i] + " ");
            }
        }else if(dR == dC){
            for(int i = tR; i <= dR; i++){
                System.out.println(m[i][dR] + " ");
            }
        }else{
            int curC = tC;
            int curR = tR;
            while(curC != dC){
                System.out.println(m[tR][curC] + " ");
                curC++;
            }
            while (curR != dR){
                System.out.println(m[curR][dC] + " ");
                curR++;
            }
            while (curC != tC){
                System.out.println(m[dR][curC] + " ");
                curC--;
            }
            while (curR != tR){
                System.out.println(m[curR][dC] + " ");
                curR--;
            }
        }

    }

    public void rotate(int[][] matrix){
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while(tR < dR){
            rotateEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    private void rotateEdge(int[][] matrix, int tR, int tC, int dR, int dC) {
        int times = dC - tC;
        int tmp = 0;
        for(int i = 0; i != times; i++){
            tmp = matrix[tR][tC+i];
            matrix[tR][tC+i] = matrix[dR-i][tC];
            matrix[dR-i][tC] = matrix[dR][dC-i];
            matrix[dR][dC-i] = matrix[tR+i][dC];
            matrix[tR+i][dC] = tmp;
        }
    }
}
