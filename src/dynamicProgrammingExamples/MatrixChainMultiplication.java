package dynamicProgrammingExamples;

import java.util.Arrays;

public class MatrixChainMultiplication {

    static int[][] dp = new int[100][100];

    public static void main(String[] args) throws Exception {


        int[][] A = {{1, 1, 1},
                {2, 2, 2}}; // 2x3
        int[][] B = {{3, 3, 3, 3},
                {1, 1, 1, 1},
                {1, 1, 1, 1}}; // 3x4
        int[][] C = {{3, 3, 3, 3},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}}; // 4x4
        int[][] D = {{3, 3, 3},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}}; // 4x3
        int[][][] m = {A, B, C, D};
        chainOfMatrices(m);
        matrixMultiply(A, B);
        matrixMultiply(B, C);
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        int[] input = {m[0].length, m[1].length, m[2].length,
                m[3].length, m[3][0].length};
        System.out.println("MCM Recursive: " +
                mcmRecursive(input, 1, input.length - 1));
        System.out.println("MCM Memoization: " +
                MatrixChainOrder(input, input.length));
        System.out.println("MCM Tabulation: " +
                mcmTabulation(input));


    }

    static int mcmRecursive(int[] p, int i, int j) {
        if (i == j) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int count = mcmRecursive(p, i, k)
                    + mcmRecursive(p, k + 1, j)
                    + p[i - 1] * p[k] * p[j];
            if (count < min) {
                min = count;
            }
        }
        return min;
    }

    static int mcmMemoization(int[] p, int i, int j) {

        if (i == j) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        dp[i][j] = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            dp[i][j] = Math.min(
                    dp[i][j], mcmMemoization(p, i, k)
                            + mcmMemoization(p, k + 1, j)
                            + p[i - 1] * p[k] * p[j]);
        }

        return dp[i][j];

    }

    static int mcmTabulation(int p[]) {
        int dp[][] = new int[p.length][p.length];
        for (int i = 1; i < p.length; i++) {
            dp[i][i] = 0;
        }
        int j;
        for (int l = 2; l < p.length; l++) {
            for (int i = 1; i < p.length - l + 1; i++) {
                j = i + l - 1;
                if (j == p.length) {
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int q = dp[i][k] + dp[k + 1][j] + (p[i - 1] * p[j] * p[k]);
                    if (q < dp[i][j]) {
                        dp[i][j] = q;
                    }

                }
            }

        }
        return dp[1][p.length - 1];
    }

    static int MatrixChainOrder(int[] p, int n) {
        int i = 1, j = n - 1;
        return mcmMemoization(p, i, j);
    }

    static int[][] matrixMultiply(int[][] A, int[][] B) throws Exception {
        int rowsA = A.length;
        int rowsB = B.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        int[][] C = new int[rowsA][colsB];

        if (colsA != rowsB) {
            System.out.println("Incompatible Dimensions");
            System.exit(0);
        }
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                C[i][j] = 0;
                for (int k = 0; k < colsA; k++) {
                    C[i][j] = C[i][j] + (A[i][k] * B[k][j]);
                }
            }
        }
        System.out.println("Number of Operations: " + (rowsA * colsA * colsB));
        return C;
    }

    static void chainOfMatrices(int[][][] chain) {
        for (int i = 0; i < chain.length; i++) {
            System.out.print("Matrix " + (i + 1) + ": " + Arrays.deepToString(chain[i]) + " ");
            getDimension(chain[i]);
        }
    }

    static void getDimension(int[][] matrix) {
        int rowsA = matrix.length;
        int colsA = matrix[0].length;
        System.out.println("(" + rowsA + "x" + colsA + ")");
    }
}