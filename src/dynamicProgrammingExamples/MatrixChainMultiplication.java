package dynamicProgrammingExamples;

import java.util.Arrays;

public class MatrixChainMultiplication {
    public static void main(String[] args) throws Exception {

        int[][] A = {{1,1,1},
                    {2,2,2}}; // 2x3
        int[][] B = {{3,3,3,3},
                    {1,1,1,1},
                    {1,1,1,1}}; // 3x4
        int[][] C = {{3,3,3,3},
                {1,1,1,1},
                {1,1,1,1},
                {1,1,1,1}}; // 4x4
        int[][][] matrices = {A,B,C};

        chainOfMatrices(matrices);
        numberOfOperations(B,C);
    }

    static int[][] matrixMultiply(int[][] A, int[][] B) throws Exception{
        int rowsA = A.length;
        int rowsB = B.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        int[][] C = new int[rowsA][colsB];

        if(colsA!=rowsB){
            System.out.println("Incompatible Dimensions");
            System.exit(0);
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
    static void chainOfMatrices(int[][][] chain){
        for (int i = 0; i < chain.length; i++) {
            System.out.print("Matrix " + (i+1) + ": " + Arrays.deepToString(chain[i]) + " ");
            getDimension(chain[i]);
        }
    }

    static void getDimension(int[][] matrix){
        int rowsA = matrix.length;
        int colsA = matrix[0].length;
        System.out.println("("+rowsA+"x"+colsA+")");
    }
    static void numberOfOperations(int[][] A, int[][] B){
        int rowsA = A.length;
        int rowsB = B.length;
        int colsA = A[0].length;
        int colsB = B[0].length;
        if(colsA!=rowsB){
            System.out.println("Incompatible Dimensions");
            System.exit(0);
        }
        System.out.println("Number of Operations: " + (rowsA*colsA*colsB));
    }
}