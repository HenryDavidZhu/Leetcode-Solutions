class Solution {
    public int countSquares(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        int squareOneMatrices = 0;
        
        for (int i = 0; i < matrix[0].length; i++) {
            dp[0][i] = matrix[0][i];
            
            if (dp[0][i] == 1) {
                squareOneMatrices++;
            }
        }
        
        for (int j = 1; j < matrix.length; j++) {
            dp[j][0] = matrix[j][0];
            
            if (dp[j][0] == 1) {
                squareOneMatrices++;
            }
        }
        
        for (int k = 1; k < matrix.length; k++) {
            for (int m = 1; m < matrix[0].length; m++) {
                if (matrix[k][m] == 1) {
                    dp[k][m] = Math.min(dp[k][m - 1], Math.min(dp[k - 1][m - 1], dp[k - 1][m])) + 1;
                    squareOneMatrices += dp[k][m];
                }
            }
        }
        
        return squareOneMatrices;
    }
}