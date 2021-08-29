class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int[][] dp = new int[mat.length][mat[0].length];
        
        for (int r = 0; r < dp.length; r++) {
            for (int c = 0; c < dp[0].length; c++) {
                dp[r][c] = Integer.MAX_VALUE;
            }
        }
        
        // 1st pass: top, left
        for (int r1 = 0; r1 < dp.length; r1++) {
            for (int c1 = 0; c1 < dp[0].length; c1++) {
                if (mat[r1][c1] == 0) {
                    dp[r1][c1] = 0;
                }
                else {
                    if (r1 > 0) {
                        if (dp[r1 - 1][c1] != Integer.MAX_VALUE) {
                            dp[r1][c1] = Math.min(dp[r1][c1], dp[r1 - 1][c1] + 1);
                        }
                    }
                    if (c1 > 0) {
                        if (dp[r1][c1 - 1] != Integer.MAX_VALUE) {
                            dp[r1][c1] = Math.min(dp[r1][c1], dp[r1][c1 - 1] + 1);
                        }
                    }
                }
            }
        }
        
        // 2nd pass: bottom, right
        for (int r2 = mat.length - 1; r2 >= 0; r2--) {
            for (int c2 = mat[0].length - 1; c2 >= 0; c2--) {
                if (mat[r2][c2] == 0) {
                    dp[r2][c2] = 0;
                }
                else {
                    if (r2 < mat.length - 1) {
                        if (dp[r2 + 1][c2] != Integer.MAX_VALUE) {
                            dp[r2][c2] = Math.min(dp[r2][c2], dp[r2 + 1][c2] + 1);
                        }
                    }
                    if (c2 < mat[0].length - 1) {
                        if (dp[r2][c2 + 1] != Integer.MAX_VALUE) {
                            dp[r2][c2] = Math.min(dp[r2][c2], dp[r2][c2 + 1] + 1);
                        }
                    }
                }                
            }
        }
        
        return dp;
    }
}