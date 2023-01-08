package dynamicProgrammingExamples;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String X = "lcstest";
        String Y = "lcs";
        int m = X.length();
        int n = Y.length();

        System.out.println(lcsRecursive(X, Y, m, n));
        lcsDynamic(X, Y, m, n);
    }

    static String lcsRecursive(String X, String Y, int m, int n) {
        if (m == 0 || n == 0) {
            return "";
        }
        if (X.charAt(m - 1) == Y.charAt(n - 1)) {
            return lcsRecursive(X, Y, m - 1, n - 1) + X.charAt(m - 1);
        } else {
            if (lcsRecursive(X, Y, m, n - 1).length() >
                    lcsRecursive(X, Y, m - 1, n).length()) {
                return lcsRecursive(X, Y, m, n - 1);
            } else {
                return lcsRecursive(X, Y, m - 1, n);
            }
        }
    }

    static void lcsDynamic(String X, String Y, int m, int n) {
        int[][] L = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (X.charAt(i - 1) == Y.charAt(j - 1))
                    L[i][j] = L[i - 1][j - 1] + 1;
                else
                    L[i][j] = Math.max(L[i - 1][j],
                            L[i][j - 1]);
            }
        }
        int index = L[m][n];
        int temp = index;

        char[] lcs = new char[index + 1];
        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                lcs[index - 1] = X.charAt(i - 1);
                i--;
                j--;
                index--;
            } else if (L[i - 1][j] > L[i][j - 1])
                i--;
            else
                j--;
        }
        for (int k = 0; k < temp; k++)
            System.out.print(lcs[k]);
    }
}
