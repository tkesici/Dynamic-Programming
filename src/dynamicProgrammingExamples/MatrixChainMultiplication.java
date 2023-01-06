package dynamicProgrammingExamples;

import java.util.Arrays;

public class MatrixChainMultiplication {
    public static void main(String[] args) throws Exception {
        int[][] A = {{1,1,1},
                    {2,2,2}}; // 2x3
        int[][] B = {{3,3,3,3},
                    {1,1,1,1},
                    {1,1,1,1}}; // 3x4
        System.out.println(Arrays.deepToString(matrixMultiply(A,B)));
    }

    static int[][] matrixMultiply(int[][] A, int[][] B) throws Exception{
        int rowsA = A.length;
        int rowsB = B.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        int[][] C = new int[rowsA][colsB];

        if(colsA!=rowsB){
            throw new IllegalAccessException("Incompatible Dimensions");
        }
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                C[i][j] = 0;
                for (int k = 0; k < colsA; k++) {
                    C[i][j] = C[i][j] + (A[i][k]*B[k][j]);
                }
            }
        }
        return C;
    }
}