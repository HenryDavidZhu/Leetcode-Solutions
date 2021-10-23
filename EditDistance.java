class Solution {
    public int minDistance(String word1, String word2) {
        String s = word1;
        String t = word2;
        
        // dp[i][j] = edit distance from first i characters of s to 
        //            first j characters of j.
        int[][] dp = new int[s.length() + 1][t.length() + 1];
            
        // Fill out base cases:
        // Base Case 1: dist(i, 0) = i
        // We need to remove i characters from x to get to the empty string.
        for (int i = 0; i <= s.length(); i++) {
            dp[i][0] = i;
        }
            
        // Base Case 2: dist(0, j) = j
        // We need to add j characters from x to get to the first j characters
        // of y.
        for (int j = 0; j <= t.length(); j++) {
            dp[0][j] = j;
        }
        
        // Populate the dp matrix.
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                // Note: When computing the minimum of the insertion and deletion
                // costs, I factored out the common 1.

                // Insertion cost: dp[i][j - 1] + 1
                // We add the j'th character of y to x.
                int insertionCost = dp[i][j - 1];

                // Deletion cost: dp[i - 1][j] + 1
                // We delete the i'th character of x.
                int deletionCost = dp[i - 1][j];
                
                // Replacement cost: dp[i - 1][j - 1] + c
                // c = 0: If x's i'th character = y's jth character
                // c = 1: If x's i'th character != y's jth character
                int replacementCost = dp[i - 1][j - 1];
                int c = 0;
                if (s.charAt(i - 1) != t.charAt(j - 1)) {
                    c = 1;
                }
                replacementCost += c;

                dp[i][j] = Math.min(Math.min(insertionCost, deletionCost) + 1, replacementCost);
            }
        }
        
        return dp[s.length()][t.length()];
    }
}